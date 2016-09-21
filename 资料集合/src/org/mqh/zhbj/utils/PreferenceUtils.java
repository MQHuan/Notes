package org.itheima15.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.utils
 * @类名: PreferenceUtils
 * @作者: 肖琦
 * @创建时间: 2015-11-13 上午10:20:27
 * @描述: 存储全局的key-value
 * 
 * @更新时间: $Date: 2015-11-16 09:17:57 +0800 (Mon, 16 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 32 $
 * @更新内容: TODO:
 */
public class PreferenceUtils
{

	private static SharedPreferences	mSp;

	private static SharedPreferences getSp(Context context)
	{
		if (mSp == null)
		{
			mSp = context.getSharedPreferences("zhbj", Context.MODE_PRIVATE);
		}
		return mSp;
	}

	/**
	 * 获得boolean的数据,没有时为false
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Context context, String key)
	{
		return getBoolean(context, key, false);
	}

	/**
	 * 获得boolean的数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 *            :如果没有返回的值
	 * @return
	 */
	public static boolean getBoolean(Context context, String key,
										boolean defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 设置boolean值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putBoolean(Context context, String key, boolean value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 获得String的数据,没有时为null
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getString(Context context, String key)
	{
		return getString(context, key, null);
	}

	/**
	 * 获得String的数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 *            :如果没有返回的值
	 * @return
	 */
	public static String getString(Context context, String key, String defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getString(key, defValue);
	}

	/**
	 * 设置String值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putString(Context context, String key, String value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 获得int的数据,没有时为-1
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static int getInt(Context context, String key)
	{
		return getInt(context, key, -1);
	}

	/**
	 * 获得int的数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 *            :如果没有返回的值
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getInt(key, defValue);
	}

	/**
	 * 设置int值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putInt(Context context, String key, int value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 获得long的数据,没有时为-1
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static long getLong(Context context, String key)
	{
		return getLong(context, key, -1);
	}

	/**
	 * 获得long的数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 *            :如果没有返回的值
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getLong(key, defValue);
	}

	/**
	 * 设置long值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putLong(Context context, String key, long value)
	{
		SharedPreferences sp = getSp(context);
		Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}
}
