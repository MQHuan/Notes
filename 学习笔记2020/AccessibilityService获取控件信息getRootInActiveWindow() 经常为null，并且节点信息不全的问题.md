## 1. 无障碍服务的xml 配置
```
<accessibility-service xmlns:android="http://schemas.android.com/apk/res/android"
    android:accessibilityEventTypes="typeAllMask"
    android:accessibilityFeedbackType="feedbackAllMask"
    android:accessibilityFlags="flagDefault|flagRetrieveInteractiveWindows|flagIncludeNotImportantViews"
    android:canRequestFilterKeyEvents="true"
    android:canRetrieveWindowContent="true"
    android:canRequestEnhancedWebAccessibility="true"
    android:notificationTimeout="100" />
```
上面的android:accessibilityFlags="flagDefault|flagRetrieveInteractiveWindows|flagIncludeNotImportantViews" 是重点
只有flagDefault只是基础的，不会带上特殊的flag类型，必须加上|flagRetrieveInteractiveWindows|flagIncludeNotImportantViews

## 2. getRootInActiveWindow() 改为getWindows()
getRootInActiveWindow() 这个方法只是获取到活动窗口的信息，信息可能不全或者，当前窗口不活动时会为null, getWindows可以获取到更全的信息节点,如下：
```
                AccessibilityNodeInfo root = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    List<AccessibilityWindowInfo> windows = getWindows();
                    if (windows.size() > 0) {
                        root = windows.get(0).getRoot();
                    }
                }
```

参考资料：https://blog.csdn.net/qq_28210079/article/details/80486592