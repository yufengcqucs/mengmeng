package com.mengmeng.login;

import com.stone.card.R;
import com.stone.card.R.id;
import com.stone.card.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		// R.layout.titlebar);
		setContentView(R.layout.register);

		// 注册成功后跳转到主界面
		Button loginBtn = (Button) findViewById(R.id.register_registerbtn);
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(RegisterActivity.this, InitNewPasswordActivity.class);
				startActivity(intent);
			}
		});

	}

}
