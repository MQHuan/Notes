##获取镜像
```
docker pull mysql
```
在这里不讲docker的安装，使用上面的命令就可以直接拉取最新的mysql镜像。

坑

##设置用户和密码

在拉取完mysql镜像后，就直接使用了:
```
docker run -r -t mysql
```
发现了提示如下错误：
```
error: database is uninitialized and password option is not specified 
  You need to specify one of MYSQL_ROOT_PASSWORD, MYSQL_ALLOW_EMPTY_PASSWORD and MYSQL_RANDOM_ROOT_PASSWORD
```
通过读取官方文档，发现，在使用mysql镜像的时候需要提供mysql用户名和密码，如果是使用root用户登录的时候，需要提供root用户的密码。提供密码的方式很简单，就是**使用docker run 的-e 指令**，
```
docker run -i -t -e MYSQL_ROOT_PASSWORD=hsulei mysql
```
可以使用-p 命令对外暴露3306端口。这样我们的数据库管理工具就能够连接并管理docker中的mysql镜像了。

##挂载数据卷

每次启动一个mysql镜像的时候，数据库都是空的，每次操作的时候都要重新建表，这个非常不友好。我们可以**使用-v 来挂载本地的数据库文件到mysql镜像中的/var/lib/mysql目录**下。
```
docker run -i -t -v -p 3306:3306 /var/lib/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=hsulei mysql
```
但是，这样挂载了数据卷的时候，我的workbench就无法访问此时的mysql镜像了。出现的问题是：不允许连接到数据库。去google了一下，有的说是mysql对访问的ip进行了闲置，只能是localhost进行连接，我进入了mysql镜像中，查看配置后，返现在mysql镜像中的配置文件中已经取消了访问ip的限制。那问题出在哪呢，然后又看到有人说要修改系统表，把root用户修改成任何ip都能访问。这时想到，我是通过挂载数据卷的方式启动的mysql镜像。那么在镜像中的数据库就是使用我本地的数据库文件，也就是说在启动的时候我没有必要指定root用户密码，

进入本地的mysql对root数据进行修改：
```
mysql>use mysql;
mysql>update  user set host = '%' where user= 'root';
mysql>selecthost, userfromuser;
```
然后再通过挂载数据卷的方式，就能通过workbench愉快的玩耍了。(docker-compose，也是这样)。