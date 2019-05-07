1.FragmentPagerAdapter：在每次切换页面的时候，是将fragment进行分离，适合页面较少的fragment使用以保存一些内存，对系统内存不会有多大影响。





2.FragmentStatePagerAdapter：在每次切换页面的时候，是将fragment进行回收，适合页面较多的fragment使用，这样就不会消耗更多的内存。


所以，看源码中的destoryItem的方法，就可以看出这两个adapter的区别与用法。
--------------------- 
作者：LQ-刘强 
来源：CSDN 
原文：https://blog.csdn.net/qq_33429583/article/details/79793168 
版权声明：本文为博主原创文章，转载请附上博文链接！