package org.itheima15.zhbj.fragment;

import java.util.List;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.activity.MainUI;
import org.itheima15.zhbj.bean.NewsCenterBean.NewsCenterMenuBean;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.fragment
 * @类名: MenuFragment
 * @作者: 肖琦
 * @创建时间: 2015-11-13 下午4:01:52
 * @描述: 左侧菜单对应的fragment
 * 
 * @更新时间: $Date: 2015-11-15 15:52:51 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 25 $
 * @更新内容: TODO:
 */
public class MenuFragment extends Fragment implements OnItemClickListener
{
	private ListView					mListView;
	private List<NewsCenterMenuBean>	mDatas;

	private int							mCurrentPosition;	// 当前选中的item的position
	private MenuAdapter					mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = View.inflate(getActivity(), R.layout.menu, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		mListView = (ListView) view.findViewById(R.id.menu_listview);

		// 设置listView的item点击事件
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		// 如果点击的是已经选中的，不执行操作
		if (mCurrentPosition == position) { return; }

		// 1. 菜单要关闭
		MainUI ui = (MainUI) getActivity();
		// 获得菜单的实例
		SlidingMenu slidingMenu = ui.getSlidingMenu();
		slidingMenu.toggle();

		// 2. 选中项
		mCurrentPosition = position;
		// ui改变
		mAdapter.notifyDataSetChanged();

		// 3. 内容区域要发生改变
		// 获得contentFragment的实例
		ContentFragment contentFragment = ui.getContentFragment();
		// 告知内容部分帮menu去选中对应的菜单
		contentFragment.switchMenu(position);
	}

	/**
	 * 给菜单设置数据
	 * 
	 * @param datas
	 */
	public void setData(List<NewsCenterMenuBean> datas)
	{
		mCurrentPosition = 0;

		this.mDatas = datas;

		mAdapter = new MenuAdapter();
		mListView.setAdapter(mAdapter);// adapter --->list
	}

	private class MenuAdapter extends BaseAdapter
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
				// 没有复用
				// 1.加载xml
				convertView = View.inflate(getActivity(), R.layout.item_menu, null);
				// 2.创建holder
				holder = new ViewHolder();
				// 3.设置标记
				convertView.setTag(holder);
				// 4.给holder找view
				holder.tvTitle = (TextView) convertView.findViewById(R.id.item_tv_title);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}

			// 给holder中的view设置数据
			NewsCenterMenuBean bean = mDatas.get(position);
			holder.tvTitle.setText(bean.title);

			// if (mCurrentPosition == position)
			// {
			// // 选中的
			// holder.tvTitle.setEnabled(true);
			// }
			// else
			// {
			// // 没有选中
			// holder.tvTitle.setEnabled(false);
			// }

			holder.tvTitle.setEnabled(mCurrentPosition == position);

			return convertView;
		}
	}

	private class ViewHolder
	{
		TextView	tvTitle;
	}
}
