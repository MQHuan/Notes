报错内容:
Error running 'ServiceStarter': Command line is too long. Shorten command line for ServiceStarter or also for Application default configuration.
解决办法：
修改项目下 .idea\workspace.xml，找到标签 <component name="PropertiesComponent"> ， 在标签里  <property name="dynamic.classpath" value="true" />

作者：水饺鲜生
链接：https://www.jianshu.com/p/7f3874b16e4b
来源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。