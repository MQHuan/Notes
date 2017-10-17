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
### 镜像列表
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



