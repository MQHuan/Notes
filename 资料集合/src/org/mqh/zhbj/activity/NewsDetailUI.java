package org.itheima15.zhbj.activity;

import org.itheima15.zhbj.R;
import org.itheima15.zhbj.utils.Constants;
import org.itheima15.zhbj.utils.PreferenceUtils;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.TextSize;
import android.widget.ProgressBar;

/**
 * @项目名: Zhbj15
 * @包名: org.itheima15.zhbj.activity
 * @类名: NewsDetailUI
 * @作者: 肖琦
 * @创建时间: 2015-11-18 下午3:25:19
 * @描述: 新闻详情页面
 * 
 * @更新时间: $Date: 2015-11-19 08:54:41 +0800 (Thu, 19 Nov 2015) $
 * @更新人: $Author: xq $
 * @版本: $Rev: 47 $
 * @更新内容: TODO:
 */
public class NewsDetailUI extends Activity
{
	public static final String	KEY_URL			= "key_url";
	@ViewInject(R.id.news_detail_webview)
	private WebView				mWebView;

	@ViewInject(R.id.news_detail_loading)
	private ProgressBar			mProgressBar;
	private int					mCheckedItem	= 2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_news_detail);

		// 注入
		ViewUtils.inject(this);

		// 加载数据
		loadData();
	}

	private void loadData()
	{
		// 读取字体数据
		mCheckedItem = PreferenceUtils.getInt(this, Constants.TEXT_SIZE, 2);
		initTextSize();

		String url = getIntent().getStringExtra(KEY_URL);
		// String url = "http://www.itheima.com";

		// mWebview的设置
		WebSettings settings = mWebView.getSettings();
		// 设置js可见
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);// 设置有放大和缩小的控件
		settings.setUseWideViewPort(true);// 双击缩放

		// 设置webView监听
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{
				mProgressBar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url)
			{
				mProgressBar.setVisibility(View.GONE);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				if (url.startsWith("http://dvd.boxuegu.com"))
				{
					mWebView.loadUrl("http://www.baidu.com");
					return true;
				}

				return super.shouldOverrideUrlLoading(view, url);
			}
		});

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress)
			{
				mProgressBar.setProgress(newProgress);
			}
		});

		// 加载url地址
		mWebView.loadUrl(url);
	}

	@OnClick(R.id.news_detail_iv_back)
	public void clickBack(View view)
	{
		finish();
	}

	@OnClick(R.id.news_detail_iv_textsize)
	public void clickTextSize(View view)
	{
		// 弹出单选对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("设置字体大小");

		CharSequence[] items = new CharSequence[] { "超大字体", "大字体", "正常字体", "小字体", "超小字体" };
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// 选中的index ,用来记录选中的是那一个

				mCheckedItem = which;
			}
		};
		builder.setSingleChoiceItems(items, mCheckedItem, listener);

		builder.setNegativeButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// 点击确定时修改字体
				initTextSize();

				// 存储到Preference中
				PreferenceUtils.putInt(getApplicationContext(), Constants.TEXT_SIZE, mCheckedItem);
			}
		});
		builder.show();
	}

	@OnClick(R.id.news_detail_iv_share)
	public void clickShare(View view)
	{
	}

	private void initTextSize()
	{
		WebSettings settings = mWebView.getSettings();

		TextSize size = null;
		switch (mCheckedItem)
		{
			case 0:
				size = TextSize.LARGEST;
				break;
			case 1:
				size = TextSize.LARGER;
				break;
			case 2:
				size = TextSize.NORMAL;
				break;
			case 3:
				size = TextSize.SMALLER;
				break;
			case 4:
				size = TextSize.SMALLEST;
				break;
			default:
				break;
		}
		settings.setTextSize(size);
	}
}
