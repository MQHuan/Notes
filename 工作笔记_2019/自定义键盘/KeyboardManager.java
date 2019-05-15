package com.kye.pad.manager;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.kye.pad.R;

/**
 * Routing
 * Desc Keyboard 工具类
 * Source
 * Created by Chase on 2018/10/29 17:29
 * Modify by Chase on 2018/10/29 17:29
 * Version 1.0
 */
public class KeyboardManager implements OnKeyboardActionListener {

    private static final String TAG = "KeyboardManager";
    private Context mContext;
    private KeyboardView keyboardView;
    private Keyboard k1;// 键盘1
    private Keyboard k2;// 键盘2
    private String[] mTexts; // 进来键盘输入的文本（车牌号）
    private int mCurrentIndex = 0;
    private int mMaxLength;
    private OnKeyListener mOnKeyListener;

    public KeyboardManager(Context context, KeyboardView keyboardView, int maxLength, String carNo) {
        this.mContext = context;
        this.keyboardView = keyboardView;
        this.mMaxLength = maxLength;
        // 初始化文本
        mTexts = new String[maxLength];
        if (!TextUtils.isEmpty(carNo)) {
            int carNolength = carNo.length();
            for (int i = 0; i < maxLength; i++) {
                if (i < carNolength) {
                    mTexts[i] = String.valueOf(carNo.charAt(i));
                }
            }
        }

        k1 = new Keyboard(context, R.xml.keyboard_car_no_one);
        k2 = new Keyboard(context, R.xml.keyboard_car_no_two);
        keyboardView.setKeyboard(k1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(false); // 点击某个键,不会在键盘上方悬浮的显示该键的预览
        keyboardView.setOnKeyboardActionListener(this);
    }

    public void setOnKeyListener(OnKeyListener onKeyListener) {
        this.mOnKeyListener = onKeyListener;
    }

    /********** OnKeyboardActionListener **********/

    @Override
    public void swipeUp() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void onText(CharSequence text) {
        Log.e(TAG, "onText " + text);
    }

    @Override
    public void onRelease(int primaryCode) {
        Log.e(TAG, "onRelease " + Character.toString((char) primaryCode));
    }

    @Override
    public void onPress(int primaryCode) {
        Log.e(TAG, "onPress " + Character.toString((char) primaryCode));
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        /*
         回退
         */
        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            Log.e(TAG, "onKey 回退");
            if (mCurrentIndex > 0) {
                // 将当前索引减一，并清除对应索引的数据
                mCurrentIndex--;
                mTexts[mCurrentIndex] = "";
                // 退到第一个，显示键盘一
                if (mCurrentIndex == 0) {
                    showKeyboardOneDelay();
                }
                // 显示文本
                if (mOnKeyListener != null) {
                    mOnKeyListener.onKey(mTexts, mCurrentIndex);
                }
            }
            return;
        }

        /*
         其他按键
         */
        // 超出输入框，继续输入没反应
        if (mCurrentIndex > mMaxLength - 1) {
            return;
        }
        mTexts[mCurrentIndex] = Character.toString((char) primaryCode);
        mCurrentIndex++;
        showKeyboardTwo();
        if (mOnKeyListener != null) {
            mOnKeyListener.onKey(mTexts, mCurrentIndex);
        }
    }

    /********** OnKeyboardActionListener **********/

    /**
     * 设置当前焦点索引
     *
     * @param currentIndex
     */
    public void setCurrentIndex(int currentIndex) {
        this.mCurrentIndex = currentIndex;
        // 根据索引，显示不同的键盘
        if (currentIndex == 0) {
            showKeyboardOne();
            return;
        }
        showKeyboardTwo();
    }

    /**
     * 获取当前焦点索引
     *
     * @return
     */
    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    /**
     * 显示键盘控件
     */
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏键盘控件
     */
    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 延时显示键盘1:如果不延时，在长按回退时，直接切换键盘，会崩溃。
     */
    private void showKeyboardOneDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showKeyboardOne();
            }
        }, 100);
    }

    /**
     * 显示键盘1
     */
    private void showKeyboardOne() {
        Log.e(TAG, "showKeyboardOne begin");
        showKeyboard();
        keyboardView.setKeyboard(k1);
        Log.e(TAG, "showKeyboardOne end");
    }

    /**
     * 显示键盘2
     */
    private void showKeyboardTwo() {
        showKeyboard();
        keyboardView.setKeyboard(k2);
    }

    public void clearView(){
        for (int i = 0 ; i < mTexts.length; i++){
            mTexts[i] = "";
        }
    }

    /**
     * 按键监听
     */
    public interface OnKeyListener {
        void onKey(String[] texts, int currentIndex);
    }
}
