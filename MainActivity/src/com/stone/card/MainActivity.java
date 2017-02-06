package com.stone.card;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.mengmeng.common.RoundImageView;
import com.mengmeng.userinfo.CustomInfoActivity;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initImageLoader();
		if (savedInstanceState == null) {

			CardFragment cardFragment = new CardFragment();
			cardFragment.setMainPageSettingMenu(initSettingMenu());
			getSupportFragmentManager().beginTransaction().add(R.id.container, cardFragment).commitAllowingStateLoss();
		}
	}

	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).memoryCacheExtraOptions(480, 800)
				// default = device screen dimensions
				.threadPoolSize(3)
				// default
				.threadPriority(Thread.NORM_PRIORITY - 1)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
				.discCacheSize(50 * 1024 * 1024) // 缓冲大小
				.discCacheFileCount(100) // 缓冲文件数目
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(this)) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.writeDebugLogs().build();

		// 2.单例ImageLoader类的初始化
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
	}

	private SlidingMenu initSettingMenu() {
		SlidingMenu settingMenu = new SlidingMenu(this);
		settingMenu.setMode(SlidingMenu.LEFT);

		settingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		settingMenu.setShadowWidthRes(R.dimen.shadow_width);
		settingMenu.setShadowDrawable(R.drawable.gray);

		settingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		settingMenu.setFadeDegree(0.35f);

		settingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		settingMenu.setMenu(R.layout.left_setting_menu);

		RoundImageView settingBtn = (RoundImageView) settingMenu.findViewById(R.id.setting_person_icon_btn);
		settingBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CustomInfoActivity.class);
				startActivity(intent);
			}
		});

		return settingMenu;
	}

}
