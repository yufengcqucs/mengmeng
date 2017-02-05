/**
 * 
 */
package com.stone.card;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * @author yufengcqucs
 *
 */
public class FindFriendActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.findfriend);

		final SlidingMenu leftMenu = new SlidingMenu(this);
		leftMenu.setMode(SlidingMenu.LEFT);
		// ���ô�����Ļ��ģʽ
		leftMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		leftMenu.setShadowWidthRes(R.dimen.shadow_width);
		leftMenu.setShadowDrawable(R.drawable.gray);

		// ���û����˵���ͼ�Ŀ��
		leftMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// ���ý��뽥��Ч����ֵ
		leftMenu.setFadeDegree(0.35f);
		/**
		 * SLIDING_WINDOW will include the Title/ActionBar in the content
		 * section of the SlidingMenu, while SLIDING_CONTENT does not.
		 */
		leftMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// Ϊ�໬�˵����ò���
		leftMenu.setMenu(R.layout.left_setting_menu);
		
		Button showLeftMenuBtn = (Button)findViewById(R.id.showLeftMenu_btn);
		showLeftMenuBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				leftMenu.showMenu(true);
			}
		});
		
		Button showRightMenuBtn = (Button)findViewById(R.id.showRightMenu_btn);
		showRightMenuBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//rightMenu.showMenu(true);
			}
		});
	}
}
