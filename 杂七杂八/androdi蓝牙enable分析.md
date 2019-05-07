这应该只会是唯一的一篇从头到尾的调用关系都分析一遍的文章，目的是为了帮助初学者从上往下一层一层分析代码，对大量的代码不再害怕。如果是对阅读Android代码很熟悉的人可以略过这一篇。
蓝牙Enable
Android的蓝牙Enable是由BluetoothAdapter提供的。只需要调用BluetoothAdapter.enable()即可启动蓝牙。下面我就分析这一个过程。由于Android的java层的代码过多，我只顺序的看下去。
1、打开BluetoothAdapter.java，找到其中的enable方法，代码如下:
    public boolean enable() {
        if (isEnabled() == true){
            if (DBG) Log.d(TAG, "enable(): BT is already enabled..!");
            return true;
        }
        try {
            return mManagerService.enable();
        } catch (RemoteException e) {Log.e(TAG, "", e);}
        return false;
    }
发现关键的一句就是mManagerService.enable();这个mManagerService是什么呢？其实就是btAdapter的一个proxy。可以在getDefaultAdapter()里面看到如下代码
public static synchronized BluetoothAdapter getDefaultAdapter() {
        if (sAdapter == null) {
            IBinder b = ServiceManager.getService(BLUETOOTH_MANAGER_SERVICE);
            if (b != null) {
                IBluetoothManager managerService = IBluetoothManager.Stub.asInterface(b);
                sAdapter = new BluetoothAdapter(managerService);
            } else {
                Log.e(TAG, "Bluetooth binder is null");
            }
        }
        return sAdapter;
    }
 
    BluetoothAdapter(IBluetoothManager managerService) {
        if (managerService == null) {
            throw new IllegalArgumentException("bluetooth manager service is null");
        }
        try {
            mService = managerService.registerAdapter(mManagerCallback); //这其实是一步重要的操作，初始化了我们的mService
        } catch (RemoteException e) {Log.e(TAG, "", e);}
        mManagerService = managerService;
        mServiceRecordHandler = null;
    }

它是通过ServiceManager获取了一个系统服务，然后转换为了IBluetoothManager接口，让mManagerService作为了bluetooth_manager服务的代理。这里基本就能想到，这个bluetooth_manager服务可能是Bluetooth.apk里面的btAdapter。但是我们还是得找找到底是怎么来的。
2、在frameworks/base/services/java/com/android/server/SystemServer.java里面可以看到这样一句：
bluetooth = new BluetoothManagerService(context);
ServiceManager.addService(BluetoothAdapter.BLUETOOTH_MANAGER_SERVICE, bluetooth);
哦，原来是用的一个BluetoothManagerService的东西注册的服务，并不是Bluetooth.apk里面的。去看看是个什么玩意儿。
3、打开BluetoothManagerService.java，可以看到，这个东西就是BluetoothAdapter里面的mManagerService的proxy了。下面看里面的enable方法：

    public boolean enable() {
        //前面省略权限相关的东西
        synchronized(mReceiver) {
            mQuietEnableExternal = false;
            mEnableExternal = true;
            // waive WRITE_SECURE_SETTINGS permission check
            long callingIdentity = Binder.clearCallingIdentity();
            persistBluetoothSetting(BLUETOOTH_ON_BLUETOOTH);
            Binder.restoreCallingIdentity(callingIdentity);
            sendEnableMsg(false);
        }
        return true;
    }
看到这里的关键就是sendEnableMsg()，继续tag进去看看究竟。
    private void sendEnableMsg(boolean quietMode) {
        mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_ENABLE,
                             quietMode ? 1 : 0, 0));
    }
额，又是Handler模式，这个玩意儿在Android系统里面用得太多了。。遇到这种东西的时候，就进入到mHandler这个对象里面的handleMessage去看switch---case就对了。
                case MESSAGE_ENABLE:
                    mHandler.removeMessages(MESSAGE_RESTART_BLUETOOTH_SERVICE);
                    mEnable = true;
                    handleEnable(msg.arg1 == 1);
                    break;
OK，去handleEnable里面去看吧，这里传送的参数是0。这个handleEnable有点太长了，我就截取重要部分了
           if ((mBluetooth == null) && (!mBinding)) {
                //Start bind timeout and bind
                Intent i = new Intent(IBluetooth.class.getName());
                if (!mContext.bindService(i, mConnection,Context.BIND_AUTO_CREATE,
                                          UserHandle.USER_CURRENT)) {
                }
            } else if (mBluetooth != null) {
		//如果已经绑定了服务，就做其他事……其实我们一般调用，都是会进入这里的。
            }
 
            //Enable bluetooth
            try {
                    if (!mQuietEnable) {
                        if(!mBluetooth.enable()) {
                            Log.e(TAG,"IBluetooth.enable() returned false");
                        }
                    }
                    else {
                        if(!mBluetooth.enableNoAutoConnect()) {
                            Log.e(TAG,"IBluetooth.enableNoAutoConnect() returned false");
                        }
                    }
             } catch (RemoteException e) {
                 Log.e(TAG,"Unable to call enable()",e);
             }

好吧，转那么大的一圈，结果就是调用了mBluetooth.enable();而且蛋疼的是在BluetoothAdapter里面本来就有一个mService，并且mService就是registerAdapter()返回的mBluetooth!!不过亲爱的朋友得注意了，我们BluetoothAdapter的mService的赋值是在这里的bindService之前的，而且其他的API的实现，都其实是直接用的mService，所以其实bindService是在BluetoothManagerService.java的另外一个MESSAGE_GET_NAME_AND_ADDRESS中调用的。真心蛋疼，这里我个人觉得写得很不好。
这个mBluetooth就是Bluetooth.apk里面的AdapterService了。去看看代码吧。
4.打开/packages/app/Bluetooth/src/com/android/bluetooth/btservice/AdapterService.java，找到关键代码
     public synchronized boolean enable(boolean quietMode) {
         enforceCallingOrSelfPermission(BLUETOOTH_ADMIN_PERM,
                 "Need BLUETOOTH ADMIN permission");
         if (DBG)debugLog("Enable called with quiet mode status =  " + mQuietmode);
         mQuietmode  = quietMode;
         Message m =
                 mAdapterStateMachine.obtainMessage(AdapterState.USER_TURN_ON);
         mAdapterStateMachine.sendMessage(m);
         return true;
     }
额，用了StateMachine，直接搜索UER_TURN_ON找到processMessage吧（StateMachine就是状态机，在不同的状态下，收到相同的Event，做不同的事情）
notifyAdapterStateChange(BluetoothAdapter.STATE_TURNING_ON);		//更新AdapterService里的状态为TURNING_ON
   mPendingCommandState.setTurningOn(true);				//设置在等待状态下的TurningOn为打开状态
   transitionTo(mPendingCommandState);					//转移到Pending(等待)状态
   sendMessageDelayed(START_TIMEOUT, START_TIMEOUT_DELAY);		//这个家伙是设置超时的
   mAdapterService.processStart();					//真正Enable蓝牙的地方


继续看processStart
void processStart() { 
       ..................       
    if (!mProfilesStarted && supportedProfileServices.length >0) {
        //Startup all profile services
        setProfileServiceState(supportedProfileServices,BluetoothAdapter.STATE_ON);	//这种情况下也会Call到else里面的一样的代码
    }else {
        if (DBG) {debugLog("processStart(): Profile Services alreay started");}
        mAdapterStateMachine.sendMessage(mAdapterStateMachine.obtainMessage(AdapterState.STARTED));
    }
}


Android就是这么烦，又进入了STARTED的状态，这次是在Pending状态下去看
    //Remove start timeout
    removeMessages(START_TIMEOUT);					//去除超时的定时器
    //Enable
    boolean ret = mAdapterService.enableNative();			//。。。原来这里才是真正的enableNative
    if (!ret) {								//如果失败了
        notifyAdapterStateChange(BluetoothAdapter.STATE_OFF);		//让AdapterService进入失败状态
        transitionTo(mOffState);					
    } else {
        sendMessageDelayed(ENABLE_TIMEOUT, ENABLE_TIMEOUT_DELAY);
    }


好了，这样就从java层进入到Native层了。对于以后分析蓝牙，我就直接分析Bluetooth.apk里面的东西了，就不会涉及到任何其他的java代码了。

5.在Bluetooth APP里面打开jni/com_android_bluetooth_btservice_AdapterService.cpp，找到里面的enableNative();
    jboolean result = JNI_FALSE;
    if (!sBluetoothInterface) return result;
 
    int ret = sBluetoothInterface->enable();
    result = (ret == BT_STATUS_SUCCESS) ? JNI_TRUE : JNI_FALSE;
    return result;
嗯，直接调用了sBluetoothInterface里面的enable();sBluetoothInterface的初始化在classInitNative()，这个函数大概做了以下的事情：

1、注册java的回调函数（就是当下层已经打开蓝牙了，然后要通知上层，蓝牙已经打开了，java层就可以发送蓝牙打开的Broadcast了。）
2、初始化蓝牙模块的HAL接口。
3、得到sBluetoothInterface

你需要对应着/external/bluetooth/bluedroid/btif/src/bluetooth.c看，就比较容易理解了。
5、接下来终于进入Bluedroid了，找到/external/bluetooth/bluedroid/btif/src/bluetooth.c里面的enable函数，发现调用的btif_enable_bluetooth()，用tag进去看，发现原来整个分析的关键点
bte_main_enable(btif_local_bd_addr.address);
这个函数有点长啊，在bluedroid/main/bte_main.c里面，我们就关心bt_hc_if->set_power就是了。前面有做一些初始化Bluedroid的动作

bt_hc_if是由bt_hc_get_interface()返回的。继续tag进去看。
发现在bluedroid/hci/src/bt_hci_bdroid.c里面的，有个set_power()
这个过程如下：
    int pwr_state;
 
    BTHCDBG("set_power %d", state);
 
    /* Calling vendor-specific part */
    pwr_state = (state == BT_HC_CHIP_PWR_ON) ? BT_VND_PWR_ON : BT_VND_PWR_OFF;
 
    if (bt_vnd_if)
        bt_vnd_if->op(BT_VND_OP_POWER_CTRL, &pwr_state);
    else
        ALOGE("vendor lib is missing!");
好吧，最终就是调用的bt_vnd_if->op(BT_VND_OP_POWER_CTRL, &pwr_state);bt_vnd_if在init_vnd_if()函数里面赋值，发现其实是一个libbt-vendor.so的interface。好吧，可以绝望了，这个是Vendor的library。
我解释一下什么是VND(Vendor)
Vendor就是芯片供应商的意思，在他们做好一块蓝牙芯片后，需要提供一些硬件相关的动作，比如上下电，设置波特率之类的。但是这些操作一般不会对没有许可的开放。Bluedroid提供了一个统一的接口bt_vendor_interface_t，供应商只需要实现这个接口定义的蓝牙相关的操作就可以交给bluedroid去做剩下的事情了。

我们继续分析，既然原生的Android代码能够编译出来可用的Android系统，那么里面肯定也有厂家提供的开放的libbt-vendor.so，我们进入/hardware/里面去执行以下命令
$ find . -name Android.mk | xargs grep "libbt"
返回结果：
$ find . -name Android.mk | xargs grep libbt
./qcom/bt/Android.mk:include $(call all-named-subdir-makefiles,libbt-vendor)
./qcom/bt/libbt-vendor/Android.mk:LOCAL_MODULE := libbt-vendor
哈哈，原来Nexus4用的高通提供的蓝牙芯片，那我们可以继续进去看咯。
打开./qcom/bt/libbt-vendor/bt_vendor_qcom.c，找到op函数，找到关键点
        case BT_VND_OP_POWER_CTRL:
            {
                nState = *(int *) param;
                retval = hw_config(nState);
                if(nState == BT_VND_PWR_ON
                   && retval == 0
                   && is_hw_ready() == TRUE){
                    retval = 0;
                }
                else {
                    retval = -1;
                }
            }
            break;
额，调用的hw_config(nState)，进入看
int hw_config(int nState)
{
    ALOGI("Starting hciattach daemon");
    char *szState[] = {"true", "false"};
    char *szReqSt = NULL;
 
    if(nState == BT_VND_PWR_OFF)
        szReqSt = szState[1];
    else
        szReqSt = szState[0];
 
    ALOGI("try to set %s", szReqSt);
 
    if (property_set("bluetooth.hciattach", szReqSt) < 0){
        ALOGE("Property Setting fail");
        return -1;
    }
 
    return 0;
}
看到这里，我又蛋疼了。怎么跟Android的前几个版本实现一样了。我到现在都不知道这个hciattach属性是在哪里定义的，估计可能根本没有使用这个东西，因为bluez的hciattach.c我没有找到。所以为了不妨碍我们学习，我又找到了Broadcom的Vendor实现
在/device/common/libbt/src/bt_vendor_brcm.c里面的。同样的方式找到了upio.c里面的upio_set_bluetooth_power()

int upio_set_bluetooth_power(int on)
{
    int sz;
    int fd = -1;
    int ret = -1;
    char buffer = '0';
 
    switch(on)
    {
        case UPIO_BT_POWER_OFF:
            buffer = '0';
            break;
 
        case UPIO_BT_POWER_ON:
            buffer = '1';
            break;
    }
 
    if (is_emulator_context())
    {
        /* if new value is same as current, return -1 */
        if (bt_emul_enable == on)
            return ret;
 
        UPIODBG("set_bluetooth_power [emul] %d", on);
 
        bt_emul_enable = on;
        return 0;
    }
 
    /* check if we have rfkill interface */
    if (is_rfkill_disabled())
        return 0;
 
    if (rfkill_id == -1)
    {
        if (init_rfkill())
            return ret;
    }
 
    fd = open(rfkill_state_path, O_WRONLY);
 
    if (fd < 0)
    {
        ALOGE("set_bluetooth_power : open(%s) for write failed: %s (%d)",
            rfkill_state_path, strerror(errno), errno);
        return ret;
    }
 
    sz = write(fd, &buffer, 1);
 
    if (sz < 0) {
        ALOGE("set_bluetooth_power : write(%s) failed: %s (%d)",
            rfkill_state_path, strerror(errno),errno);
    }
    else
        ret = 0;
 
    if (fd >= 0)
        close(fd);
 
    return ret;
}

好吧，原来就是在rfkill_state_path(/sys/class/rfkill/rfkill[x]/state)虚拟设备里写入了1。对于驱动的代码，我就不继续看了。毕竟目前我也没深入到那一块去。
rfkill是Linux下的一个标准的无线控制的虚拟设备，Linux也提供了rfkill的命令去查看以及控制所有的注册的无线设备。它们会在/dev/（PC的Linux）或者/sys/class（一般是Android）下生成相应的虚拟设备。
到这里，整个蓝牙的Enable过程就分析完了。。
总结
整个过程十分累，从应用到下层。但是其实关键步骤就那么几步，从JNI那一块开始入手的看，还是比较好分析的。只要我们愿意深入下去看，对于Bluedroid和蓝牙的理解一定会更上一层。

--------------------- 
作者：鱼塘鱼汤 
来源：CSDN 
原文：https://blog.csdn.net/yutao52shi/article/details/12690353 
版权声明：本文为博主原创文章，转载请附上博文链接！