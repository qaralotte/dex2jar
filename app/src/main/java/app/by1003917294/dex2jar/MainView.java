package app.by1003917294.dex2jar;

import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import app.by1003917294.dex2jar.*;
import com.googlecode.dex2jar.v3.*;
import java.io.*;

public class MainView extends AppCompatActivity 
{
	private FloatingActionButton 选择文件控件;
	private ImageView 图标;
	private TextView 关于作者;
	private TextView 关于版本;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		初始化程序();
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 1000)
		{
			Animation 图标进场动画 = AnimationUtils.loadAnimation(MainView.this, R.anim.logo_in);
			图标.setVisibility(View.VISIBLE);
			图标.startAnimation(图标进场动画);
			
			Animation 选择文件控件进场动画 = AnimationUtils.loadAnimation(MainView.this, R.anim.choosefile_in);
			选择文件控件.setVisibility(View.VISIBLE);
			选择文件控件.startAnimation(选择文件控件进场动画);
		}
	}

	public void 初始化程序()
	{
		选择文件控件 = (FloatingActionButton)findViewById(R.id.choosefile);
		Animation 选择文件控件进场动画 = AnimationUtils.loadAnimation(MainView.this, R.anim.choosefile_in);
		选择文件控件.startAnimation(选择文件控件进场动画);
		
		关于作者 = (TextView)findViewById(R.id.about1);
		关于作者.setVisibility(View.INVISIBLE);
		
		关于版本 = (TextView)findViewById(R.id.about2);
		关于版本.setVisibility(View.INVISIBLE);
		
		图标 = (ImageView)findViewById(R.id.logoimage);
		图标.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					if(关于作者.getVisibility() == View.INVISIBLE && 关于版本.getVisibility() == View.INVISIBLE)
					{
						Animation 关于作者入场 = AnimationUtils.loadAnimation(MainView.this, R.anim.convtext_in);
						关于作者.startAnimation(关于作者入场);
						关于作者.setVisibility(View.VISIBLE);
						
						Animation 关于版本入场 = AnimationUtils.loadAnimation(MainView.this, R.anim.convtext_in);
						关于版本.startAnimation(关于版本入场);
						关于版本.setVisibility(View.VISIBLE);
					}
				}
		});
	}
	
	public void 选择文件(View 控件)
	{
		Animation 选择文件控件出场动画 = AnimationUtils.loadAnimation(MainView.this, R.anim.choosefile_out);
		选择文件控件.startAnimation(选择文件控件出场动画);
		选择文件控件.setVisibility(View.INVISIBLE);
		
		Animation 图标出场动画 = AnimationUtils.loadAnimation(MainView.this, R.anim.logo_out);
		图标.startAnimation(图标出场动画);
		图标.setVisibility(View.INVISIBLE);
		
		new Handler().postDelayed(new Runnable(){
				public void run() {
					Intent 跳转 = new Intent(MainView.this,ChooseFile.class);
					startActivityForResult(跳转,1000);
					overridePendingTransition(0,0);
				}
		},700);
	}
}
