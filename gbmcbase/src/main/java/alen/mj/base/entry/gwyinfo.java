package alen.mj.base.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.R.integer;

public class gwyinfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String TABMCFLAGLAB = "25"; // 自定义 名册表
	public static String TABNORMALLAB = "23"; // 普通表  单位名册
	
	public static String TYPE = "type"; 
	public static String TIME = "time";
	public static String DATAVERSION = "dataversion";
	public static String PSNCOUNT = "psncount";
	public static String B0101 = "B0101";
	public static String B0111 = "B0111";
	public static String B0114 = "B0114";
	public static String B0194 = "B0194";
	public static String LINKPSN = "linkpsn";
	public static String LINKTEL = "linktel";
	public static String REMARK = "remark";
	public static String NUM = "num";//子名册数
	
	// 存储于node map中
	private HashMap<String, String> nodeMap = new HashMap<String, String>();
	private ArrayList<String> fileNames = new ArrayList<String>();
	public HashMap<String, String> getNodeMap() {
		return nodeMap;
	}
	public void setNodeMapKey2Value(String key, String value) {
		this.nodeMap.put(key, value);
	}
	public ArrayList<String> getFileNames() {
		return fileNames;
	}
	public void addFileName(String fileName) {
		this.fileNames.add(fileName);
	}
	public void addFileName(int position,String fileName) {
		this.fileNames.add(position, fileName);
	}
}
