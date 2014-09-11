package com.cola.loader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

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
		//示例按钮
		findViewById(R.id.btnBasic1).setOnClickListener(this);
		findViewById(R.id.btnBasic2).setOnClickListener(this);
		findViewById(R.id.btnBasic3).setOnClickListener(this);
		findViewById(R.id.btnBasic4).setOnClickListener(this);
		findViewById(R.id.btnBasic5).setOnClickListener(this);
		findViewById(R.id.btnBasic6).setOnClickListener(this);
		findViewById(R.id.btnBasic7).setOnClickListener(this);
		findViewById(R.id.btnBasic8).setOnClickListener(this);
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
			break;
		case R.id.btnBasic6:
			ColaProgress.show(MainActivity.this, "加载中", true, true,new OnCancelListener(){
				@Override
				public void onCancel(DialogInterface dialog) {
					Toast.makeText(MainActivity.this,"取消了进度条",Toast.LENGTH_SHORT).show();
				}
			});
			break;
		case R.id.btnBasic8:
			ColaProgress.show(MainActivity.this,"",true,true, null);
			break;
		case R.id.btnBasic7:
			//一个异步任务执行，显示进度信息
			new AsyncTask<Void, String, Void>() {
				ColaProgress cp=null;
				
				@Override
				protected void onPreExecute() {
					//实例化方便后面通过setMessage调用
					cp=ColaProgress.show(MainActivity.this,"下载开始!", true, false,new OnCancelListener(){
						@Override
						public void onCancel(DialogInterface dialog) {
							Toast.makeText(MainActivity.this,"下载取消!",Toast.LENGTH_SHORT).show();
						}
					});
					super.onPreExecute();
				};
				
				@Override
				protected Void doInBackground(Void... params) {
					//模拟下载进度
					for (int i = 1; i <= 100; i++) {
						publishProgress(i+"%");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					return null;
				}
				
				@Override
				protected void onProgressUpdate(String[] values) {
					//设置进度
					cp.setMessage(values[0]);
					super.onProgressUpdate(values);
				};
				
				@Override
				protected void onPostExecute(Void result) {
					//执行结束后关闭
					cp.dismiss();
					super.onPostExecute(result);
				};
			}.execute();
			break;
		default:
			break;
		}
	}
	
	

}
