Android开发拨打座机分机号码
Android开发拨打如：021-11111111，分机号码是1111的电话号码时，如下：

String phone = "021-11111111";

String fenPhone = "1111";

Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone + "," + fenPhone));

startActivity(phoneIntent);



手机上拨打:10080P1W1，这里P和W分别是Auto DTMF和Wait user confirm DTMF,在Android里面没有P和W，是用,和;代替的，但功能就是P,W。

也就是说"tel:" + phone + "," + fenPhone：是自动拨打分机号码；

而"tel:" + phone + ";" + fenPhone：是需要用户确认，确认之后，自动拨打分机号码



［注意］Android拨打电话号码需要加入权限在配置文件中：


<uses-permission android:name="android.permission.CALL_PHONE" />


--------------------- 
作者：百家晓东 
来源：CSDN 
原文：https://blog.csdn.net/tianmaxingkong_/article/details/48752103 
版权声明：本文为博主原创文章，转载请附上博文链接！


下面是自己写的，一个可以根据输入电话返回可拨打电话字符串的方法
```
    /**
     * 获取固定电话号码，非固定电话返回空字符串
     * @param number 电话号码
     * @return 非固定电话返回空字符串
     */
    public static String obtainFixedPhone(String number) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{8}|\\d{4}-\\d{7,8}");

        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {// 座机直接返回
            return number;
        } else if (matcher.lookingAt()){// 以座机开头的是带分机号的例如：0755-2657471-001
            String[] split = number.split("-");
            if (split.length == 3) {
                return split[0] + "-" + split[1] + "," + split[2];
            }
        }
        return "";
    }
```

```
    /**
     * 拨打电话
     *
     * @param context 上下文
     * @param phone   电话号码
     */
    public static void call(Context context, String phone) {
        //Intent intent = new Intent(Intent.ACTION_CALL);
        //不直接拨打
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }
```