package com.ifriend.listener;

import com.stone.card.R;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 鲜花管理
 * 
 * @author yufengcqucs
 *
 */
public class FlowerManager extends Activity {

	private String impagePath1[] = { "assets://1.jpg", "assets://2.jpg", "assets://3.jpg", };

	private String impagePath2[] = { "assets://4.jpg", "assets://5.jpg", "assets://6.jpg", };

	private String impagePath3[] = { "assets://7.jpg", "assets://8.jpg", "assets://9.jpg", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.flowermanager);

		ViewGroup flowerManager = (ViewGroup) findViewById(R.id.flowermanager_line1);
		initFlowerKind(flowerManager, impagePath1, true);

		initFlowerKind((ViewGroup) findViewById(R.id.flowermanager_line2), impagePath2, true);

		initFlowerKind((ViewGroup) findViewById(R.id.flowermanager_line3), impagePath3, false);
	}

	private void initFlowerKind(ViewGroup viewGroup, String[] imagePath, boolean disabled) {
		int flowerKind = 3;

		ImageView[] imageViews = new ImageView[flowerKind];
		for (int i = 0; i < imageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			params.weight = 1;
			params.gravity = Gravity.CENTER;
			imageView.setLayoutParams(params);
			imageViews[i] = imageView;
			if (!disabled) {
				disableImage(imageView);
			}
			imageView.setImageResource(R.drawable.flower1);

			viewGroup.addView(imageView);
		}
	}

	private void disableImage(ImageView imageView) {
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0); // 设置饱和度
		ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
		imageView.setColorFilter(grayColorFilter);
	}

}
