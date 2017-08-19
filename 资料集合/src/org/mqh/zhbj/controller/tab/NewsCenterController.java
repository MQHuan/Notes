package org.itheima15.zhbj.controller.tab;

import java.util.ArrayList;
import java.util.List;

import org.itheima15.zhbj.activity.MainUI;
import org.itheima15.zhbj.bean.NewsCenterBean;
import org.itheima15.zhbj.bean.NewsCenterBean.NewsCenterMenuBean;
import org.itheima15.zhbj.controller.BaseController;
import org.itheima15.zhbj.controller.TabController;
import org.itheima15.zhbj.controller.menu.InteractMenuController;
import org.itheima15.zhbj.controller.menu.NewsMenuController;
import org.itheima15.zhbj.controller.menu.PicMenuController;
import org.itheima15.zhbj.controller.menu.SubjectMenuController;
import org.itheima15.zhbj.fragment.MenuFragment;
import org.itheima15.zhbj.utils.Constants;
import org.itheima15.zhbj.utils.PreferenceUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller.tab
 * @类名: NewsCenterController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 上午9:14:37
 * @描述: 新闻中心对应的控制器
 * 
 * @更新时间: $Date: 2015-11-19 10:01:54 +0800 (Thu, 19 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 49 $
 * @更新内容: TODO:
 */
public class NewsCenterController extends TabController
{

	protected static final String		TAG	= "NewsCenterController";

	private List<BaseController>		mMenuControllers;				// 菜单对应的控制器

	private FrameLayout					mContainer;

	private List<NewsCenterMenuBean>	mMenuDatas;

	public NewsCenterController(Context context) {
		super(context);
	}

	@Override
	protected View initContentView(Context context)
	{
		// TextView tv = new TextView(context);
		// tv.setText("新闻中心");
		// tv.setGravity(Gravity.CENTER);
		// tv.setTextSize(20);// px
		// tv.setTextColor(Color.RED);
		//
		// return tv;

		mContainer = new FrameLayout(context);
		return mContainer;
	}

	@Override
	public void initData()
	{
		// 设置标题
		mTvTitle.setText("新闻中心");
		// 隐藏或显示menu按钮
		mIvMenu.setVisibility(View.VISIBLE);

		// 去服务器加载数据
		HttpUtils utils = new HttpUtils();
		// 请求服务器获取数据
		// request:
		// 1. url
		// 2. method:
		// 3. headers
		// 4. 内容
		final String url = Constants.NEWS_CENTER_URL;

		long delay = 5 * 60 * 1000;
		// 去取缓存
		long time = PreferenceUtils.getLong(mContext, url + "-timestamp");
		String json = PreferenceUtils.getString(mContext, url);
		if (!TextUtils.isEmpty(json) && time != -1 && (time + delay > System.currentTimeMillis()))
		{

			// 数据没有过期
			// 存在缓存数据
			// 展现缓存数据
			Log.d(TAG, "读取缓存数据,不再加载网络数据了");
			processData(json);
		}
		else
		{
			// 去网络获取最新的数据
			if (!TextUtils.isEmpty(json))
			{
				// 先展示缓存
				Log.d(TAG, "读取缓存数据,继续加载网络数据");
				processData(json);
			}

			// RequestParams params = new RequestParams();
			// params.addHeader("", "");//添加自定义的头
			//
			// //1. get请求
			// params.addQueryStringParameter("", "");//添加到url的后面
			//
			// //2. post请求
			// params.addBodyParameter("", "");//key-value
			// params.addBodyParameter(key, file);// key-file：文件上传

			utils.send(HttpMethod.GET, url, null, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException e, String msg)
				{
					// 主线程中执行的
					// 失败
					// 访问服务器失败，访问接口失败
					// 1.手机无法联网
					// 2.服务器宕机
					e.printStackTrace();

					// TODO: 用户提示UI
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo)
				{
					// 主线程中执行的
					// 成功
					// 1.连接服务器成功
					// 2. 接口是否成功?????

					int statusCode = responseInfo.statusCode;
					Log.d(TAG, "status : " + statusCode);
					if (200 == statusCode)
					{
						// 访问服务器成功

						// 获取头
						// Header[] allHeaders = responseInfo.getAllHeaders();
						// for (Header header : allHeaders)
						// {
						// header.getName() ;//aaaaa:bbbbb
						// }

						// 内容结果
						String result = responseInfo.result;

						Log.d(TAG, "result : " + result);

						Log.d(TAG, "存储缓存数据");
						// 存缓存-->持久化的存储
						PreferenceUtils.putString(mContext, url, result);
						// 缓存时间戳
						PreferenceUtils.putLong(mContext, url + "-timestamp", System.currentTimeMillis());

						// 解析结果数据，展示到页面上
						processData(result);

					}
				}
			});
		}
	}

	private void processData(String json)
	{
		// json解析
		Gson gson = new Gson();

		// 创造javaBean
		NewsCenterBean bean = gson.fromJson(json, NewsCenterBean.class);

		// 校验
		Log.d(TAG, "" + bean.data.get(0).children.get(0).title);

		// 左侧菜单对应的数据
		mMenuDatas = bean.data;

		// 获得MainUI
		MainUI ui = (MainUI) mContext;

		// 给左侧的菜单设置
		MenuFragment fragment = ui.getMenuFragment();// 获得菜单fragment的实例
		fragment.setData(mMenuDatas);

		// 加载内容区域的controller
		mMenuControllers = new ArrayList<BaseController>();
		for (int i = 0; i < mMenuDatas.size(); i++)
		{
			// 获得的新闻菜单数据
			NewsCenterMenuBean data = mMenuDatas.get(i);
			switch (data.type)
			{
				case 1:
					// 新闻菜单
					mMenuControllers.add(new NewsMenuController(mContext, data.children));
					break;
				case 10:
					// 专题菜单
					mMenuControllers.add(new SubjectMenuController(mContext));
					break;
				case 2:
					// 组图菜单
					mMenuControllers.add(new PicMenuController(mContext));
					break;
				case 3:
					// 互动菜单
					mMenuControllers.add(new InteractMenuController(mContext));
					break;
				default:
					break;
			}
		}

		// 显示默认0
		switchMenu(0);
	}

	@Override
	public void switchMenu(int position)
	{
		// 清空容器
		mContainer.removeAllViews();

		final BaseController controller = mMenuControllers.get(position);

		// 获得视图
		View rootView = controller.getRootView();
		mContainer.addView(rootView);

		// 加载数据
		controller.initData();

		NewsCenterMenuBean bean = mMenuDatas.get(position);
		// 设置title文本
		mTvTitle.setText(bean.title);

		// 切换菜单时，如果切换到的是组图页面,显示右边的listorgrid图标
		if (controller instanceof PicMenuController)
		{
			mIvListOrGrid.setVisibility(View.VISIBLE);
			mIvListOrGrid.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v)
				{
					// 切换内容显示
					((PicMenuController) controller).switchListOrGrid(mIvListOrGrid);
				}
			});
		}
		else
		{
			mIvListOrGrid.setVisibility(View.GONE);
		}
	}

}
