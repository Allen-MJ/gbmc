package alen.mj.base.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.format.Time;

import alen.mj.base.db.DataHelper;
import alen.mj.base.entry.Tree;
import allen.frame.tools.FileUtils;
import allen.frame.tools.Logger;
import allen.frame.tools.StringUtils;

public class Constants {
	public static final String CHECK_CODE_1 = "CHECK_CODE_1";
	public static final String DB_NAME = "GWY_DB.db";
	public static final int VERSION = 100;//初始数据库版本号100
	public static List<Tree> tree;
	
	public static String getNative(String code){
		Map<String, String> map = DataHelper.getInstance().getNative();
		if(StringUtils.notEmpty(code)){
			if(map.containsKey(code)){
				return map.get(code);
			}else{
				return "其他";
			}
		}else{
			return "";
		}
	}
	public static String getSex(String sex){
		if(StringUtils.notEmpty(sex)){
			if("1".equals(sex)){
				return "男";
			}else if("2".equals(sex)){
				return "女";
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	public static String getGwyFilePath(){
		return FileUtils.getInstance().getGenPath();
	}
	public static String getPhotoPath(){
		return FileUtils.getInstance().creatNewDir("Photos").getAbsolutePath();
	}
	public static String ageOfdate(String date,String dw){
		if(StringUtils.empty(date)||checkisDead(dw)){
			return "";
		}
		Time time = new Time();
		time.setToNow();
		int y = time.year;
		int m = time.month+1;
		double a;
		if(m<10){
			a = Double.parseDouble(y+".0"+m);
		}else{
			a = Double.parseDouble(y+"."+m);
		}
		if(date.length()>4){
			StringBuffer sb = new StringBuffer(date);
			if(date.contains(".")){
				String num = sb.toString();
				if(date.length()>7){
					num = num.substring(0, 7);
				}
				double b = Double.parseDouble(num);
				return (int)Math.floor(a-b)+"";
			}else{
				String num = sb.insert(4, ".").toString();
				double b = Double.parseDouble(num);
				return (int)Math.floor(a-b)+"";
			}
		}
		return "";
		/*if(StringUtils.empty(date)){
			return "";
		}else if(date.contains(".")){
			return date;
		}else if(date.length()>4){
			return new StringBuffer(date).insert(4, ".").toString();
		}else{
			return date;
		}*/
	}
	public static String ageOfdate(String date){
		if(StringUtils.empty(date)){
			return "";
		}
		Time time = new Time();
		time.setToNow();
		int y = time.year;
		int m = time.month+1;
		double a;
		if(m<10){
			a = Double.parseDouble(y+".0"+m);
		}else{
			a = Double.parseDouble(y+"."+m);
		}
		if(date.length()>4){
			StringBuffer sb = new StringBuffer(date);
			if(date.contains(".")){
				String num = sb.toString();
				if(date.length()>7){
					num = num.substring(0, 7);
				}
				double b = Double.parseDouble(num);
				return (int)Math.floor(a-b)+"";
			}else{
				String num = sb.insert(4, ".").toString();
				double b = Double.parseDouble(num);
				return (int)Math.floor(a-b)+"";
			}
		}
		return "";
		/*if(StringUtils.empty(date)){
			return "";
		}else if(date.contains(".")){
			return date;
		}else if(date.length()>4){
			return new StringBuffer(date).insert(4, ".").toString();
		}else{
			return date;
		}*/
	}
	public static String dategs(String date){
		if(StringUtils.empty(date)){
			return "";
		}else if(date.contains(".")){
			return date.replace(".", "年")+"月";
		}
		return date;
	}
	private static boolean checkisDead(String dw){
		return StringUtils.empty(dw)?false:(dw.contains("去世")
				||dw.contains("死亡")||dw.contains("离世"));
	}
	public static String replaceJl(String jl){
		if(StringUtils.empty(jl)){
			return "";
		}else{
			return jl.replaceAll(" ", "&nbsp;&nbsp;").replaceAll("\\r\\n", "<br>")
					.replaceAll("\\n", "<br>");
		}
	}
	public static String cldate(String date){
		if(StringUtils.empty(date)){
			return "";
		}else{
			if(date.length()>4){
				StringBuffer sb = new StringBuffer(date);
				if(date.contains(".")){
					String num = sb.toString();
					if(date.length()>7){
						num = num.substring(0, 7);
					}
					return num;
				}else{
					StringBuffer msb = new StringBuffer();
					String num;
					if(sb.length()>6){
						msb.append(sb.substring(0, 6));
					}else{
						msb = new StringBuffer(sb);
					}
					num = msb.insert(4, ".").toString();
					return num;
				}
			}else{
				return date;
			}
		}
	}
	public static String getJlJson(String jl){
		Logger.e("debug", "简历:"+jl);
		JSONArray array = new JSONArray();
		if(StringUtils.empty(jl)){
			return StringUtils.replaceJson(array.toString());
		}
		
		if(jl.contains("\n")){
			String[] jls = jl.split("\\n");
			for(int i=0;i<jls.length;i++){
				String item = jls[i];
				JSONObject object = new JSONObject();
				try {
					char firt = 0;
					if(item.length()>0){
						firt = item.charAt(0);
					}
					if(firt>'0' && firt<='9'){
						if(item.contains(" ")){
							object.put("date", item.substring(0, item.indexOf(" "))
									.replaceAll(" ", ""));
							object.put("jl", item.substring(item.indexOf(" "))
									.replaceAll(" ", ""));
						}else{
							object.put("date", "");
							object.put("jl", item);
						}
					}else{
						object.put("date", "");
						object.put("jl", item);
					}
					array.put(object);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return StringUtils.replaceJson(array.toString());
		}else if(jl.contains("\r")){//
			String[] jls = jl.split("\\r");
			for(int i=0;i<jls.length;i++){
				String item = jls[i];
				JSONObject object = new JSONObject();
				try {
					char firt = 0;
					if(item.length()>0){
						firt = item.charAt(0);
					}
					if(firt>'0' && firt<='9'){
						if(item.contains(" ")){
							object.put("date", item.substring(0, item.indexOf(" "))
									.replaceAll(" ", ""));
							object.put("jl", item.substring(item.indexOf(" "))
									.replaceAll(" ", ""));
						}else{
							object.put("date", "");
							object.put("jl", item);
						}
					}else{
						object.put("date", "");
						object.put("jl", item);
					}
					array.put(object);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return StringUtils.replaceJson(array.toString());
		}else{
			JSONObject object = new JSONObject();
			try {
				char firt = 0;
				if(jl.length()>0){
					firt = jl.charAt(0);
				}
				if(firt>'0' && firt<='9'){
					if(jl.contains(" ")){
						object.put("date", jl.substring(0, jl.indexOf(" "))
								.replaceAll(" ", ""));
						object.put("jl", jl.substring(jl.indexOf(" "))
								.replaceAll(" ", ""));
					}else{
						object.put("date", "");
						object.put("jl", jl);
					}
				}else{
					object.put("date", "");
					object.put("jl", jl);
				}
				
				array.put(object);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return StringUtils.replaceJson(array.toString());
		}
	}
	/**
	 * 
	 * @param path
	 */
	public static void deletFile(String path){
		if(StringUtils.empty(path)){
			return;
		}
		File f = new File(path);
		if(!f.exists()||f==null){
			return;
		}
		if(f.isDirectory()){
			File[] child = f.listFiles();
			if(child==null||child.length==0){
				return;
			}
			for(File fc:child){
				if(!fc.isDirectory()){
					fc.delete();
				}
			}
		}
	}
	public static String hideSfz(String sfz){
		if(StringUtils.empty(sfz)){
			return "";
		}
		return sfz.substring(0, 6)+"********"+sfz.substring(sfz.length()-4);
	}
	public static int NET_STATE = 0;//0未开，1打开
	
	public static boolean isConnectionAvailable(Context cotext){  
        boolean isConnectionFail = true;  
        ConnectivityManager connectivityManager = (ConnectivityManager)cotext.getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (connectivityManager != null){
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();                   
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()){                 
                isConnectionFail = true;  
            }else {
                isConnectionFail = false;  
            }  
        }else{
            Logger.e("debug", "Can't get connectivitManager");  
        }  
        return isConnectionFail;  
    }
	
	public static String getFileEndName(String name){
		if(StringUtils.empty(name)){
			return "";
		}else if(name.length()>=21){
			int start = name.length()-21;
			return name.substring(start);
		}else{
			return name;
		}
	}
	
}
