package alen.mj.base.db;

import allen.frame.tools.Logger;
import allen.frame.tools.StringUtils;

public class SqlHelper {
	
	public static String getMcList(){
		String sql = "SELECT B0101,B0111,B0114,B0194,dataversion,isMCTag,SortID,"
				+ "(SELECT count(*) from PB_MC "
				+ "WHERE isMCTag='25' AND length(B0111)=11 AND substr(B0111,1,7)=a.B0111) AS num "
				+ "from PB_MC a WHERE isMCTag='25' AND length(B0111)=7 "
				+ "UNION "
				+ "SELECT B0101,B0111,B0114,B0194,dataversion,isMCTag,SortID,0 AS num "
				+ "from PB_MC WHERE isMCTag='23' ORDER BY SortID";
		return sql;
	}
	public static String getMcList(String code){
		int len = code.length()+4;
		String sql = "SELECT B0101,B0111,B0114,B0194,dataversion,isMCTag,SortID,"
				+ "(SELECT count(*) from PB_MC "
				+ "WHERE isMCTag='25' AND length(B0111)="+(len+4)+" AND substr(B0111,1,"+len+")=a.B0111) AS num "
				+ "from PB_MC a WHERE isMCTag='25' AND B0111 like '"+code+"%' AND length(B0111)="+len+" ORDER BY SortID";
		return sql;
	}
	public static String getDwList(String code,String type){
		String sql;
		if("23".equals(type)){
			sql = "select a.*,(CASE WHEN length(a.B0111)="+code.length()+" THEN 1 ELSE 0 END) isOpen,"
					+ "(SELECT count(b.aid) from B01 b WHERE "
					+ "substr(b.B0111,1,(length(b.B0111)-4))=a.B0111 and length(b.B0111)=length(a.B0111)+4)"
					+ " num from B01 a where a.B0111 like '"+code+"%' ORDER by a.B0111,a.Sortid";
		}else{
			sql = "select *,1 as isOpen,0 as num "
					+ "from M02 WHERE M0100='"+code+"' ORDER BY SortID";
		}
		return sql;
	}
	/**
	 * 获取单位列表
	 * @param ccode
	 * @param code
	 * @param type
	 * @return
	 */
	public static String getDwList(String ccode,String code,String type){
		String sql;
		if("23".equals(type)){
			int len = code.length();
			if(ccode.equals(code)){
				sql="SELECT a.*,(CASE WHEN length(a.B0111)="+len+" THEN 1 ELSE 0 END) isOpen,"
						+ "(SELECT count(b.aid) from B01 b "
						+ "WHERE substr(b.B0111,1,(length(b.B0111)-4))=a.B0111 "
						+ "and length(b.B0111)=length(a.B0111)+4) num "
						+ "from B01 a WHERE a.B0111 LIKE '"+ccode+"%' "
						+ "AND length(a.B0111)<="+(len+4)+" ORDER BY a.B0121,a.SORTID";
			}else{
				sql="SELECT a.*,(CASE WHEN length(a.B0111)="+len+" THEN 1 ELSE 0 END) isOpen,"
						+ "(SELECT count(b.aid) from B01 b "
						+ "WHERE substr(b.B0111,1,(length(b.B0111)-4))=a.B0111 "
						+ "and length(b.B0111)=length(a.B0111)+4) num "
						+ "from B01 a WHERE a.B0111 LIKE '"+ccode+"%' "
						+ "AND length(a.B0111)="+(ccode.length()+4)+" ORDER BY a.SORTID";
			}
		}else{
			sql = "select *,1 as isOpen,0 as num "
					+ "from M02 WHERE M0100='"+code+"' ORDER BY SortID";
		}
		return sql;
	}
	/**
	 * 获取名册
	 * @param code 名册id
	 * @param id 单位id
	 * @param key 关键字
	 * @return
	 */
	public static String getPerMcList(String code,String id,String key,String type){
		Logger.e("debug", "code:"+code+"  id:"+id+" key:"+key+" type:"+type);
		String sql = "";
		if("23".equals(type)){
			if(StringUtils.notEmpty(key)){
				sql = "SELECT *,length(c.B0111)num,(select A0801A||A0901A from a08 where A0000=b.A0000 order by A0807 desc limit 1)xl,(SELECT A5714 from A57 WHERE A0000=b.A0000)A5714 from (SELECT x.* "
						+ "from A02 x,(SELECT *,min(A0225)aa from A02 GROUP BY A0000) y "
						+ "WHERE x.A0000=y.A0000 AND x.A0225=y.aa AND x.A0201B=y.A0201B) a,A01 b,B01 c "
						+ "WHERE a.A0000=b.A0000 "
						+ "AND a.A0201B=c.B0100 and c.B0100 in (select B0100 from B01 where B0111 like '"+code+"%') "
						+ "AND (b.A0101 LIKE '"+key+"%' OR b.A0102 LIKE '"+key
						+"%') GROUP BY b.A0000 ORDER by num,c.SORTID,a.A0225,a.A0000";
			}else{
				sql = "SELECT *,(select A0801A||A0901A from a08 where A0000=b.A0000 order by A0807 desc limit 1)xl,(SELECT A5714 from A57 WHERE A0000=b.A0000)A5714 from (SELECT x.* from A02 x,(SELECT *,min(A0225)aa from A02 "
						+ "GROUP BY A0000,A0201B) y WHERE x.A0000=y.A0000 AND x.A0225=y.aa "
						+ "AND x.A0201B=y.A0201B) a,A01 b WHERE a.A0000=b.A0000 "
						+ "AND (b.A0101 LIKE '"+key+"%' OR b.A0102 LIKE '"+key
						+"%') AND a.A0201B='"+id+"' GROUP BY b.A0000,a.A0201B ORDER by a.A0225,a.A0000";
			}
		}else{
			if(StringUtils.empty(id)){
				sql = "SELECT c.*,d.*,(select A0801A||A0901A from a08 where A0000=d.A0000 order by A0807 desc limit 1)xl,(SELECT A5714 from A57 WHERE A0000=d.A0000)A5714 from (SELECT b.*,a.M0111,a.B0100,a.SortID "
						+ "from M03 a,A01 b WHERE a.A0000=b.A0000 "
						+ "AND a.M0111='"+code+"') d LEFT JOIN A02 c "
						+ "ON d.B0100=c.A0201B AND d.A0000=c.A0000 "
						+ "WHERE d.A0101 LIKE '"+key+"%' OR d.A0102 LIKE '"+key+"%' "
						+ "GROUP BY d.A0000 ORDER BY d.SortID";
			}else{
				sql = "SELECT c.*,d.*,(select A0801A||A0901A from a08 where A0000=d.A0000 order by A0807 desc limit 1)xl,(SELECT A5714 from A57 WHERE A0000=d.A0000)A5714 from (SELECT b.*,a.M0111,a.B0100,a.SortID "
						+ "from M03 a,A01 b WHERE a.A0000=b.A0000 AND a.B0100='"+id+"' "
						+ "AND a.M0111='"+code+"') d LEFT JOIN A02 c "
						+ "ON d.B0100=c.A0201B AND d.A0000=c.A0000 "
						+ "WHERE d.A0101 LIKE '"+key+"%' OR d.A0102 LIKE '"+key+"%' "
						+ "GROUP BY d.A0000 ORDER BY d.SortID";
			}
		}
		
		return sql;
	}
	public static String getPersonInfo(String id){
		String sql = "SELECT * from (SELECT * from A01 WHERE A0000='"+id+"') a "
				+ "LEFT JOIN (SELECT * from A57 WHERE A0000='"+id+"') b "
						+ "ON a.A0000=b.A0000";
		return sql;
	}
	public static String getPersonFamily(String id){
		String sql = "SELECT DISTINCT * from A36 WHERE A0000='"+id+"' ORDER BY sortid";
		return sql;
	}
	
	public static String getPerMcTopList(String code,int top,String type){
		String sql;
		if("23".equals(type)){
			sql = "select * from (select * from A02 a, A01 b WHERE a.A0000=b.A0000 "
					+ "AND a.A0201B in (select B0100 from B01 where B0111 like '"
					+code+"%') GROUP BY b.A0000,a.A0201B ORDER by a.A0225) limit 0," + top;
		}else{
			sql = "select * from (select a.*,b.* from A02 a, A01 b,M03 c WHERE a.A0000=b.A0000 "
					+ "AND a.A0000=c.A0000 and a.A0201B=c.B0100 and c.M0111='"+code+"' GROUP BY b.A0000,a.A0201B ORDER by c.SortID) limit 0," + top;
		}
		return sql;
	}
}
