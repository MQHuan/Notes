package org.itheima15.zhbj.controller;

import android.content.Context;
import android.view.View;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller
 * @类名: BaseController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 上午9:04:08
 * @描述: 控制器的基类
 * 
 * @更新时间: $Date: 2015-11-15 09:31:11 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 19 $
 * @更新内容: TODO:
 */
public abstract class BaseController
{

	// 1.提供view
	// 2.提供数据

	protected View		mRootView;	// 控制器的根布局
	protected Context	mContext;

	public BaseController(Context context) {
		this.mContext = context;
		// 初始化根布局
		mRootView = initView(context);
	}

	public View getRootView()
	{
		return mRootView;
	}

	// xxxxx() {
	//
	// //数据库，网络，本地文件
	// data...
	// view.setData(data);
	// }

	public void initData()
	{
		// controller自己去实现自己加载数据的方式
	}

	protected abstract View initView(Context context);
}
