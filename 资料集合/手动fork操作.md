###类似github的fork的应用场景下，手动进行fork


本质是在本地的git仓库再添加一条remote的Url,平时操作就和原来一样，需要fetch upstream（即新添加的remote Url）的代码时，就用
> git fetch upstream
> git merge upstream/{你想要merge的分支}

* 注意:　其中upstream 是新添加的remote的Url的名字，类似原生的origin,也都可以在项目根目录中的.git/config中看到


添加remote Url 的　command
>git remote add 自定义的名字　远程仓库的ssh 