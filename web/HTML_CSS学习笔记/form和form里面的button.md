http://www.w3school.com.cn/tags/att_button_type.asp

���� button ��submit��Ӧ��form��action,�����typeΪsubmit�İ�ť��������ύ��action����ȥ

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

�����Ҫ�Լ���������
```
<html>
<body>

<form action="/example/html/form_action.asp" method="get"  onsubmit="return false">
  First name: <input type="text" name="fname" /><br />
  Last name: <input type="text" name="lname" /><br />
  <button type="submit" v-on:click="login">Submit</button> // ��ӦVue�����login����
  <button type="reset" value="Reset">Reset</button>
</form>

</body>
</html>

```