package org.itheima15.zhbj.activity;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.R.layout;
import org.itheima15.zhbj.utils.Constants;
import org.itheima15.zhbj.utils.PreferenceUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj
 * @类名: SplashUI
 * @作者: 肖琦
 * @创建时间: 2015-11-13 上午10:02:25
 * @描述: 欢迎界面
 * 
 * @更新时间: $Date: 2015-11-13 11:35:15 +0800 (Fri, 13 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 11 $
 * @更新内容: TODO:
 */
public class SplashUI extends Activity
{
	private final static long	DURATION	= 1500;
	private View				mRootView;
	private static final String	TAG			= "SplashUI";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_splash);

		// 初始化view
		mRootView = findViewById(R.id.splash_root);

		// 1.旋转动画
		RotateAnimation rotate = new RotateAnimation(0, 360,
														Animation.RELATIVE_TO_PARENT, 0.5f,
														Animation.RELATIVE_TO_PARENT, 0.5f);

		// 2.缩放动画
		ScaleAnimation scale = new ScaleAnimation(0, 1,
													0, 1,
													Animation.RELATIVE_TO_PARENT, 0.5f,
													Animation.RELATIVE_TO_PARENT, 0.5f);

		// 3.透明度动画
		AlphaAnimation alpha = new AlphaAnimation(0, 1);

		AnimationSet set = new AnimationSet(true);
		set.setDuration(DURATION);
		set.addAnimation(rotate);
		set.addAnimation(scale);
		set.addAnimation(alpha);

		set.setInterpolator(new BounceInterpolator());

		set.setAnimationListener(new SplashAnimationListener());

		mRootView.startAnimation(set);
	}

	private class SplashAnimationListener implements AnimationListener
	{

		@Override
		public void onAnimationStart(Animation animation)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation animation)
		{
			// 动画结束时页面跳转
			// 用户第一次使用时，进入引导界面
			boolean flag = PreferenceUtils.getBoolean(SplashUI.this, Constants.KEY_FIRST_USED, true);

			if (flag)
			{
				// 1.引导界面

				Log.d(TAG, "进入引导页面");

				Intent intent = new Intent(SplashUI.this, GuideUI.class);
				startActivity(intent);
			}
			else
			{

				// 2.主页面
				Log.d(TAG, "进入主页面");

				Intent intent = new Intent(SplashUI.this, MainUI.class);
				startActivity(intent);
			}

			finish();

		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{
			// TODO Auto-generated method stub

		}

	}
}
