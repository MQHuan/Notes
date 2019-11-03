## apt (Annotation Processing Tool)
注解处理器 Java5 中叫APT(Annotation Processing Tool)，在Java6开始，规范化为 Pluggable Annotation Processing。Apt应该是这其中我们最常见到的了，难度也最低。定义编译期的注解，再通过继承Proccesor实现代码生成逻辑，实现了编译期生成代码的逻辑。

链接：https://www.jianshu.com/p/dca3e2c8608a

oracle官方教程:https://docs.oracle.com/javase/7/docs/technotes/guides/apt/GettingStarted.html#how-to-use-apt

jdk7的更新说明：https://docs.oracle.com/javase/7/docs/technotes/guides/apt/index.html

## android-apt
这个是第三方用Groovy写的一个插件，目前已经不再维护

项目地址：https://bitbucket.org/hvisser/android-apt

谷歌对于这个插件的态度是：
The third party android-apt plugin is no longer supported. You should switch to the built-in annotation processor support, which has been improved to handle resolving dependencies lazily.

地址是：https://developer.android.com/studio/known-issues

替代方案地址是：https://developer.android.com/studio/build/#annotationProcessor_config