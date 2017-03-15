/***************************************
Qusetion:
Android Studio 会默认把七个平台(armeabi,armeabi-v7,arm64-v8a,mips,mips64,x86,x86_64)全部都打包到APK包里，这样在armeabi里面添加一个so文件就必须在armeabi-v7,arm64-v8a都放一个文件名相同的，不然会报ClassNotFoundException
Answer:
在gradle中配置splits

android {
 splits {
  enable true
  reset()
  include "armeabi-v7a"//Specifies a list of ABIs that Gradle should create APKs for.
  universalApk false
 }

}

相关资料：
Developer Android: https://developer.android.com/studio/build/configure-apk-splits.html
中文博客：http://jp1017.github.io/2016/03/18/%E5%AE%89%E5%8D%93%20jni%20%E5%BC%80%E5%8F%91%20%E2%80%94%E2%80%94%20Android%20Studio%20%E6%89%93%E5%8C%85%20so%20%E7%9A%84%E5%9D%91/
