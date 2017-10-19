### 镜像检索
除了在官网https://hub.docker.com/检索外，还可以用下面的命令
```
docker search 镜像名
```
检索Redis, 输入：
```
docer search redis
```

### 镜像下载
```
docker pull 镜像名
```
eg:
```
docker pull redis
```
### 查看镜像列表
```
docker images
```

### 镜像删除
删除指定镜像
```
docker rmi image-id
```
删除所有镜像
```
docker rmi $(docer images -g)
```

### 运行镜像为容器
 --name后面是为容器取的名称；
 -d 表示 detached ,救治执行完这句命令后控制台不会阻塞，可继续输入命令;
 最后的image-name 是使用哪个镜像来运行容器
```
docker run --name container-name -d image-name
```
eg:
```
docker run --name test-redis -d redis
```

### 查看容器列表
```
docker ps
```
查看运行和停止状态的容器:
```
docker ps -a
```

### 停止容器
```
docker stop container-name/container-id
```
eg：
```
docker stop test-redis
```

### 启动容器
```
docker start container-name/container-id
```
再次启动我们刚才停止的容器
```
docker start test-redis
```

### 端口映射
Docker容器中运行的软件所使用的端口，在本机和本机的局域网是不能访问的；
需要将Docker容器中的端口映射到当前主机的端口上
端口映射是通过** -p**参数实现的
eg: 映射容器6379端口到本机的6378端口
```
docker run -d -p 6378:6379 --name port-redis redis
```

### 删除容器
删除单个容器
```
docker rm container-id
```
删除所有容器
```
docker rm   $(docker ps -a -q)
```

### 容器日志
查看当前容器日志
```
docker logs container-name/container-id
```
查看上下面一个容器日志
```
docker logs port-redis
```
### 登录容器
```
docker exec -it container-id/container-name bash
```
使用exit命令退出登录

