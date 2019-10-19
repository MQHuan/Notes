23之前的PopupWindow没有默认height值，调用默认构建函数会把width和height设置为0，所以23之前的PopupWindow必须在代码里面设置宽高 setHeight(), setWidth();

>23之前如图：
![](./image/PopupWindow_API22之前.png)


> API23以及之后如图：
![](./image/PopupWindow_API23以及之后.png)