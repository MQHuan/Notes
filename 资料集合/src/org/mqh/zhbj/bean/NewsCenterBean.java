package org.itheima15.zhbj.bean;

import java.util.List;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.bean
 * @类名: NewsCenterBean
 * @作者: 肖琦
 * @创建时间: 2015-11-15 上午11:28:23
 * @描述: 新闻中心对应的数据bean
 * 
 * @更新时间: $Date: 2015-11-15 11:37:56 +0800 (Sun, 15 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 22 $
 * @更新内容: TODO:
 */
public class NewsCenterBean
{

	public List<NewsCenterMenuBean>	data;
	public List<Long>				extend;
	public int						retcode;

	public class NewsCenterMenuBean
	{
		public List<NewsListBean>	children;
		public long					id;
		public String				title;
		public int					type;

		public String				url;
		public String				url1;

		public String				dayurl;
		public String				excurl;
		public String				weekurl;
	}

	public class NewsListBean
	{
		public long		id;
		public String	title;
		public int		type;
		public String	url;
	}

}
