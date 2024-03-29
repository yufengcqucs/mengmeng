package com.mengmeng.login;

import org.xutils.x;
import org.xutils.view.annotation.ContentView;

import com.stone.card.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

@ContentView(R.layout.start_main)
public class StartActionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		x.view().inject(this);
		//setContentView(R.layout.start_main);

		// 登录按钮
		Button loginBtn = (Button) findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActionActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});

		Button registerBtn = (Button) findViewById(R.id.register_btn);
		registerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActionActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
	}

}
