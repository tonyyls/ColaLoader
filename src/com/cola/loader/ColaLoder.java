package com.cola.loader;
/*
 * @Author yulongsheng
 * @Date 2014/8/23
 * @Email seafishyls@126.com
 * */


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ColaLoder extends Dialog {
	
	private String DEFAULT_TEXT="加载中...";
	private Context _context;
	private String _text;
	private int _duration;
	
	public ColaLoder(Context context) {
		super(context);
		_context=context;
		_text=DEFAULT_TEXT;
		init();
	}
	
	public ColaLoder(Context context,int duration) {
		super(context);
		_context=context;
		_text=DEFAULT_TEXT;
		_duration=duration;
		init();
	}
	
	public ColaLoder(Context context,String text) {
		super(context);
		_context=context;
		_text=text;
		init();
	}
	
	public ColaLoder(Context context,String text, int duration) {
		super(context);
		_context=context;
		_text=text;
		_duration=duration;
		init();
	}
	

	
	private void init(){
		//设置Dialog
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//设置布局
		LinearLayout contentView=new LinearLayout(_context);
		contentView.setMinimumHeight(70);  
		contentView.setMinimumWidth(200);
        contentView.setGravity(Gravity.CENTER);  
        contentView.setOrientation(LinearLayout.HORIZONTAL);  
        contentView.setBackgroundColor(Color.BLACK);
        //图片
        ImageView image = new ImageView(_context);  
        image.setImageResource(R.drawable.cola_loading);  
        contentView.addView(image);
        //图片旋转动画
        Animation anim=null;
        anim=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(700);
        anim.setRepeatCount(-1);
        anim.setInterpolator(new LinearInterpolator());
	    image.setAnimation(anim);  
	    image.startAnimation(anim);
        //文本
        TextView text=new TextView(_context);
        text.setText(_text);
        text.setTextColor(Color.WHITE);
        text.setTextSize(17);
        contentView.addView(text);
        this.setContentView(contentView);
        
        //N秒后消失
        if(_duration!=0){
        	Thread thread=new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(_duration);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dismiss();
				}
        		
        	};
        	thread.start();
        }
	}
	
	public void start(){
		this.show();
	}
	
	public void stop() {
		this.dismiss();
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			this.dismiss();
		}
		return true;
	}
}
