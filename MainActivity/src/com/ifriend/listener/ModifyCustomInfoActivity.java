package com.ifriend.listener;

import java.io.File;
import java.util.Date;

import com.stone.card.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 用户自定义信息
 * 
 * @author yufengcqucs
 *
 */
public class ModifyCustomInfoActivity extends Activity {

	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果
	private File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.modifycustominfo_main);

		// 编缉相片或删除
		findViewById(R.id.mainuser_roundImageView).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final ImageView imageView = (ImageView) v;

				AlertDialog.Builder builder = new AlertDialog.Builder(ModifyCustomInfoActivity.this);
				builder.setNegativeButton("选择照片", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
						getAlbum.setType("image/*");
						startActivityForResult(getAlbum, PHOTO_REQUEST_GALLERY);
					}
				});
				builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// 移除照片
						imageView.setImageResource(R.drawable.addphoto);
					}
				});
				builder.show();
			}
		});

		//
		findViewById(R.id.customuserinfo_username)
				.setOnClickListener(new EditTextDialogListener(ModifyCustomInfoActivity.this));
		findViewById(R.id.customuserinfo_city)
				.setOnClickListener(new EditTextDialogListener(ModifyCustomInfoActivity.this));
		findViewById(R.id.customuserinfo_mylovesport)
				.setOnClickListener(new EditTextDialogListener(ModifyCustomInfoActivity.this));
		findViewById(R.id.customuserinfo_userlabel)
				.setOnClickListener(new EditTextDialogListener(ModifyCustomInfoActivity.this));
		findViewById(R.id.customuserinfo_work)
				.setOnClickListener(new EditTextDialogListener(ModifyCustomInfoActivity.this));
	}

	@SuppressLint("NewApi")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case PHOTO_REQUEST_TAKEPHOTO:// 当选择拍照时调用
			startPhotoZoom(Uri.fromFile(tempFile));
			break;
		case PHOTO_REQUEST_GALLERY:// 当选择从本地获取图片时
			// 做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
			if (data != null) {
				System.out.println("11================");
				startPhotoZoom(data.getData());
			} else {
				System.out.println("================");
			}
			break;
		case PHOTO_REQUEST_CUT:// 返回的结果
			if (data != null)
				// setPicToView(data);
				// sentPicToNext(data);
				addIcon(data);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@SuppressLint("NewApi")
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	private void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 2);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", 100);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		intent.putExtra("noFaceDetection", true);
		System.out.println("22================");
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	private void addIcon(Intent data) {

		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(this.getResources(), photo);
			((ImageView) findViewById(R.id.mainuser_roundImageView)).setImageDrawable(drawable);
		}

	}
}

class EditTextDialogListener implements OnClickListener {

	private Context context;

	public EditTextDialogListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		final LinearLayout layout = (LinearLayout) v;

		final EditText inputServer = new EditText(context);
		inputServer.setFocusable(true);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(inputServer);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String inputName = inputServer.getText().toString();
				TextView textView = (TextView) layout.getChildAt(1);
				textView.setText(inputName);
			}
		});
		builder.show();
	}

}
