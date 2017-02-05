package com.ifriend.listener;

import com.stone.card.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * 用户自定义信息
 * 
 * @author yufengcqucs
 *
 */
public class CustomInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custominfo_main);

		Button modifyBtn = (Button) findViewById(R.id.custominfo_editinfo);
		modifyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CustomInfoActivity.this, ModifyCustomInfoActivity.class);
				startActivity(intent);
			}
		});
	}

}
