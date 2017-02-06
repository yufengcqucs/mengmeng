package com.mengmeng.login;

import com.stone.card.MainActivity;
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

public class InitNewPasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
		// R.layout.titlebar);
		setContentView(R.layout.init_newpassword);

		// 进入主页
		Button resetPasswordFinishBtn = (Button) findViewById(R.id.initNewPassword_finish);
		resetPasswordFinishBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InitNewPasswordActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}

}
