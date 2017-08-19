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
 * @类名: SubjectMenuController
 * @作者: 肖琦
 * @创建时间: 2015-11-15 下午3:23:14
 * @描述: 专题菜单对应的controller
 * 
 * @更新时间: $Date: 2015-11-15 15:52:51 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 25 $
 * @更新内容: TODO:
 */
public class SubjectMenuController extends BaseController
{

	public SubjectMenuController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initView(Context context)
	{
		TextView tv = new TextView(context);
		tv.setText("专题 菜单");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(20);// px
		tv.setTextColor(Color.RED);

		return tv;
	}

}
