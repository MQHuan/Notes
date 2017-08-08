Build Environment (环境配置)

To get started with Data Binding, download the library from the Support repository in the Android SDK manager.
先去Android SDK Manager中的支持库下载Data Binding的库。

To configure your app to use data binding, add the dataBinding element to your build.gradle file in the app module.
去应用中配置使用 data binding, 在app模块的build.gradle 文件中添加dataBinding成员

Use the following code snippet to configure data binding:
使用下面的代码去配置data binding

android {
 .....
 dataBinding {	
 	enabled = true;
  }

}

