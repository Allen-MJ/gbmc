package alen.mj.base.db;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alen.mj.base.dao.A01Dao;
import alen.mj.base.dao.A36Dao;
import alen.mj.base.dao.B01Dao;
import alen.mj.base.dao.GwyinfoDao;
import alen.mj.base.dao.impl.A01DaoImpl;
import alen.mj.base.dao.impl.A36DaoImpl;
import alen.mj.base.dao.impl.B01DaoImpl;
import alen.mj.base.dao.impl.GwyinfoDaoImpl;
import alen.mj.base.entry.A01;
import alen.mj.base.entry.A36;
import alen.mj.base.entry.M01;
import alen.mj.base.entry.Tree;
import alen.mj.base.entry.UnitslevelEntry;
import alen.mj.base.entry.gwyinfo;

public class DataHelper {
	private Context context;
	private static DataHelper helper;
	private Map<String, String> map;
	private DataHelper(Context context) {
		this.context = context;
		initnative();
	}
	public static DataHelper getInstance(Context context){
		if(helper==null){
			helper = new DataHelper(context);
		}
		return helper;
	}
	
	public static DataHelper init(Context context){
		if(helper==null){
			helper = new DataHelper(context);
		}
		return helper;
	}
	public static DataHelper getInstance(){
		return helper;
	}
	
	public Map<String, String> getNative(){
		if(map==null){
			initnative();
		}
		return map;
	}
	
	private void initnative(){
		map = new HashMap<String, String>();
		map.put("01", "汉族");
		map.put("99", "加入中国籍的外国人");
		map.put("98", "外国民族");
		map.put("92", "外国血统");
		map.put("91", "其他");
		map.put("56", "基诺族");
		map.put("55", "珞巴族");
		map.put("54", "门巴族");
		map.put("53", "赫哲族");
		map.put("52", "鄂伦春族");
		map.put("51", "独龙族");
		map.put("50", "塔塔尔族");
		map.put("49", "京族");
		map.put("48", "裕固族");
		map.put("47", "保安族");
		map.put("46", "德昂族");
		map.put("45", "鄂温克族");
		map.put("44", "俄罗斯族");
		map.put("43", "乌孜别克族");
		map.put("42", "怒族");
		map.put("41", "塔吉克族");
		map.put("40", "普米族");
		map.put("39", "阿昌族");
		map.put("38", "锡伯族");
		map.put("37", "仡佬族");
		map.put("36", "毛南族");
		map.put("35", "撒拉族");
		map.put("34", "布朗族");
		map.put("33", "羌族");
		map.put("32", "仫佬族");
		map.put("31", "达斡尔族");
		map.put("30", "土族");
		map.put("29", "柯尔克孜族");
		map.put("28", "景颇族");
		map.put("27", "纳西族");
		map.put("26", "东乡族");
		map.put("25", "水族");
		map.put("24", "拉祜族");
		map.put("23", "高山族");
		map.put("22", "畲族");
		map.put("21", "佤族");
		map.put("20", "傈僳族");
		map.put("19", "黎族");
		map.put("18", "傣族");
		map.put("17", "哈萨克族");
		map.put("16", "哈尼族");
		map.put("15", "土家族");
		map.put("14", "白族");
		map.put("13", "瑶族");
		map.put("12", "侗族");
		map.put("11", "满族");
		map.put("10", "朝鲜族");
		map.put("09", "布依族");
		map.put("08", "壮族");
		map.put("07", "彝族");
		map.put("06", "苗族");
		map.put("05", "维吾尔族");
		map.put("04", "藏族");
		map.put("03", "回族");
		map.put("02", "蒙古族");
	}
	/**
	 * 获取名册
	 * @return
	 */
	public List<gwyinfo> getMcList(){
		GwyinfoDao dao = new GwyinfoDaoImpl(context);
		return dao.getMcList();
	}
	/**
	 * 获取下级名册
	 * @param code
	 * @return
	 */
	public List<gwyinfo> getMcList(String code){
		GwyinfoDao dao = new GwyinfoDaoImpl(context);
		return dao.getMcList(code);
	}
	/**
	 * 获取组织树
	 * @param code
	 * @return
	 */
	public List<Tree> getDwList(String code, String type){
		B01Dao dao = new B01DaoImpl(context);
		return dao.getDwList(code,type);
	}
	public List<Tree> getDwList(String ccode, String code, String type){
		B01Dao dao = new B01DaoImpl(context);
		return dao.getDwList(ccode,code,type);
	}
	public List<M01> getPerMcList(String code, String id, String key, String type){
		A01Dao dao = new A01DaoImpl(context);
//		System.out.println(id + "-----code---->>>" + code);
		return dao.getPerMcList(code, id, key, type);
	}
	public A01 getPersonInfo(String id){
		A01Dao dao = new A01DaoImpl(context);
		return dao.getPersonInfo(id);
	}
	public List<A36> getPersonFamily(String id){
		A36Dao dao = new A36DaoImpl(context);
		return dao.getPersonFamily(id);
	}
	public void deleteAllData(){
		GwyinfoDao dao = new GwyinfoDaoImpl(context);
		dao.deleteAllData();
	}
	
	// 获取前多少条
	public List<M01> getPerMcTopList(String code,int top, String type){
		A01Dao dao = new A01DaoImpl(context);
		return dao.getPerMcTopList(code, top, type);
	}
	
	public List<UnitslevelEntry> getUnitsLevelList(String code, String type, int levels){
		B01Dao dao = new B01DaoImpl(context);
		return dao.getUnitsList(code, type, levels);
	}
}
