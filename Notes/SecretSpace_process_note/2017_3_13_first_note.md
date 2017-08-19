# 更少的代码，更高效的实现

### 用ViewPager+BottomNavigation作为最上层的基础架构，这里的ViewPager是自定义的，重写了onInterceptTouchEvent和onTouchEvent(都返回false),这样做ViewPager就不会滑动了，实现只有通过用户点击BottomNavigation的item，才会切换界面的效果。

### 准备使用Data Binding,放弃ButterKnife,在项目中大量使用RecyclerView

### 网路框架准备用retrofit

### 图片加载框架，估计不需要， 如果要就用Glide

### 视频播放

### 可能引入greenDao一类的库


