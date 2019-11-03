ViewPager 如何得到当前的Fragment （使用FragmentPagerAdapter）
使用FragmentPagerAdapter时，难免要在MainActivity 和 当前显示的Fragment间传递数据。但是FragmentPagerAdapter并没有给我们提供类似getCurrentFragment一类的API。

通过FragmentPagerAdapter源码，发现每次Fragment切换都会调用到FragmentPagerAdapter.setPrimaryItem 方法。这个方法里把一个Fragment设置为mCurrentPrimaryItem。 很明显，mCurrentPrimaryItem就是我们想得到的当前Fragment.那么我们只需要重载这个方法，把传入的Fragment记录下来即可。


我们可以在Adapter里定义一个currentFragment成员

```
public class XXPagerAdapter extends FragmentPagerAdapter{
Fragment currentFragment;

@Override
public void setPrimaryItem(ViewGroup container, int position, Object object) {
　　currentFragment = (QipanFragment) object;
　　super.setPrimaryItem(container, position, object);
}

}
```


在外层Activity中，

``` 
getFragmentPageAdapter().currentFragment
```

https://www.cnblogs.com/englefly/p/4222953.html
 

