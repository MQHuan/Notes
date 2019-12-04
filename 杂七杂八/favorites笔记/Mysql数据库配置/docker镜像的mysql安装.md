###1.获取mysql镜像

从docker hub的仓库中拉取mysql镜像
```
sudo docker pull mysql
```
###2.运行一个mysql容器

运行一个mysql实例的命令如下：
```
sudo docker run --name first-mysql -p 3306:3306 -e MYSQL\_ROOT\_PASSWORD=12345 -d mysql
```

上述命令各个参数含义：
```
run            运行一个容器
--name         后面是这个镜像的名称
-p 3306:3306   表示在这个容器中使用3306端口(第二个)映射到本机的端口号也为3306(第一个)
-d             表示使用守护进程运行，即服务挂在后台
```

#### 如果想持久化存储需要 把数据存储在本地目录，很简单，只需要映射本地目录到容器即可
1、加上-v参数
```
$ docker run -d -e MYSQL_ROOT_PASSWORD=admin --name mysql -v /data/mysql/data:/var/lib/mysql -p 3306:3306 mysql 
```
还可以指定配置文件
```
docker run -d -e MYSQL_ROOT_PASSWORD=admin --name mysql -v /data/mysql/my.cnf:/etc/mysql/my.cnf -v /data/mysql/data:/var/lib/mysql -p 3306:3306 mysql 
```

### 3.访问mysql数据库
想要访问mysql数据库，我的机器上需要装一个mysql-client。
```
sudo apt-get install mysql-client-core-5.6
```
下面我们使用mysql命令访问服务器，密码如刚才所示为12345,**172.17.0.1为我这台机器的ip,** 3306为刚才所示的占用本物理机的端口（不是在docker内部的端口）
```
mysql -h172.17.0.1 -P3306 -uroot -p12345
```
### 3.访问mysql数据库的方法二
1 进入镜像
```
docker exec -it id或容器名 /bin/bash
```
2 登录mysql
```
mysql -u root -p
```
---

作者：孙成龙
链接：http://www.jianshu.com/p/c24e3e5f5b58
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

