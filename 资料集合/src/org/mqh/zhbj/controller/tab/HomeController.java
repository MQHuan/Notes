package org.itheima15.zhbj.controller.tab;

import org.itheima15.zhbj.controller.TabController;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller.tab
 * @类名: HomeController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 上午9:14:37
 * @描述: 首页对应的控制器
 * 
 * @更新时间: $Date: 2015-11-15 09:53:31 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 20 $
 * @更新内容: TODO:
 */
public class HomeController extends TabController
{

	public HomeController(Context context) {
		super(context);
	}

	@Override
	protected View initContentView(Context context)
	{
		TextView tv = new TextView(context);
		tv.setText("首页");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(20);// px
		tv.setTextColor(Color.RED);

		return tv;
	}

	@Override
	public void initData()
	{
		// 设置标题
		mTvTitle.setText("首页");
		// 隐藏或显示menu按钮
		mIvMenu.setVisibility(View.GONE);

	}

}
