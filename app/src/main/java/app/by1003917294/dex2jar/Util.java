package app.by1003917294.dex2jar;

import android.graphics.*;
import android.os.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import org.apache.commons.codec.binary.*;
import org.apache.commons.io.*;

public class Util
{
	public static String 获取后缀名(String 文件名称)
	{
		int 最后 = 文件名称.lastIndexOf(".");
		return 文件名称.substring(最后 + 1);
	}
	
	public static String 获取无后缀名(String 文件名称)
	{
		int 最后 = 文件名称.lastIndexOf(".");
		return 文件名称.substring(0,最后);
	}
	
	public static String 获取SD卡位置() {
        return Environment.getExternalStorageDirectory().getPath();
    }
	
	
}
