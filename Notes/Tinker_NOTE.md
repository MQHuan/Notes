step1 :
Add following code in build.gradle of Project
 classpath "com.tencent.tinker:tinker-patch-grable-plugin:${TINKER_VERSION}"

step2 :
添加依赖
    provided("com.tencent.tinker:tinker-android-anno:${TINKER_VERSION}") { changing = true }
    compile("com.tencent.tinker:tinker-android-lib:${TINKER_VERSION}") { changing = true }
    compile "com.android.support:multidex:1.0.1"

step3 :
在 defaultConfig中添加
 multiDexEnabled true

step4:
复制一堆的gradle代码
注意:
坑1：修改ignoreWarning = true 和 tinkerId = "1.0.0"(这个要和前面一大堆代码中的versionName对应的)
坑2：keystore地址需要修改
signingConfigs {
	release {
		...
		storeFile file("/home/你的release.keystore地址")
        }
        debug {
            storeFile file("/home/mai/.android/debug.keystore")//这是linux默认的，修改成你的keystore地址
        }
}


step 5: 用DefaultApplicationLike的子类替换原有的Application(如果有)
并在DefaultApplicationLike的子类添加注解
@DefaultLifeCycle(application = "com.tinker.App",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
上面注解中的com.tinker.App是自动生成的Application,要把这个配置到 AndroidManifest.xml
生成的application在模块下的bulid/generated/source/apt/debug/

想在Application中写写代码，可以覆写：
@Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        context = base;
        TinkerInstaller.install(this);
        // 可以看成Application中的onCreate
    }

step 6:修复需要一个触发的地方，官方源码实在service中触发的，
在触发的地方调用
TinkerInstaller.onReceiveUpgradePatch(getApplicationContext()
  , Environment.getDownloadCacheDirectory().getAbsolutePath() + "/test");//test :是补丁包的名称，可以自定义

注意记得在AndroidManifest中添加读写存储的权限

更新的步骤

step 7: 修改gradle中的版本号信息，代码如下
   tinkerOldApkPath = "${bakPath}/app-debug-0318-18-27-08.apk"//修改这里的APK名字，替换未模块下bulid/bakApk里面的apk包名字

step 8在Module目录下里面命令行下使用 gradle tinkerPatchDebug  命令生成差异文件(差异文件生成在output/tinkerPatch)


