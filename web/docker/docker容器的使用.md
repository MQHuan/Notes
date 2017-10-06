###查看所有容器
```
sudo docker ps --all
```

###查看最近创建的容器
```
sudo docker ps -l
```

###查看正在运行的容器
```
sudo docker ps
```

###查看容器日志
docker logs [ID或者名字] 可以查看容器内部的标准输出。
```
sudo docker logs -f 7a38a1ad55c6
```

###查看容器内部运行的进程
```
docker top ID或者名字
```

###查看Docker容器的底层信息
```
docker inspect ID或者名字
```

###查看容器端口的映射情况
```
sudo docker port ID或者名字
```

###停止容器
```
sudo docker stop ID或者名字
```

###启动容器
```
sudo docker start ID或者名字
```

###重启容器
```
sudo docker restart ID或者名字
```

###删除容器
```
sudo docker rm ID或者名字
```