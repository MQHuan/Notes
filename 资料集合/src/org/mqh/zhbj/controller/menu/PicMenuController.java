package org.itheima15.zhbj.controller.menu;

import java.util.List;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.bean.NewsDataBean;
import org.itheima15.zhbj.bean.NewsDataBean.NewsBean;
import org.itheima15.zhbj.controller.BaseController;
import org.itheima15.zhbj.utils.Constants;

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
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller.menu
 * @类名: PicMenuController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 下午3:22:38
 * @描述: 组图菜单对应的controller
 * 
 * @更新时间: $Date: 2015-11-19 10:01:54 +0800 (Thu, 19 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 49 $
 * @更新内容: TODO:
 */
public class PicMenuController extends BaseController
{
	@ViewInject(R.id.menu_pic_listview)
	private ListView		mListView;

	@ViewInject(R.id.menu_pic_gridview)
	private GridView		mGridView;

	private List<NewsBean>	mDatas;

	private BitmapUtils		mBitmapUtils;

	private boolean			isDisplayList	= true; // 用来标记是否显示的是list

	private PicAdapter		mAdapter;

	public PicMenuController(Context context) {
		super(context);

		mBitmapUtils = new BitmapUtils(context);
	}

	@Override
	protected View initView(Context context)
	{
		// TextView tv = new TextView(context);
		// tv.setText("组图菜单");
		// tv.setGravity(Gravity.CENTER);
		// tv.setTextSize(20);// px
		// tv.setTextColor(Color.RED);
		//
		// return tv;

		View view = View.inflate(context, R.layout.menu_pic, null);

		// 注入
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	public void initData()
	{
		// 加载数据
		// 去网络获取数据
		HttpUtils utils = new HttpUtils();
		String url = Constants.PHOTO_URL;
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException e, String msg)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				String result = responseInfo.result;

				processData(result);
			}
		});
	}

	private void processData(String json)
	{
		// 解析json数据的展示
		Gson gson = new Gson();
		NewsDataBean bean = gson.fromJson(json, NewsDataBean.class);
		mDatas = bean.data.news;

		// 给listview加载数
		mAdapter = new PicAdapter();
		mListView.setAdapter(mAdapter);

		// 给gridView加载数
		mGridView.setAdapter(mAdapter);
	}

	public void switchListOrGrid(ImageView iv)
	{
		// 如果当前是list显示，显示grid
		if (isDisplayList)
		{
			mGridView.setVisibility(View.VISIBLE);
			mListView.setVisibility(View.GONE);
		}
		else
		{
			mGridView.setVisibility(View.GONE);
			mListView.setVisibility(View.VISIBLE);
		}

		// 修改图标显示
		iv.setImageResource(isDisplayList ? R.drawable.icon_pic_list_type : R.drawable.icon_pic_grid_type);

		isDisplayList = !isDisplayList;
	}

	private class PicAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			if (mDatas != null) { return mDatas.size(); }
			return 0;
		}

		@Override
		public Object getItem(int position)
		{
			if (mDatas != null) { return mDatas.get(position); }
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
				convertView = View.inflate(mContext, R.layout.item_pic, null);
				holder = new ViewHolder();
				convertView.setTag(holder);
				// 给holder去找View
				holder.ivPic = (ImageView) convertView.findViewById(R.id.item_pic_iv_pic);
				holder.tvTitle = (TextView) convertView.findViewById(R.id.item_pic_tv_title);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			// 给holder中的view设置数
			NewsBean bean = mDatas.get(position);

			holder.tvTitle.setText(bean.title);
			holder.ivPic.setImageResource(R.drawable.pic_item_list_default);// 设置默认图片

			// 加载网络的图片
			String url = bean.listimage;
			mBitmapUtils.display(holder.ivPic, url);

			return convertView;
		}
	}

	private class ViewHolder
	{
		ImageView	ivPic;
		TextView	tvTitle;
	}

}
