package app.by1003917294.dex2jar;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import app.by1003917294.dex2jar.*;
import com.googlecode.dex2jar.v3.*;
import java.io.*;
import me.zhanghai.android.materialprogressbar.*;
import app.by1003917294.dex2jar.R;

public class ChangeView extends AppCompatActivity
{
	private ImageView 齿轮图标;
	private TextView 字;
	private MaterialProgressBar 进度条;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change);
		
		字 = (TextView)findViewById(R.id.convertingtext);
		字.setVisibility(View.INVISIBLE);
		齿轮图标 = (ImageView)findViewById(R.id.cog_logo);
		Animation 图标进场动画 = AnimationUtils.loadAnimation(ChangeView.this, R.anim.convcog_in);
		齿轮图标.startAnimation(图标进场动画);
		进度条 = (MaterialProgressBar)findViewById(R.id.convertingprogress);
		Animation 进度条进场动画 = AnimationUtils.loadAnimation(ChangeView.this, R.anim.progress_in);
		进度条.startAnimation(进度条进场动画);
		new Handler().postDelayed(new Runnable(){
				public void run() {
					字.setVisibility(View.VISIBLE);
					Animation 字进场动画 = AnimationUtils.loadAnimation(ChangeView.this, R.anim.convtext_in);
					字.startAnimation(字进场动画);
					Animation 图标进场动画 = AnimationUtils.loadAnimation(ChangeView.this, R.anim.cog_rotate);
					齿轮图标.startAnimation(图标进场动画);
					开始();
				}
			},700);
	}

	@Override
	public void onBackPressed()
	{
		
	}
	
	public void 开始(){
		new Thread(new Runnable() {
			public void run() {
				try
				{
					Dex2jar.from(ChooseFile.路径).to(new File(ChooseFile.路径).getParent() + "/" + Util.获取无后缀名(ChooseFile.名称) + ".jar");
					Message message = new Message();
					message.what = 789000;
					并行处理.sendMessage(message);
				}
				catch (IOException e)
				{}
			}
		}).start();
	}
	
	Handler 并行处理 = new Handler(){
		@Override
		public void handleMessage(Message 信息)
		{
			super.handleMessage(信息);
			
			if(信息.what == 789000)
			{
				Intent 跳转 = new Intent(ChangeView.this, Success.class);
				ActivityOptions 设置 = ActivityOptions.makeSceneTransitionAnimation(ChangeView.this,进度条,"progresstocheck");
				跳转.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(跳转,设置.toBundle());
				overridePendingTransition(0,0);
			}
		}
	};
}
