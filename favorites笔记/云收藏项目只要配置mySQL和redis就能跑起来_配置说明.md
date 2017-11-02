## 1. 安装和启动mysql
### 1.1 linxu
#### 1.1.1 直接安装
ubuntu上安装MySQL非常简单只需要几条命令就可以完成。
```
sudo apt-get install mysql-server

apt-get isntall mysql-client

sudo apt-get install libmysqlclient-dev
```
安装过程中会提示设置密码什么的，注意设置了不要忘了，安装完成之后可以使用如下命令来检查是否安装成功：

sudo netstat -tap | grep mysql

通过上述命令检查之后，如果看到有mysql 的socket处于 listen 状态则表示安装成功。
#### 1.1.2 dokcer镜像安装
* 1.获取mysql镜像

从docker hub的仓库中拉取mysql镜像
```
sudo docker pull mysql
```
* 2.运行一个mysql容器

运行一个mysql实例的命令如下：
```
sudo docker run -p 3306:3306 --name mymysql -v $PWD/conf/my.cnf:/etc/mysql/my.cnf -v $PWD/logs:/logs -v $PWD/data:/mysql_data -e MYSQL_ROOT_PASSWORD=123456 -d mysql
```

上述命令各个参数含义：
```
-p 3306:3306：将容器的3306端口映射到主机的3306端口
-v $PWD/conf/my.cnf:/etc/mysql/my.cnf：将主机当前目录下的conf/my.cnf挂载到容器的/etc/mysql/my.cnf
-v $PWD/logs:/logs：将主机当前目录下的logs目录挂载到容器的/logs
-v $PWD/data:/mysql_data：将主机当前目录下的data目录挂载到容器的/mysql_data
-e MYSQL_ROOT_PASSWORD=123456：初始化root用户的密码
```
注意：$PWD代表当前目录路径，所以记得要在为mysql准备的目录下运行这个命令
#### 1.1.3 新建一张叫 favorites 的表
登录mysql
```
mysql -h172.17.0.1 -P3306 -uroot -p12345
```
 172.17.0.1 是mysql安装机器的ip, 12345是密码
 
 登录成功后建表
```
mysql> create database favorites;
```
 
### 1.2 windows 
#### 1.2.1 直接安装
#### 1.2.2 dokcer镜像安装


## 2. 安装和启动redis
### 2.1 linxu
#### 2.1.1 直接安装
```
wget http://download.redis.io/releases/redis-2.8.17.tar.gz
```
```
tar xzf redis-2.8.17.tar.gz
```
```
cd redis-2.8.17
```
```
make
```
** 启动redis服务**
在redis的安装目录下执行
```
./redis-server
```
### 2.2 dokcer镜像安装
* 1.拉取官方的镜像
```
docker pull  redis
```

* 2.配置redis.conf
这个不是必须的，但redis默认是没有密码的，如果要配置密码需要提供一个redis.conf
创建一个 redis/data 目录，如果需要指定配置文件，那么请在redis目录下放一个redis.conf配置文件。配置文件去redis安装包中找一个
* 3.启动redis容器
简单版本：
```
docker run -d -p 6379:6379 redis
```
复杂版本，好像跑不起来？
```
docker run -p 6379:6379 --name myredis -v $PWD/redis.conf:/etc/redis/redis.conf -v $PWD/data:/data -d redis:3.2 redis-server /etc/redis/redis.conf --appendonly yes
```
上述命令各个参数含义：
```
--name myredis : 指定容器名称，这个最好加上，不然在看docker进程的时候会很尴尬。
-p 6699:6379 ： 端口映射，默认redis启动的是6379，至于外部端口，随便玩吧，不冲突就行。
-v $PWD/redis.conf:/etc/redis/redis.conf ： 将主机中当前目录下的redis.conf配置文件映射。
-v $PWD/data:/data -d redis:3.2 ： 将主机中当前目录下的data挂载到容器的/data
--redis-server --appendonly yes :在容器执行redis-server启动命令，并打开redis持久化配置
```
注意：$PWD代表当前目录路径，所以记得要在为redis准备的目录下运行这个命令
### 2.2 windows 
#### 2.2.1 直接安装
#### 2.2.2 dokcer镜像安装


