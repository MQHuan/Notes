```
 var apiURL = '/login?passWord=123&amp;userName=mai'

 如上面get请求在html里面要把 & 符号写成 &amp; 但在js发送get请求的时候并不会自动把&amp; 转化回&，所以在发送的时候把amp；替换掉

 apiURL.replace("amp;", "");

 
```
