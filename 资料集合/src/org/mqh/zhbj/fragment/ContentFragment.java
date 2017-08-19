package org.itheima15.zhbj.fragment;

import java.util.ArrayList;
import java.util.List;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.activity.MainUI;
import org.itheima15.zhbj.controller.BaseController;
import org.itheima15.zhbj.controller.TabController;
import org.itheima15.zhbj.controller.tab.GovController;
import org.itheima15.zhbj.controller.tab.HomeController;
import org.itheima15.zhbj.controller.tab.NewsCenterController;
import org.itheima15.zhbj.controller.tab.SettingController;
import org.itheima15.zhbj.controller.tab.SmartServiceController;
import org.itheima15.zhbj.view.NoScrollViewPager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.fragment
 * @类名: ContentFragment
 * @作者: 肖琦
 * @创建时间: 2015-11-13 下午4:04:24
 * @描述: TODO:
 * 
 * @更新时间: $Date: 2015-11-15 16:02:51 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 26 $
 * @更新内容: TODO:
 */
public class ContentFragment extends Fragment
{
	public static final String	TAG	= "ContentFragment";
	private NoScrollViewPager	mViewPager;
	private RadioGroup			mRgTabs;

	// private List<TextView> mPageDatas; // 临时使用的数据
	// private List<View> mPageDatas; // 临时使用的数据
	private List<TabController>	mPageDatas;				//
	private int					mCurrentTab;				// 记录当前选中的tab

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = View.inflate(getActivity(), R.layout.content, null);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		// 当View创建后的回调

		// 初始化View
		mViewPager = (NoScrollViewPager) view.findViewById(R.id.content_viewpager);
		mRgTabs = (RadioGroup) view.findViewById(R.id.content_rg_tabs);

		// 初始化事件
		mRgTabs.setOnCheckedChangeListener(new TabChangedListener());

		// 设置默认选中项
		mRgTabs.check(R.id.content_rb_home);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		// 加载数据
		initData();
	}

	private void initData()
	{
		// // 模拟数据显示 TODO:
		// mPageDatas = new ArrayList<TextView>();
		// for (int i = 0; i < 5; i++)
		// {
		// View view = View.inflate(getActivity(), resource, null);// xml获得的view
		// // TextView tv = new TextView(getActivity());
		// // tv.setText("页面-" + i);
		// // tv.setGravity(Gravity.CENTER);
		// // tv.setTextSize(20);// px
		// // tv.setTextColor(Color.RED);
		//
		// // mPageDatas.add(tv);
		// mPageDatas.add(view);
		// }

		// 加载页面数据
		mPageDatas = new ArrayList<TabController>();
		mPageDatas.add(new HomeController(getActivity()));// 首页
		mPageDatas.add(new NewsCenterController(getActivity()));// 新闻中心
		mPageDatas.add(new SmartServiceController(getActivity()));// 智慧服务
		mPageDatas.add(new GovController(getActivity()));// 政务
		mPageDatas.add(new SettingController(getActivity()));// 设置

		// 给viewpager设置adapter
		mViewPager.setAdapter(new ContentPagerAdapter());
	}

	private class TabChangedListener implements OnCheckedChangeListener
	{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			// checkedId:选中的radioButton的id
			switch (checkedId)
			{
				case R.id.content_rb_home:
					mCurrentTab = 0;
					// 没有菜单
					setMenuEnable(false);
					break;
				case R.id.content_rb_news:
					mCurrentTab = 1;
					// 有菜单
					setMenuEnable(true);
					break;
				case R.id.content_rb_smart:
					mCurrentTab = 2;
					// 有菜单
					setMenuEnable(true);
					break;
				case R.id.content_rb_gov:
					mCurrentTab = 3;
					// 有菜单
					setMenuEnable(true);
					break;
				case R.id.content_rb_setting:
					mCurrentTab = 4;
					// 没有菜单
					setMenuEnable(false);
					break;
				default:
					break;
			}

			mViewPager.setCurrentItem(mCurrentTab);
		}
	}

	private void setMenuEnable(boolean enable)
	{
		MainUI ui = (MainUI) getActivity();
		ui.getSlidingMenu().setTouchModeAbove(enable ? SlidingMenu.TOUCHMODE_FULLSCREEN : SlidingMenu.TOUCHMODE_NONE);
	}

	private class ContentPagerAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mPageDatas != null) { return mPageDatas.size(); }
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{

			Log.d(TAG, "加载" + position + "页面");

			// TextView tv = mPageDatas.get(position);
			//
			// container.addView(tv);
			//
			// return tv;

			BaseController controller = mPageDatas.get(position);

			// 通过控制器提供显示的view
			View rootView = controller.getRootView();
			container.addView(rootView);// 显示的view

			// 提供加载数据的方式
			controller.initData();

			return rootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			Log.d(TAG, "销毁" + position + "页面");

			container.removeView((View) object);
		}
	}

	public void switchMenu(int position)
	{
		// 获得当前选中的tabController
		TabController controller = mPageDatas.get(mCurrentTab);

		// 让选中的controller负责切换他的menu的内容
		controller.switchMenu(position);
	}
}
