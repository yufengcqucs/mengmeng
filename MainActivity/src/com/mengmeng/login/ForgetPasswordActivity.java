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

public class ForgetPasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forgetpassword);

		// 完成验证进入重置密码
		Button resetPasswordFinishBtn = (Button) findViewById(R.id.forgetpassword_nextstepbtn);
		resetPasswordFinishBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ForgetPasswordActivity.this, InitNewPasswordActivity.class);
				startActivity(intent);
			}
		});

		// 获取验证码
		Button getCheckNumberBtn = (Button) findViewById(R.id.forgetpassword_getchecknumberbtn);
		getCheckNumberBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(LoginActivity.this,
				// ForgetPasswordActivity.class);
				// startActivity(intent);
			}
		});
	}

}
