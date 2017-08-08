package org.itheima15.zhbj.controller.news;

import java.util.List;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.activity.NewsDetailUI;
import org.itheima15.zhbj.bean.NewsDataBean;
import org.itheima15.zhbj.bean.NewsCenterBean.NewsListBean;
import org.itheima15.zhbj.bean.NewsDataBean.NewsBean;
import org.itheima15.zhbj.bean.NewsDataBean.TopNewsBean;
import org.itheima15.zhbj.controller.BaseController;
import org.itheima15.zhbj.controller.menu.NewsMenuController.OnViewIdleListener;
import org.itheima15.zhbj.utils.Constants;
import org.itheima15.zhbj.utils.DensityUtils;
import org.itheima15.zhbj.utils.PreferenceUtils;
import org.itheima15.zhbj.view.RefreshListView;
import org.itheima15.zhbj.view.RefreshListView.OnRefreshListener;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.sax.StartElementListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller.news
 * @类名: NewsListController
 * @作者: 肖琦
 * @创建时间: 2015-11-16 上午9:19:58
 * @描述: TODO:
 * 
 * @更新时间: $Date: 2015-11-19 08:54:41 +0800 (Thu, 19 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 47 $
 * @更新内容: TODO:
 */
public class NewsListController extends BaseController implements OnPageChangeListener, OnViewIdleListener, OnRefreshListener
{
	private static final String	TAG	= "NewsListController";

	@ViewInject(R.id.news_list_viewpager)
	private ViewPager			mViewPager;				// 轮播图的viewpager

	@ViewInject(R.id.news_list_point_container)
	private LinearLayout		mPointContainer;			// 装点的容器

	@ViewInject(R.id.news_list_tv_title)
	private TextView			mTvTitle;					// 轮播图的title

	@ViewInject(R.id.news_list_listview)
	private RefreshListView		mListView;

	private NewsListBean		mData;						// 当前页面对应的数据

	private List<TopNewsBean>	mPicDatas;					// 轮播图对应的数据

	private BitmapUtils			mBitmapUtils;

	// // 是否是在主线程中new
	// private final Handler handler = new Handler() {
	// public void handleMessage(Message msg)
	// {
	// // 执行主线程操作
	// };
	// };

	// private Runnable task;
	private SwitchTask			mSwitchTask;

	private List<NewsBean>		mNewsDatas;

	private String				mMoreUrl;

	private NewsListAdapter		mNewsAdapter;

	public NewsListController(Context context, NewsListBean bean) {
		super(context);
		this.mData = bean;

		mBitmapUtils = new BitmapUtils(context);

		// Message msg = handler.obtainMessage();
	}

	@Override
	protected View initView(Context context)
	{
		View view = View.inflate(context, R.layout.news_list, null);
		// 注入
		ViewUtils.inject(this, view);

		// 加载轮播图 的布局
		View picView = View.inflate(context, R.layout.news_header, null);
		// 注入
		ViewUtils.inject(this, picView);

		// 给listView添加headerview
		mListView.addHeaderView(picView);

		// 设置点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				position = position - mListView.getHeaderViewsCount();

				Toast.makeText(mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
				NewsBean bean = mNewsDatas.get(position);

				// 设置已读标记
				PreferenceUtils.putBoolean(mContext, bean.id + "", true);
				// UI要刷新
				mNewsAdapter.notifyDataSetChanged();

				String url = bean.url;
				Intent intent = new Intent(mContext, NewsDetailUI.class);
				intent.putExtra(NewsDetailUI.KEY_URL, url);
				mContext.startActivity(intent);
			}
		});

		// 设置listView的刷新监听
		mListView.setOnRefreshListener(this);

		return view;
	}

	@Override
	public void initData()
	{

		// 访问网络获得数据
		final String url = Constants.SERVER_URL + mData.url;

		// 读缓存
		String json = PreferenceUtils.getString(mContext, url);
		if (!TextUtils.isEmpty(json))
		{
			// 加载缓存的数据
			processData(json);
		}

		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException e, String msg)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				int statusCode = responseInfo.statusCode;
				// if (200 == statusCode)
				// {
				String result = responseInfo.result;

				// 存储缓存
				PreferenceUtils.putString(mContext, url, result);

				processData(result);
				// }
			}
		});
	}

	private void processData(String json)
	{
		// 处理数据--> json解析 ---->javabean ----->UI显示javabean
		Gson gson = new Gson();
		NewsDataBean bean = gson.fromJson(json, NewsDataBean.class);

		// 获得加载更多的url
		mMoreUrl = bean.data.more;

		// 校验
		Log.d(TAG, "" + bean.data.topnews.get(0).title);

		// 加载list数据
		mPicDatas = bean.data.topnews;

		// 给viewpager加载数据
		mViewPager.setAdapter(new PicAdapter());// list数据

		// 添加点
		// 清空所有的点
		mPointContainer.removeAllViews();
		for (int i = 0; i < mPicDatas.size(); i++)
		{
			View point = new View(mContext);

			point.setBackgroundResource(R.drawable.dot_normal);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
																				DensityUtils.dp2px(mContext, 6),
																				DensityUtils.dp2px(mContext, 6));

			if (i != 0)
			{
				params.leftMargin = DensityUtils.dp2px(mContext, 6);
			}
			else
			{
				point.setBackgroundResource(R.drawable.dot_focus);
				mTvTitle.setText(mPicDatas.get(i).title);
			}
			mPointContainer.addView(point, params);
		}

		// 给viewpager设置监听
		mViewPager.setOnPageChangeListener(this);

		// if (task == null)
		// {
		// task = new Runnable() {
		// @Override
		// public void run()
		// {
		// // 设置viewpager选中下一个，如果已经是最后一个了，选中第0个
		// int item = mViewPager.getCurrentItem();
		// if (item == mViewPager.getAdapter().getCount() - 1)
		// {
		// // 是最后一个
		// item = 0;
		// }
		// else
		// {
		// item++;
		// }
		//
		// mViewPager.setCurrentItem(item);
		//
		// // 再次执行
		// handler.postDelayed(this, 2000);
		// }
		// };
		// }
		//
		// // 执行前先从消息队列中移除
		// handler.removeCallbacks(task);
		// handler.postDelayed(task, 2000);

		// 开启轮播任务
		if (mSwitchTask == null)
		{
			mSwitchTask = new SwitchTask();
		}
		mSwitchTask.start();

		// 设置viewpager的touchlistener
		mViewPager.setOnTouchListener(new PicTouchListener());

		// 获得listview的数据
		mNewsDatas = bean.data.news;

		// 给listView加载数据
		mNewsAdapter = new NewsListAdapter();
		mListView.setAdapter(mNewsAdapter);
	}

	private class NewsListAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (mNewsDatas != null) { return mNewsDatas.size(); }
			return 0;
		}

		@Override
		public Object getItem(int position)
		{
			if (mNewsDatas != null) { return mNewsDatas.get(position); }
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder = null;
			if (convertView == null)
			{
				// 没有复用
				// 1.加载xml
				convertView = View.inflate(mContext, R.layout.item_news, null);
				// 2.新建holder
				holder = new ViewHolder();
				// 3.设置标记
				convertView.setTag(holder);
				// 4. 给holder找view
				holder.ivPic = (ImageView) convertView.findViewById(R.id.item_news_iv_pic);
				holder.tvTitle = (TextView) convertView.findViewById(R.id.item_news_tv_title);
				holder.tvDate = (TextView) convertView.findViewById(R.id.item_news_tv_date);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			// 给holder的view设置数据
			NewsBean bean = mNewsDatas.get(position);

			holder.tvTitle.setText(bean.title);// 设置title
			holder.tvDate.setText(bean.pubdate);// 设置日期
			// 设置默认图片
			holder.ivPic.setImageResource(R.drawable.pic_item_list_default);
			// 去网络加载
			mBitmapUtils.display(holder.ivPic, bean.listimage);

			boolean flag = PreferenceUtils.getBoolean(mContext, bean.id + "");

			// 设置文本颜色
			holder.tvTitle.setTextColor(flag ? Color.GRAY : Color.BLACK);

			return convertView;
		}
	}

	private class ViewHolder
	{
		ImageView	ivPic;
		TextView	tvTitle;
		TextView	tvDate;
	}

	private class PicTouchListener implements OnTouchListener
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			// 只想知道用户是按下还是松开
			switch (event.getAction())
			{
				case MotionEvent.ACTION_DOWN:
					// 按下,停止轮播图
					Log.d(TAG, "Down停止轮播图");
					mSwitchTask.stop();
					break;
				case MotionEvent.ACTION_UP:
					// 松开,开启轮播
					Log.d(TAG, "UP开启轮播图");
					mSwitchTask.start();
					break;
				case MotionEvent.ACTION_CANCEL:
					Log.d(TAG, "CANCEL");
					// mSwitchTask.start();
					// 当用户松开时，要去开启轮播
					// 外侧的viewpager固定时，要去开启轮播
					break;
				default:
					break;
			}

			return false;
		}
	}

	private class SwitchTask extends Handler implements Runnable
	{
		@Override
		public void run()
		{
			// 设置viewpager选中下一个，如果已经是最后一个了，选中第0个
			int item = mViewPager.getCurrentItem();
			if (item == mViewPager.getAdapter().getCount() - 1)
			{
				// 是最后一个
				item = 0;
			}
			else
			{
				item++;
			}

			mViewPager.setCurrentItem(item);

			// 再次执行
			postDelayed(this, 2000);
		}

		public void start()
		{
			removeCallbacks(this);
			postDelayed(this, 2000);
		}

		public void stop()
		{
			removeCallbacks(this);
		}
	}

	private class PicAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mPicDatas != null) { return mPicDatas.size(); }
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
			ImageView iv = new ImageView(mContext);
			iv.setScaleType(ScaleType.FIT_XY);

			TopNewsBean bean = mPicDatas.get(position);
			String url = bean.topimage;

			// 获取网络的图片
			// iv.setImageResource(resId);// 设置图片
			mBitmapUtils.display(iv, url);

			container.addView(iv);

			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
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
		// 选中时

		// 改变点和title
		int count = mPointContainer.getChildCount();
		for (int i = 0; i < count; i++)
		{
			View child = mPointContainer.getChildAt(i);

			child.setBackgroundResource(i == position
														? R.drawable.dot_focus
														: R.drawable.dot_normal);
		}

		mTvTitle.setText(mPicDatas.get(position).title);
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onIdle()
	{
		Log.d(TAG, mData.title + " : 接收到外侧viewpager闲置");

		mSwitchTask.start();
	}

	@Override
	public void onRefreshing()
	{
		// 模拟延时
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run()
			{
				// 重新的去网络获取数据
				// 访问网络获得数据
				final String url = Constants.SERVER_URL + mData.url;

				HttpUtils utils = new HttpUtils();
				utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException e, String msg)
					{
						Log.d(TAG, "下拉刷新访问数据失败");

						// 加载数据完成
						mListView.setRefreshFinish();
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo)
					{
						Log.d(TAG, "下拉刷新访问数据成功");

						int statusCode = responseInfo.statusCode;
						// if (200 == statusCode)
						// {
						String result = responseInfo.result;

						// 存储缓存
						PreferenceUtils.putString(mContext, url, result);

						processData(result);
						// }

						// 让刷新的listView状态重置
						mListView.setRefreshFinish();
					}
				});
			}
		}, 2000);

	}

	@Override
	public void onLoadMore()
	{
		// 加载更多的数据

		// 模拟数据延时
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run()
			{
				if (TextUtils.isEmpty(mMoreUrl))
				{
					// 没有更多数据
					Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();

					// 加载数据完成
					mListView.setRefreshFinish();
					return;
				}

				String url = Constants.SERVER_URL + mMoreUrl;
				HttpUtils utils = new HttpUtils();
				utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException e, String msg)
					{
						// 加载数据完成
						mListView.setRefreshFinish();
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo)
					{
						String json = responseInfo.result;

						Gson gson = new Gson();
						NewsDataBean bean = gson.fromJson(json, NewsDataBean.class);

						// 获得下一个页面加载更多的url
						mMoreUrl = bean.data.more;

						// 获得当前加载更多的数据
						List<NewsBean> more = bean.data.news;
						// 添加数据
						mNewsDatas.addAll(more);
						// UI更新
						mNewsAdapter.notifyDataSetChanged();

						// 通知加载更多完成
						mListView.setRefreshFinish();
					}
				});
			}
		}, 2000);

	}

}
