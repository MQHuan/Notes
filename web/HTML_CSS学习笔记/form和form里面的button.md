http://www.w3school.com.cn/tags/att_button_type.asp

如下 button 的submit对应着form的action,点击了type为submit的按钮会把数据提交到action里面去

```
<html>
<body>

<form action="/example/html/form_action.asp" method="get">
  First name: <input type="text" name="fname" /><br />
  Last name: <input type="text" name="lname" /><br />
  <button type="submit" value="Submit">Submit</button>
  <button type="reset" value="Reset">Reset</button>
</form>

</body>
</html>

```


如果想要自己处理，如下
```
<html>
<body>

<form action="/example/html/form_action.asp" method="get"  onsubmit="return false">
  First name: <input type="text" name="fname" /><br />
  Last name: <input type="text" name="lname" /><br />
  <button type="submit" v-on:click="login">Submit</button> // 对应Vue里面的login方法
  <button type="reset" value="Reset">Reset</button>
</form>

</body>
</html>

```
