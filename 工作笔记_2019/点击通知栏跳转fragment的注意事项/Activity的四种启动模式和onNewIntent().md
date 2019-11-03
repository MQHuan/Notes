Android中Activity启动模式详解 

　　在Android中每个界面都是一个Activity，切换界面操作其实是多个不同Activity之间的实例化操作。在Android中Activity的启动模式决定了Activity的启动运行方式。 

　　Android总Activity的启动模式分为四种： 

```
Activity启动模式设置：  
  
        <activity android:name=".MainActivity" android:launchMode="standard" />  
  
Activity的四种启动模式：  
  
    1. standard  
  
        默认启动模式，每次激活Activity时都会创建Activity，并放入任务栈中，永远不会调用onNewIntent()。  
  
    2. singleTop  
  
        如果在任务的栈顶正好存在该Activity的实例， 就重用该实例，并调用其onNewIntent()，否者就会创建新的实例并放入栈顶(即使栈中已经存在该Activity实例，只要不在栈顶，都会创建实例，而不会调用onNewIntent()，此时就跟standard模式一样)。  
  
    3. singleTask  
  
        如果在栈中已经有该Activity的实例，就重用该实例(会调用实例的onNewIntent())。重用时，会让该实例回到栈顶，因此在它上面的实例将会被移除栈。如果栈中不存在该实例，将会创建新的实例放入栈中（此时不会调用onNewIntent()）。   
  
    4. singleInstance  
  
        在一个新栈中创建该Activity实例，并让多个应用共享改栈中的该Activity实例。一旦改模式的Activity的实例存在于某个栈中，任何应用再激活改Activity时都会重用该栈中的实例，其效果相当于多个应用程序共享一个应用，不管谁激活该Activity都会进入同一个应用中。  
```

大家遇到一个应用的Activity供多种方式调用启动的情况，多个调用希望只有一个Activity的实例存在，这就需要Activity的onNewIntent(Intent intent)方法了。只要在Activity中加入自己的onNewIntent(intent)的实现加上Manifest中对Activity设置lanuchMode=“singleTask”就可以。 

       onNewIntent（）非常好用，Activity第一启动的时候执行onCreate()---->onStart()---->onResume()等后续生命周期函数，也就时说第一次启动Activity并不会执行到onNewIntent(). 而后面如果再有想启动Activity的时候，那就是执行onNewIntent()---->onResart()------>onStart()----->onResume().  如果android系统由于内存不足把已存在Activity释放掉了，那么再次调用的时候会重新启动Activity即执行onCreate()---->onStart()---->onResume()等。

     当调用到onNewIntent(intent)的时候，需要在onNewIntent() 中使用setIntent(intent)赋值给Activity的Intent.否则，后续的getIntent()都是得到老的Intent。 
--------------------- 
作者：清澈@Cherry 
来源：CSDN 
原文：https://blog.csdn.net/nihaoqiulinhe/article/details/50697301 
版权声明：本文为博主原创文章，转载请附上博文链接！