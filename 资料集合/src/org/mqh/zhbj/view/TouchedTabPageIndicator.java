package org.itheima15.zhbj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.viewpagerindicator.TabPageIndicator;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.view
 * @类名: TouchedTabPageIndicator
 * @作者: 肖琦
 * @创建时间: 2015-11-16 上午11:17:52
 * @描述: TODO:
 * 
 * @更新时间: $Date: 2015-11-16 11:30:13 +0800 (Mon, 16 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 37 $
 * @更新内容: TODO:
 */
public class TouchedTabPageIndicator extends TabPageIndicator
{

	public TouchedTabPageIndicator(Context context) {
		this(context, null);
	}

	public TouchedTabPageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		// 请求父容器不要拦截touch
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(ev);
	}
}
