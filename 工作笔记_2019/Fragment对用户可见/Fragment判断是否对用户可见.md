### UservisibleHint
使用场景1：当fragment结合viewpager使用的时候 这个方法会调用
获取当前fragment是否对用户可见
```
getUserVisibleHint()
```

fragment是否对用户可见回调
```
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        
    }
```

### onHiddenChange
使用场景：add hide show进行fragment切换的时候

添加使用add  隐藏使用hide 需要判断是否添加 如果添加直接show
```
/**
     * 修改显示的内容 不会重新加载
     * newFragmeent 下一个fragment
     * currentFrament 当前的fragment
     */
    private void switchFragment(Fragment newFragmeent) {
        if (newFragmeent != currentFrament ) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!newFragmeent.isAdded()) { // 判断是否被add过
                // 隐藏当前的fragment，将 下一个fragment 添加进去
      transaction.hide(currentFrament).add(R.id.layout_content, newFragmeent).commit(); 
            } else {
                // 隐藏当前的fragment，显示下一个fragment
              transaction.hide(currentFrament).show(newFragmeent).commit(); 
            }
            currentFrament = newFragmeent
        }

    }
```
使用add hide() show()方法切换fragment  不会走任何的生命周期 无法通过生命周期进行刷新

这个时候另一个方法就派上用处了onHiddenChanged()
```
@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {   // 不在最前端显示 相当于调用了onPause();

            //做一些事情

            return;
        }else{  // 在最前端显示 相当于调用了onResume();
            //做一些事情
        }
    }

```
--------------------- 
作者：kristch_wu 
来源：CSDN 
原文：https://blog.csdn.net/kristch_wu/article/details/80000148 
版权声明：本文为博主原创文章，转载请附上博文链接！
