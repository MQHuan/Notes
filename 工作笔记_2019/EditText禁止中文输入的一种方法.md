方法：直接在回调中把中文字符删除    
      mDataBinding.evMonitorUnloadPhotoBarcode.getTextView()
                .addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(
                            CharSequence s, int start, int count,
                            int after) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s, int start, int before,
                            int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // 把中文输入删除，汉字的unicode范围是:0x4E00~0x9FA5
                        if (s.length() > 0) {
                            for (int i = 0; i < s.length(); i++) {
                                char c = s.charAt(i);
                                if (c >= 0x4e00 && c <= 0X9fff) {
                                    s.delete(i, i + 1);
                                }
                            }
                        }
                    }
                });

EidtText禁止输入的其他方法有
1 配置android:digits属性，digits只配置允许输入什么，没有配置不允许输入什么
2 配置inputType属性

具体参考：https://blog.csdn.net/ccpat/article/details/46652921