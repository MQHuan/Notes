链接：http://www.jianshu.com/p/4ac5831b36e1
#步骤一 － 创建Dockerfile
```
vim Dockerfile
```
在文件中输入下面的内容
```
FROM golang:1.9

# Install beego and the bee dev tool
RUN go get github.com/astaxie/beego && go get github.com/beego/bee

# Expose the application on port 8080
EXPOSE 8080

# Set the entry point of the container to the bee command that runs the
# application and watches for changes
CMD ["bee", "run"]


```

第一行，

FROM golang:1.6

将Go的官方映像文件作为基础映像。该映像文件预安装了Go 1.6. 该映像已经把$GOPATH的值设置到了/go。所有安装在/go/src中的包将能够被go命令访问。

第二行，

RUN go get github.com/astaxie/beego && go get github.com/beego/bee

安装beego包和bee工具。beego包将在应用程序中使用。bee工具用语在开发中再现地重新加载我们的代码。

第三行，

EXPOSE 8080

在开发主机上利用容器为应用程序开放8080端口。

最后一行，

CMD ["bee", "run"]

使用bee命令启动应用程序的在线重新加载。


#步骤二 － 构建映像文件
####创建映像文件
```
docker build -t xxxx .
```
#####注意：就这个例子而言，如果本地没有 golang:1.9 这个映像文件，可能会失败几次，如果本地下载好了golang:1.9, 成功率会更高

执行以上的命令将创建名为ma-image的映像文件。该映像文件现在可以用于使用该应用程序的任何人。这将确保这个团队能够使用一个统一的开发环境。

为了查看你的系统上的映像文件列表，运行如下的命令：


####查看映像文件列表
```
docker images
```
#步骤三 － 运行容器
一旦ma-image已经完成，你可以使用以下的命令启动一个容器：
```
docker run -it --rm --name ma-instance -p 8080:8080 -v /app/MathApp:/go/src/MathApp -w /go/src/MathApp ma-image
```
让我们分析一下上面的命令来看看它做了什么。
。docker run命令用于从一个映像文件上启动一个容器
。-it 标签以交互的方式启动容器
。--rm 标签在容器关闭后将会将其清除
。--name ma-instance 将容器命名为ma-instance
。-p 8080:8080 标签允许通过8080端口访问该容器
。-v /app/MathApp:/go/src/MathApp更复杂一些。它将主机的/app/MathApp映射到容器中的/go/src/MathApp。这将使得开发文件在容器的内部和外部都可以访问。
。ma-image 部分声明了用于容器的映像文件。

执行以上的命令将启动Docker容器。该容器为你的应用程序开发了8080端口。无论何时你做了变更，它都将自动地重构你的应用程序。你将在console（控制台）上看到以下的输出：

```
______
| ___ \
| |_/ /  ___   ___
| ___ \ / _ \ / _ \
| |_/ /|  __/|  __/
\____/  \___| \___| v1.9.1
2017/09/21 14:12:05 INFO     ▶ 0001 Using 'quickstart' as 'appname'
2017/09/21 14:12:05 INFO     ▶ 0002 Initializing watcher...
quickstart/controllers
quickstart/routers
quickstart
2017/09/21 14:12:06 SUCCESS  ▶ 0003 Built Successfully!
2017/09/21 14:12:06 INFO     ▶ 0004 Restarting 'quickstart'...
2017/09/21 14:12:06 SUCCESS  ▶ 0005 './quickstart' is running...
2017/09/21 14:12:06 [I] [asm_amd64.s:2337] http server Running on http://:8080

```
