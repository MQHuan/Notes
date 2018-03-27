## vue中的$http服务  需要引入一个叫vue-resource.js的文件，因为vue.js中没有$http服务。如果需要使用这个服务去百度下载vue-resource.js 然后引进项目即可

## get
```
<!DOCTYPE html>  
<html>  
    <head>  
        <meta charset="UTF-8">  
        <title></title>  
        <script src="js/vue.js"></script>  
        <script src="js/vue-resource.js"></script>  
    </head>  
    <body>  
        <div id="app">  
            <input type="button" value="点击获取" @click="get()"/>  
        </div>  
        <script>  
            new Vue({  

                el:"#app",  
                methods:{  
                    get:function(){  
                        this.$http.get('get.php',{  
                            a:10,  
                            b:1  
                        }).then(function(res){  
                            alert(res.data);  

                        },function(res){  
                            alert(res.status)  
                        });  
                    }  
                }  

            })  


        </script>  
    </body>  
</html>  
```

## post
```
<!DOCTYPE html>  
<html>  
    <head>  
        <meta charset="UTF-8">  
        <title></title>  
        <script src="js/vue.js"></script>  
        <script src="js/vue-resource.js"></script>  
    </head>  
    <body>  
        <div id="app">  
            <input type="button" value="点击获取" @click="get()"/>  
        </div>  
        <script>  
            new Vue({  

                el:"#app",  
                methods:{  
                    get:function(){  
                        this.$http.post('post.php',{  
                            a:2,  
                            b:3  
                        },{  
                            emulateJSON:true  
                        }).then(function(res){  
                            alert(res.data);  

                        },function(res){  
                            alert(res.status)  
                        });  
                    }  
                }  

            })  


        </script>  
    </body>  
</html>  
```


具体请看：http://blog.csdn.net/qq_36947128/article/details/72832977
