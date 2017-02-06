package com.mengmeng.userinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.stone.card.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * 查看用户的详细信息
 * 
 * @author yufengcqucs
 *
 */
public class ShowDetailInfoActivity extends Activity {

	// viewpager
	private ViewPager view_pager;
	private LinearLayout ll_dotGroup;

	// 存储5个目录
	private int curIndex = 0;
	// 用来记录当前滚动的位置
	PicsAdapter picsAdapter;
	private int imgResIds[] = new int[] { R.drawable.wall01, R.drawable.wall02, R.drawable.wall03, R.drawable.wall04,
			R.drawable.wall05, R.drawable.wall01 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.showuserdetailinfo);
		setViewPager();
	}

	private void setViewPager() {
		view_pager = (ViewPager) findViewById(R.id.view_pager);
		ll_dotGroup = (LinearLayout) findViewById(R.id.dotgroup);

		picsAdapter = new PicsAdapter(); // 创建适配器
		picsAdapter.setData(imgResIds);
		view_pager.setAdapter(picsAdapter); // 设置适配器

		view_pager.setOnPageChangeListener(new MyPageChangeListener()); // 设置页面切换监听器

		initPoints(imgResIds.length); // 初始化图片小圆点
		// startAutoScroll(); // 开启自动播放
	}

	// 初始化图片轮播的小圆点和目录
	private void initPoints(int count) {
		for (int i = 0; i < count; i++) {

			ImageView iv = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
			params.setMargins(0, 0, 20, 0);
			iv.setLayoutParams(params);

			iv.setImageResource(R.drawable.dot1);

			ll_dotGroup.addView(iv);

		}
		((ImageView) ll_dotGroup.getChildAt(curIndex)).setImageResource(R.drawable.dot2);
	}

	// 自动播放
	private void startAutoScroll() {
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 每隔4秒钟切换一张图片
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 5, 4, TimeUnit.SECONDS);
	}

	// 切换图片任务
	private class ViewPagerTask implements Runnable {
		@Override
		public void run() {

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					int count = picsAdapter.getCount();
					view_pager.setCurrentItem((curIndex + 1) % count);
				}
			});
		}
	}

	// 定义ViewPager控件页面切换监听器
	class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			ImageView imageView1 = (ImageView) ll_dotGroup.getChildAt(position);
			ImageView imageView2 = (ImageView) ll_dotGroup.getChildAt(curIndex);
			if (imageView1 != null) {
				imageView1.setImageResource(R.drawable.dot1);
			}
			if (imageView2 != null) {
				imageView2.setImageResource(R.drawable.dot2);
			}
			curIndex = position;
		}

		boolean b = false;

		@Override
		public void onPageScrollStateChanged(int state) {
			// 这段代码可不加，主要功能是实现切换到末尾后返回到第一张
			switch (state) {
			case 1:// 手势滑动
				b = false;
				break;
			case 2:// 界面切换中
				b = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
					// 当前为最后一张，此时从右向左滑，则切换到第一张
				if (view_pager.getCurrentItem() == view_pager.getAdapter().getCount() - 1 && !b) {
					view_pager.setCurrentItem(0);
				}
				// 当前为第一张，此时从左向右滑，则切换到最后一张
				else if (view_pager.getCurrentItem() == 0 && !b) {
					view_pager.setCurrentItem(view_pager.getAdapter().getCount() - 1);
				}
				break;

			default:
				break;
			}
		}
	}

	// 定义ViewPager控件适配器
	class PicsAdapter extends PagerAdapter {

		private List<ImageView> views = new ArrayList<ImageView>();

		@Override
		public int getCount() {
			if (views == null) {
				return 0;
			}
			return views.size();
		}

		public void setData(int[] imgResIds) {
			for (int i = 0; i < imgResIds.length; i++) {
				ImageView iv = new ImageView(ShowDetailInfoActivity.this);
				ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.FIT_XY);
				// 设置ImageView的属性
				iv.setImageResource(imgResIds[i]);
				views.add(iv);
			}
		}

		public Object getItem(int position) {
			if (position < getCount())
				return views.get(position);
			return null;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {

			if (position < views.size())
				((ViewPager) container).removeView(views.get(position));
		}

		@Override
		public int getItemPosition(Object object) {
			return views.indexOf(object);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			if (position < views.size()) {
				final ImageView imageView = views.get(position);
				((ViewPager) container).addView(imageView);
				return views.get(position);
			}
			return null;
		}

	}

}
