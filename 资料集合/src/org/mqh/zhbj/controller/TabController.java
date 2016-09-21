package org.itheima15.zhbj.controller;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.activity.MainUI;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller
 * @类名: TabController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 上午9:39:26
 * @描述: 底部tab对应的controller基类
 * 
 * @更新时间: $Date: 2015-11-19 10:01:54 +0800 (Thu, 19 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 49 $
 * @更新内容: TODO:
 */
public abstract class TabController extends BaseController
{
	protected ImageView		mIvMenu;
	protected TextView		mTvTitle;
	protected FrameLayout	mContentContainer;
	protected ImageView		mIvListOrGrid;

	public TabController(Context context) {
		super(context);
	}

	@Override
	protected View initView(Context context)
	{
		View view = View.inflate(context, R.layout.tab_controller, null);

		mIvMenu = (ImageView) view.findViewById(R.id.tab_iv_menu);
		mTvTitle = (TextView) view.findViewById(R.id.tab_tv_title);
		mContentContainer = (FrameLayout) view.findViewById(R.id.tab_content_container);
		mIvListOrGrid = (ImageView) view.findViewById(R.id.tab_iv_listorgrid);

		// 添加内容的view
		mContentContainer.addView(initContentView(context));

		// 设置事件
		mIvMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// 打开菜单
				MainUI ui = (MainUI) mContext;
				ui.getSlidingMenu().toggle();
			}
		});

		return view;
	}

	/**
	 * 获得内容view的部分
	 * 
	 * @param context
	 * @return
	 */
	protected abstract View initContentView(Context context);

	/**
	 * 切换菜单的方法
	 * 
	 * @param position
	 */
	public void switchMenu(int position)
	{
		// 默认空实现，有的controller没有菜单。如果有菜单的话，实现切换的方法
	}

}
