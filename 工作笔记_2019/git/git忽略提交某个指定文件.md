git 忽略提交某个指定的文件(不从版本库中删除)
执行指令：
```
[Sun@webserver2 demo]$ git update-index --assume-unchanged config.conf
[Sun@webserver2 demo]$ git status
```
此时忽略config.conf文件跟踪之后再查看状态，发现已经没有显示config.conf的状态了。

附取消忽略指令：
```
[Sun@webserver2 demo]$ git update-index --no-assume-unchanged config.conf
```