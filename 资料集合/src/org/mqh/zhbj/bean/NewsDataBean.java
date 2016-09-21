package org.itheima15.zhbj.bean;

import java.util.List;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.bean
 * @类名: NewsDataBean
 * @作者: 肖琦
 * @创建时间: 2015-11-16 上午10:01:09
 * @描述: 新闻中心新闻菜单的viewpager中对应的数据
 * 
 * @更新时间: $Date: 2015-11-16 10:22:18 +0800 (Mon, 16 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 34 $
 * @更新内容: TODO:
 */
public class NewsDataBean
{
	public DataBean	data;
	public int		retcode;

	public class DataBean
	{
		public String				countcommenturl;
		public String				more;
		public List<NewsBean>		news;
		public String				title;
		public List<TopicBean>		topic;
		public List<TopNewsBean>	topnews;
	}

	public class NewsBean
	{
		public boolean	comment;
		public String	commentlist;
		public String	commenturl;
		public long		id;
		public String	listimage;
		public String	pubdate;
		public String	title;
		public String	type;
		public String	url;
	}

	public class TopicBean
	{
		public String	description;
		public long		id;
		public String	listimage;
		public int		sort;
		public String	title;
		public String	url;
	}

	public class TopNewsBean
	{
		public boolean	comment;
		public String	commentlist;
		public String	commenturl;
		public long		id;
		public String	pubdate;
		public String	title;
		public String	topimage;
		public String	type;
		public String	url;
	}

}
