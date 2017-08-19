package org.itheima15.zhbj.controller.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.activity.MainUI;
import org.itheima15.zhbj.bean.NewsCenterBean.NewsListBean;
import org.itheima15.zhbj.controller.BaseController;
import org.itheima15.zhbj.controller.menu.NewsMenuController.OnViewIdleListener;
import org.itheima15.zhbj.controller.news.NewsListController;
import org.itheima15.zhbj.controller.tab.NewsCenterController;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller.menu
 * @类名: NewsMenuController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 下午3:22:38
 * @描述: 新闻菜单对应的controller
 * 
 * @更新时间: $Date: 2015-11-16 15:58:41 +0800 (Mon, 16 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 39 $
 * @更新内容: TODO:
 */
public class NewsMenuController extends BaseController
														implements OnPageChangeListener,
														OnOpenedListener,
														OnOpenListener,
														OnClosedListener,
														OnCloseListener
{
	@ViewInject(R.id.menu_news_viewpager)
	private ViewPager			mViewPager;

	@ViewInject(R.id.menu_news_indicator)
	private TabPageIndicator	mIndicator;

	private List<NewsListBean>	mDatas;		// viewpager对应的数据

	private SlidingMenu			mSlidingMenu;

	public NewsMenuController(Context context, List<NewsListBean> children) {
		super(context);

		this.mDatas = children;

		mSlidingMenu = ((MainUI) context).getSlidingMenu();
		mSlidingMenu.setOnOpenedListener(this);
		mSlidingMenu.setOnOpenListener(this);
		mSlidingMenu.setOnClosedListener(this);
		mSlidingMenu.setOnCloseListener(this);
	}

	@Override
	protected View initView(Context context)
	{
		// TextView tv = new TextView(context);
		// tv.setText("新闻菜单");
		// tv.setGravity(Gravity.CENTER);
		// tv.setTextSize(20);// px
		// tv.setTextColor(Color.RED);
		//
		// return tv;
		// 准备一个视图
		View view = View.inflate(context, R.layout.menu_news, null);

		// findViewById:
		// mViewPager = (ViewPager) view.findViewById(R.id.menu_news_viewpager);
		// 注入view
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	public void initData()
	{
		// 给viewpager加载数据
		mViewPager.setAdapter(new NewsAdapter());

		// 给indicator设置viewpager
		mIndicator.setViewPager(mViewPager);

		// 设置viewpager的监听
		mIndicator.setOnPageChangeListener(this);
	}

	@OnClick(R.id.menu_news_iv_arrow)
	public void clickArrow(View view)
	{
		// 让viewpager选中下一个
		int item = mViewPager.getCurrentItem();
		mViewPager.setCurrentItem(++item);
	}

	private class NewsAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mDatas != null) { return mDatas.size(); }
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
			// 展示数据 使用临时的textview显示

			// NewsListBean bean = mDatas.get(position);
			//
			// TextView tv = new TextView(mContext);
			// tv.setText(bean.title);
			// tv.setGravity(Gravity.CENTER);
			// tv.setTextSize(20);// px
			// tv.setTextColor(Color.RED);
			//
			// // 添加到container中
			// container.addView(tv);
			//
			// return tv;

			NewsListBean bean = mDatas.get(position);

			NewsListController controller = new NewsListController(mContext, bean);

			// 获得controller的布局
			View rootView = controller.getRootView();

			// 添加布局
			container.addView(rootView);

			// 给controller加载数据
			controller.initData();

			// 添加监听器
			// NewsMenuController.this.setOnViewIdleListener(controller);
			NewsMenuController.this.addOnViewIdleListener(controller);

			// 设置标记
			rootView.setTag(controller);

			return rootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			NewsListController controller = (NewsListController) ((View) object).getTag();
			NewsMenuController.this.removeOnViewIdleListener(controller);

			container.removeView((View) object);
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			// 获得viewpager的title
			NewsListBean bean = mDatas.get(position);
			return bean.title;
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position)
	{
		// 当viewpager选中某个页面时,判断slidingMenu是否可以触摸滑动
		// 只有在第0个页面slidingmenu才可以拖动出来

		MainUI ui = (MainUI) mContext;
		ui.getSlidingMenu().setTouchModeAbove(position == 0
															? SlidingMenu.TOUCHMODE_FULLSCREEN
															: SlidingMenu.TOUCHMODE_NONE);
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// * @see ViewPager#SCROLL_STATE_IDLE
		// * @see ViewPager#SCROLL_STATE_DRAGGING
		// * @see ViewPager#SCROLL_STATE_SETTLING

		if (state == ViewPager.SCROLL_STATE_IDLE)
		{
			// 闲置了
			// 回调接口
			// if (mListener != null)
			// {
			// mListener.onIdle();
			// }
			notifyUpdate();
		}
	}

	// private OnViewIdleListener mListener;

	// 提供setOnxxx
	// public void setOnViewIdleListener(OnViewIdleListener listener)
	// {
	// this.mListener = listener;
	// }
	private List<OnViewIdleListener>	mListeners	= new ArrayList<NewsMenuController.OnViewIdleListener>();

	public void addOnViewIdleListener(OnViewIdleListener listener)
	{
		if (!mListeners.contains(listener))
		{
			mListeners.add(listener);
		}
	}

	public void removeOnViewIdleListener(OnViewIdleListener listener)
	{
		mListeners.remove(listener);
	}

	private void notifyUpdate()
	{
		ListIterator<OnViewIdleListener> iterator = mListeners.listIterator();
		while (iterator.hasNext())
		{
			OnViewIdleListener next = iterator.next();
			next.onIdle();
		}
	}

	// 声明接口
	public interface OnViewIdleListener
	{
		/**
		 * 当闲置时的回调
		 */
		void onIdle();
	}

	@Override
	public void onClose()
	{
		notifyUpdate();
	}

	@Override
	public void onClosed()
	{
		notifyUpdate();
	}

	@Override
	public void onOpen()
	{
		notifyUpdate();
	}

	@Override
	public void onOpened()
	{
		notifyUpdate();
	}
}
