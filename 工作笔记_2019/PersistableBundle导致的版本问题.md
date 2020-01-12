在sdk版本>=21 (5.0)开发中, 使用PersistableBundle 会导致crash

在Activity 中有三个方法存在PersistableBundle

-onCreate()方法
-onSaveInstanceState()方法
-onRestoreInstanceState()方法

onCreate

第一种：
```
 @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
   }
```

第二种
```
 @Override
 public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);

 }
```
注意第二种只能在sdk版本>=21 (5.0)中使用

https://www.jianshu.com/p/a044e20fd6e7
https://stackoverflow.com/questions/34085681/how-to-avoid-a-noclassdeffounderror-android-os-persistablebundle-on-pre-l