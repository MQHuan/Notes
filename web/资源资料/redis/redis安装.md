###1 在redis解压的更目录下执行：
```
make
```

###2 完成后执行：
```
make PREFIX=/usr/local/redis install
```
![](install.png) 

/usr/local/redis是用户定义的安装目录

###3 copy解压目录的redis.conf到安装目录下
```
~/Downloads/redis-4.0.2$ sudo cp redis.conf /usr/local/redis/
```