[](https://www.jianshu.com/p/eea14ca0b164)

有些设备会在应用点击了home按键后，再点击图标会重新调一个启动界面的栈
可以在启动时判断启动界面非栈底就直接finish调来处理：
```
        if (!this.isTaskRoot()) {
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                }
            }
        }
```