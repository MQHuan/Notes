package org.itheima15.zhbj.controller.menu;

import org.itheima15.zhbj.controller.BaseController;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.controller.menu
 * @类名: InteractMenuController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 下午3:24:12
 * @描述: 互动菜单对应的controller
 * 
 * @更新时间: $Date: 2015-11-15 15:52:51 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 25 $
 * @更新内容: TODO:
 */
public class InteractMenuController extends BaseController
{

	public InteractMenuController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initView(Context context)
	{
		TextView tv = new TextView(context);
		tv.setText("互动菜单");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(20);// px
		tv.setTextColor(Color.RED);

		return tv;
	}

}
