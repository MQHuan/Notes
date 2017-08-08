1）安装好genymotion后，新建一个模拟器。去下载的时候报错

Unable to create Virtual Device: Connection timeout.

 

通过搜索找到解决方法：http://stackoverflow.com/questions/19700646/unable-to-create-genymotion-virtual-devicesconnection-timeout

在 C:\Users\[你的名字]\AppData\Local\Genymobile\目录下找到 genymotion.log。

打开日志文件，在最后几行找到出错原因如下：

复制代码
四月 21 15:38:02 [Genymotion] [Debug] Remote file size: 182005760 ,current local file size: 0 
四月 21 15:38:02 [Genymotion] [Debug] writting to local file with mode OpenMode( "Append|WriteOnly" ) 
四月 21 15:38:02 [Genymotion] [Debug] Downloading file  "http://files2.genymotion.com/dists/4.3/ova/genymotion_vbox86p_4.3_140326_020620.ova" 
四月 21 15:38:02 [Genymotion] [Debug] Start timer 
四月 21 15:38:02 [Genymotion] [Error] Connection Timeout 
四月 21 15:38:02 [Genymotion] [Debug] Received code: 2 : "" 
复制代码
复制 

http://files2.genymotion.com/dists/4.3/ova/genymotion_vbox86p_4.3_140326_020620.ova 

到浏览器地址栏，通过浏览器下载这个ova

下载好后copy 到C:\Users\[你的名字]\AppData\Local\Genymobile\Genymotion\ova\ 里。
再次启动genymotion，下载这个版本的模拟器就不会报错了。

 

 

2) 使用Genymotion调试出现错误INSTALL_FAILED_CPU_ABI_INCOMPATIBLE解决办法

转自：http://blog.csdn.net/wjr2012/article/details/16359113

点击下载Genymotion-ARM-Translation.zip

将你的虚拟器运行起来，将下载好的zip包用鼠标拖到虚拟机窗口中，出现确认对跨框点OK就行。然后重启你的虚拟机。
