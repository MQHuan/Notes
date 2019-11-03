    
    ```
    /**
    * 获取对用户可见的fragment
    */
    protected Fragment getVisbleFragment(String result, String tag) {

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment != null) {
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return false;
    }

    ```