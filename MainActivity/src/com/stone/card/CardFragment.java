package com.stone.card;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.stone.card.CardSlidePanel.CardSwitchListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 鍗＄墖Fragment
 *
 * @author xmuSistone
 */
@SuppressLint({ "HandlerLeak", "NewApi", "InflateParams" })
public class CardFragment extends Fragment {

	private CardSwitchListener cardSwitchListener;

	private SlidingMenu mainPageSettingMenu;

	private String imagePaths[] = { "assets://wall01.jpg", "assets://wall02.jpg", "assets://wall03.jpg",
			"assets://wall04.jpg", "assets://wall05.jpg", "assets://wall06.jpg", "assets://wall07.jpg",
			"assets://wall08.jpg", "assets://wall09.jpg", "assets://wall10.jpg", "assets://wall11.jpg",
			"assets://wall12.jpg", "assets://wall01.jpg", "assets://wall02.jpg", "assets://wall03.jpg",
			"assets://wall04.jpg", "assets://wall05.jpg", "assets://wall06.jpg", "assets://wall07.jpg",
			"assets://wall08.jpg", "assets://wall09.jpg", "assets://wall10.jpg", "assets://wall11.jpg",
			"assets://wall12.jpg" }; // 24涓浘鐗囪祫婧愬悕绉�

	private String names[] = { "郭富城", "刘德华", "张学友", "李连杰", "成龙", "谢霆锋", "李易峰", "霍建华", "胡歌", "曾志伟", "吴孟达", "梁朝伟", "周星驰",
			"赵本山", "郭德纲", "周润发", "邓超", "王祖蓝", "王宝强", "黄晓明", "张卫健", "徐峥", "李亚鹏", "郑伊健" }; // 24涓汉鍚�

	private List<CardDataItem> dataList = new ArrayList<CardDataItem>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.card_layout, null);
		initView(rootView);

		// 增加主页设置菜单
		initSettingMenu(rootView);
		return rootView;
	}

	private void initSettingMenu(View rootView) {
		Button settingMenuBtn = (Button) rootView.findViewById(R.id.mainpage_setting_btn);
		settingMenuBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mainPageSettingMenu.showMenu(true);
			}
		});
	}

	private void initView(View rootView) {
		CardSlidePanel slidePanel = (CardSlidePanel) rootView.findViewById(R.id.image_slide_panel);
		cardSwitchListener = new CardSwitchListener() {

			@Override
			public void onShow(int index) {
				Log.d("CardFragment", "姝ｅ湪鏄剧ず-" + dataList.get(index).userName);
			}

			@Override
			public void onCardVanish(int index, int type) {
				Log.d("CardFragment", "姝ｅ湪娑堝け-" + dataList.get(index).userName + " 娑堝けtype=" + type);
			}

			@Override
			public void onItemClick(View cardView, int index) {
				Log.d("CardFragment", "鍗＄墖鐐瑰嚮-" + dataList.get(index).userName);
			}
		};
		slidePanel.setCardSwitchListener(cardSwitchListener);

		prepareDataList();
		slidePanel.fillData(dataList);
	}

	private void prepareDataList() {
		int num = imagePaths.length;

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < num; i++) {
				CardDataItem dataItem = new CardDataItem();
				dataItem.userName = names[i];
				dataItem.imagePath = imagePaths[i];
				dataItem.likeNum = (int) (Math.random() * 10);
				dataItem.imageNum = (int) (Math.random() * 6);
				dataList.add(dataItem);
			}
		}
	}

	public void setMainPageSettingMenu(SlidingMenu mainPageSettingMenu) {
		this.mainPageSettingMenu = mainPageSettingMenu;
	}

}
