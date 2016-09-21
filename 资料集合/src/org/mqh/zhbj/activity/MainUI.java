package org.itheima15.zhbj.activity;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.fragment.ContentFragment;
import org.itheima15.zhbj.fragment.MenuFragment;
import org.itheima15.zhbj.utils.DensityUtils;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.activity
 * @类名: MainUI
 * @作者: 肖琦
 * @创建时间: 2015-11-13 上午10:24:04
 * @描述: 主界面
 * 
 * @更新时间: $Date: 2015-11-15 15:52:51 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 25 $
 * @更新内容: TODO:
 */
public class MainUI extends SlidingFragmentActivity
{
	private static final String	TAG_MENU	= "tag_menu";
	private static final String	TAG_CONTENT	= "tag_content";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		// 设置内容部分的布局
		setContentView(R.layout.ui_main);

		// FrameLayout
		// LinearLayout
		// RelativeLayout

		// 设置背后的view:设置菜单对应的布局
		setBehindContentView(R.layout.main_menu);

		// 获得slidingMenu对象
		SlidingMenu slidingMenu = getSlidingMenu();

		// 设置右菜单
		// slidingMenu.setMode(SlidingMenu.RIGHT);

		// // 设置右侧菜单的布局
		// slidingMenu.setSecondaryMenu(R.layout.ui_splash);
		//
		// // 设置左右菜单
		// slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

		// 设置touch mode above
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 设置slidingMenu 菜单部分的宽度
		slidingMenu.setBehindWidth(DensityUtils.dp2px(this, 140));
		// slidingMenu.setBehindOffset(DensityUtils.dp2px(this, 140));

		// 设置Scroll Scale
		slidingMenu.setBehindScrollScale(0.5f);

		// 设置shadow
		slidingMenu.setShadowDrawable(R.drawable.shadow);

		// 设置fade
		slidingMenu.setFadeDegree(1f);

		// 设置touch mode behind
		// slidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 加载左侧和内容部分
		loadFragment();
	}

	private void loadFragment()
	{
		FragmentManager fm = getSupportFragmentManager();

		// 开启事务
		FragmentTransaction transaction = fm.beginTransaction();

		// 加载左侧的fragment
		transaction.replace(R.id.main_menu_container, new MenuFragment(), TAG_MENU);

		// 加载内容部分的fragment
		transaction.replace(R.id.main_content_container, new ContentFragment(), TAG_CONTENT);

		// 提交事务
		transaction.commit();

	}

	/**
	 * 获得左侧菜单的fragment
	 * 
	 * @return
	 */
	public MenuFragment getMenuFragment()
	{
		FragmentManager fm = getSupportFragmentManager();
		return (MenuFragment) fm.findFragmentByTag(TAG_MENU);
	}

	/**
	 * 获得内容部分的fragment
	 * 
	 * @return
	 */
	public ContentFragment getContentFragment()
	{
		FragmentManager fm = getSupportFragmentManager();
		return (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);
	}
}
