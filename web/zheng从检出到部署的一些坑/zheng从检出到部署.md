### 下载项目
忘记啦，以后补上，参考视频，基本是用idea直接重github下载的

打开后会自动下载依赖，视频里面只用几分钟，我的下载了接近一个小时，和本地有没有那些依赖库有关吧
### IDEA窗口调出
* 1 . idea刚进去是没有任何工具窗口的，必要的工具窗口可以通过以下的方法调出：
idea顶部的工具栏 --> View --> Tool Windows 
![](imge/idea调出常用窗口.png)

* 2 . 也可以通过万能搜索打开
按 Ctrl+ Shift + a
在弹出的窗口中输入要找的东西就可以啦
![](imge/万能搜索.png)

* 3 . 默认是没有下面的右边框栏的
![](imge/右边框栏.png)
点击左下角的小图标会出来
![](imge/左下角.png)

### install项目
![](imge/install项目.png)
要等几分钟
![](imge/构建成功的提示.png

### 配置MySQL
下载MySQL并安装，这个参考《zheng框架解析.html》
* 1 . 配置本地的SQL
![](imge/启动配置MySQL.png)
![](imge/配置MySQL.png)
* 2 . 新建zheng数据库，导入project-datamodel文件夹下的zheng.sql
这个参考qq群的的《zheng框架解析.html》，不重复啦

### 修改数据连接信息
先参考《zheng框架解析.html》
需要修改各dao模块和rpc-service模块的redis.properties、jdbc.properties、generator.properties数据库连接等配置信息，因为用来AES加密，所以需要用项目里面的AESUtil.java修改：
把密码改到main里面去，只要把你设置的密码填到如图红色处，然后运行这个java文件就会在控制台输出加密后的密码
![](imge/AESUtil修改.png)
![](imge/AES加密的结果.png)
然后直接用快捷键全局替换，把旧的那个改成新的就好，
Ctrrl+Shift+R 调出全局替换窗口，旧的密码在dev.properties可以看到，在上图的输出中也可以看到，就是红圈密钥上面的那个
![](imge/密钥的全局替换.png)

### 启动Zookeeper、Redis、ActiveMQ、Nginx
前三个的启动参考原来的教程就可以啦，
Nginx的启动需要配置一个Nginx.conf,可以参考project-tools/nginx下的*.conf文件，注意的是所有地址要改成你本地的
这里只讲几个重点的坑：
* 1 建议把nginx.conf放到nginx的安装目录下，这样有些配置文件不用标明路径
* 2 ![](imge/nginx配置的注意点1.png)
* 3 nginx.conf里面要指定运行的service的，在zheng/project-tools/nginx/servers/zheng-ui.conf,这个文件的路径要配置对，也只可以直接把整个文件夹拷贝到nginx目录下，找到这个路径就好
![](imge/service的conf配置.png)
![](imge/service的conf.png)

### 各个模块的启动步骤
参考官方的readme.cm就好啦
