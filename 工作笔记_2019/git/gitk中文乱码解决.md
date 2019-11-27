### Solution 1 :
打开以下地址的配置文件：
> C:\Users\Administrator.gitconfig

在文件末尾加入
```
[gui]
    encoding = utf-8
```

### Solution 2:
直接在命令行输入以下directive:
```
git config --global gui.encoding utf-8
```

reference : https://blog.csdn.net/zhezhebie/article/details/85157374
