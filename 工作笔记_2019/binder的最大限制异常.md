https://developer.android.google.cn/reference/android/os/TransactionTooLargeException

```
The Binder transaction buffer has a limited fixed size, currently 1Mb
```
这里的b考虑是bit，转换为字节如下：
1Mb=1024K bit=1048576bit=1048576/8Byte=131072字节
 