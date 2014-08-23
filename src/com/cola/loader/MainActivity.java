package com.cola.loader;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	//系统自带
	private ProgressDialog pd=null;
	//自定义
	private ColaLoder loader=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// 系统自带
		findViewById(R.id.btnBasic1).setOnClickListener(this);
		findViewById(R.id.btnBasic2).setOnClickListener(this);
		findViewById(R.id.btnBasic3).setOnClickListener(this);
		findViewById(R.id.btnBasic4).setOnClickListener(this);
		findViewById(R.id.btnBasic5).setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.btnBasic1:
			pd = ProgressDialog.show(MainActivity.this, "温馨提示","加载中", true, true);
			break;
		case R.id.btnBasic2:
			pd=ProgressDialog.show(MainActivity.this, "温馨提示","加载中-3s自动关闭", true, false);
			Thread thread= new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pd.dismiss();
				}
			};
			thread.start();
			break;
		case R.id.btnBasic3:
			loader=new ColaLoder(MainActivity.this);
			loader.start();
			break;
		case R.id.btnBasic4:
			loader=new ColaLoder(MainActivity.this,5000);
			loader.start();
			break;
		case R.id.btnBasic5:
			loader=new ColaLoder(MainActivity.this,"系统启动中   ");
			loader.start();
		default:
			break;
		}
	}

}
