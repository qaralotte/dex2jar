package app.by1003917294.dex2jar;

import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.widget.*;
import android.view.animation.*;

public class Success extends AppCompatActivity
{
	private ImageView success;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.success);
		
		success = (ImageView)findViewById(R.id.successImageView1);
		
		new Handler().postDelayed(new Runnable(){
				public void run() {
					Animation success出场动画 = AnimationUtils.loadAnimation(Success.this, R.anim.logo_out);
					success.startAnimation(success出场动画);
					new Handler().postDelayed(new Runnable(){
							public void run() {
								Intent 回到主界面 = new Intent(Success.this,MainView.class);
								startActivity(回到主界面);
								finish();
								overridePendingTransition(0,0);
							}
						},700);
				}
			},2000);
	}
	
}
