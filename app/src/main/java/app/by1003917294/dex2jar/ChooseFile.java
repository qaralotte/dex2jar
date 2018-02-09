package app.by1003917294.dex2jar;

import android.content.*;
import android.database.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import android.app.*;

public class ChooseFile extends AppCompatActivity
{
	private ArrayList<HashMap<String, Object>> 列表条例;
	private SimpleAdapter 列表适配器;
	private String 现在的路径;
	private ListView 文件列表;
	
	static public String 路径;
	static public String 名称;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filelistview);
		
		文件列表 = (ListView) findViewById(R.id.filelist);
		Animation 选择文件进场动画 = AnimationUtils.loadAnimation(ChooseFile.this, R.anim.fileitem_in);
		LayoutAnimationController 选择文件列表进场动画 = new LayoutAnimationController(选择文件进场动画);
		文件列表.setLayoutAnimation(选择文件列表进场动画);
		初始化列表(Util.获取SD卡位置());
		文件列表.setAdapter(列表适配器);
		现在的路径 = Util.获取SD卡位置();
		
		文件列表.setOnItemClickListener(new ListView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> 适配, final View 视图, int 位置, long 时间)
				{
					if(位置 == 0){
						初始化列表(new File(现在的路径).getParent());
						文件列表.setAdapter(列表适配器);
						现在的路径 = new File(现在的路径).getParent();
						Animation 选择文件进场动画 = AnimationUtils.loadAnimation(ChooseFile.this, R.anim.fileitem_in);
						LayoutAnimationController 选择文件列表进场动画 = new LayoutAnimationController(选择文件进场动画);
						文件列表.setLayoutAnimation(选择文件列表进场动画);
					}
					else if(位置 != 0 && ((Integer)((Map)列表条例.get(位置)).get("ItemImage")).intValue() == R.drawable.ic_folder)
					{
						String 路径 = (String)((Map)列表条例.get(位置)).get("ItemTitle");
						初始化列表(路径);
						文件列表.setAdapter(列表适配器);
						现在的路径 = 路径;
						Animation 选择文件进场动画 = AnimationUtils.loadAnimation(ChooseFile.this, R.anim.fileitem_in);
						LayoutAnimationController 选择文件列表进场动画 = new LayoutAnimationController(选择文件进场动画);
						文件列表.setLayoutAnimation(选择文件列表进场动画);
					}
					else if(位置 != 0 && ((Integer)((Map)列表条例.get(位置)).get("ItemImage")).intValue() == R.drawable.ic_file)
					{
						final String 最终路径 = (String)((Map)列表条例.get(位置)).get("ItemTitle");
						final String 最终名称 = (String)((Map)列表条例.get(位置)).get("RealTitle");
						
						ChooseFile.this.路径 = 最终路径;
						ChooseFile.this.名称 = 最终名称;
						Animation 选择文件出场动画 = AnimationUtils.loadAnimation(ChooseFile.this, R.anim.fileitem_out);
						文件列表.startAnimation(选择文件出场动画);
						new Handler().postDelayed(new Runnable(){
								public void run() {
									Intent 跳转 = new Intent(ChooseFile.this,ChangeView.class);
									startActivityForResult(跳转,2000);
									overridePendingTransition(0,0);
									setResult(2000);
								}
							},700);
					}
				}
		});
		
	}
	
	private void 初始化列表(String 路径){
		int 计数 = 0;
        列表条例 = new ArrayList<HashMap<String, Object>>();
		File 初始路径 = new File(路径);
		File[] 文件们 = 初始路径.listFiles();
		
		//计算文件夹
		for(计数 = 0;计数 < 文件们.length;计数++) { 
			HashMap<String, Object> 哈希表2 = new HashMap<String, Object>();  
			if (文件们[计数].isDirectory())
			{
				哈希表2.put("ItemTitle", 文件们[计数].getPath());
				哈希表2.put("RealTitle", 文件们[计数].getName());
				哈希表2.put("ItemImage", R.drawable.ic_folder);	
				列表条例.add(哈希表2);
			}
		}
		Collections.sort(列表条例, new Comparator<HashMap<String,Object>>(){ 
				@Override 
				public int compare(HashMap<String, Object> arg0,HashMap<String, Object> arg1) {
					return ((String) arg0.get("RealTitle")).compareTo((String) arg1.get("RealTitle")); 
				}

			});
			
		//后退
		if(!初始路径.getPath().equals("/")){
			HashMap<String, Object> 哈希表1 = new HashMap<String, Object>();
			哈希表1.put("ItemTitle", 初始路径.getPath());
			哈希表1.put("RealTitle", "..");
			哈希表1.put("ItemImage", R.drawable.ic_chevron_left);

			列表条例.add(0,哈希表1);
		}
		
		//计算文件
		if(计数 == 文件们.length)
		{
			for(File 文件 : 文件们) {
				HashMap<String, Object> 哈希表3 = new HashMap<String, Object>();
				if (文件.isFile())
				{
					if(Util.获取后缀名(文件.getName()).equals("dex")){
						哈希表3.put("ItemTitle", 文件.getPath());
						哈希表3.put("RealTitle", 文件.getName());
						哈希表3.put("ItemImage", R.drawable.ic_file);	
						列表条例.add(哈希表3);
					}
				}
			}
		}
        列表适配器 = new SimpleAdapter(this,列表条例,   
										   R.layout.filelistitem,   
										   new String[] {"RealTitle", "ItemImage"},        
									 	   new int[] {R.id.title, R.id.img}   									);   
    }

	@Override
	public void onBackPressed()
	{
		if(!现在的路径.equals(Util.获取SD卡位置()))
		{
			初始化列表(new File(现在的路径).getParent());
			文件列表.setAdapter(列表适配器);
			现在的路径 = new File(现在的路径).getParent();
			Animation 选择文件进场动画 = AnimationUtils.loadAnimation(ChooseFile.this, R.anim.fileitem_in);
			LayoutAnimationController 选择文件列表进场动画 = new LayoutAnimationController(选择文件进场动画);
			文件列表.setLayoutAnimation(选择文件列表进场动画);
		}else
		{
			finish();
			overridePendingTransition(0,0);
			setResult(1000);
		}
	}
	
}
