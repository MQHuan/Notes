package org.itheima15.zhbj.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.utils
 * @类名: DensityUtils
 * @作者: 肖琦
 * @创建时间: 2015-11-13 上午11:29:42
 * @描述: TODO:
 * 
 * @更新时间: $Date: 2015-11-13 11:35:15 +0800 (Fri, 13 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 11 $
 * @更新内容: TODO:
 */
public class DensityUtils
{

	public static int px2dp(Context context, int px)
	{
		// dp = px * 160 / dpi
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int dpi = metrics.densityDpi;
		return (int) (px * 160f / dpi + 0.5f);
	}

	public static int dp2px(Context context, int dp)
	{
		// px = dp * (dpi / 160)
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int dpi = metrics.densityDpi;
		return (int) (dp * (dpi / 160f) + 0.5f);
	}
}
