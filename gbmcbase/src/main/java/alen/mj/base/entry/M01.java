package alen.mj.base.entry;

import java.io.Serializable;

import alen.mj.base.utils.Constants;
import allen.frame.tools.StringUtils;

public class M01 implements Serializable {
	/**
	 * 名册
	 */
	private static final long serialVersionUID = 1L;
	private String M0100;
	private String M0101;//姓名
	private String M0102;//现任职务
	private String M0103;//原任职务
	private String M0104;//任职时间
	private String M0105;//任现职级时间
	private String M0106;//性别
	private String M0107;//民族
	private String M0108;//出生年月
	private String M0109;//籍贯
	private String M0110;//全日制学历学位
	private String M0111;//在职学历学位
	private String M0112;//专业技术职务
	private String M0113;//参加工作时间
	private String M0114;//入党时间
	private String M0115;//备注
	private String zy;//专业
	private String B0100;//单位编号
	private String B0101;//单位简称
	private String B0105;//单位简称
	private String B0301;//岗位
	private String A0200;//
	private String B0300;//
	private String B0307;//
	private String B0303;//
	private String A5714;//
	private String xl;
	private String A0144;
	
	public M01() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "M01 [M0100=" + M0100 + ", M0101=" + M0101 + ", M0102=" + M0102
				+ ", M0103=" + M0103 + ", M0104=" + M0104 + ", M0105=" + M0105
				+ ", M0106=" + M0106 + ", M0107=" + M0107 + ", M0108=" + M0108
				+ ", M0109=" + M0109 + ", M0110=" + M0110 + ", M0111=" + M0111
				+ ", M0112=" + M0112 + ", M0113=" + M0113 + ", M0114=" + M0114
				+ ", M0115=" + M0115 + "]";
	}
	public String getM0100() {
		return M0100;
	}
	public void setM0100(String m0100) {
		M0100 = StringUtils.replaceNull(m0100);
	}
	public String getM0101() {
		return M0101;
	}
	public void setM0101(String m0101) {
		M0101 = StringUtils.replaceNull(m0101);
	}
	
	public String getM0102() {
		return M0102;
	}
	public void setM0102(String m0102) {
		M0102 = StringUtils.replaceNull(m0102);
	}
	public String getM0103() {
		return M0103;
	}
	public void setM0103(String m0103) {
		M0103 = StringUtils.replaceNull(m0103);
	}
	public String getM0104() {
		return M0104;
	}
	public void setM0104(String m0104) {
		M0104 = StringUtils.replaceNull(m0104);
	}
	public String getM0105() {
		return M0105;
	}
	public void setM0105(String m0105) {
		M0105 = StringUtils.replaceNull(m0105);
	}
	public String getM0106() {
		return M0106;
	}
	public void setM0106(String m0106) {
		M0106 = StringUtils.replaceNull(m0106);
	}
	public String getM0107() {
		return M0107;
	}
	public void setM0107(String m0107) {
		M0107 = StringUtils.replaceNull(m0107);
	}
	public String getM0108() {
		return M0108;
	}
	public void setM0108(String m0108) {
		M0108 = StringUtils.replaceNull(m0108);
	}
	public String getM0109() {
		return M0109;
	}
	public void setM0109(String m0109) {
		M0109 = StringUtils.replaceNull(m0109);
	}
	public String getM0110() {
		return M0110;
	}
	public void setM0110(String m0110) {
		M0110 = StringUtils.replaceNull(m0110);
	}
	public String getM0111() {
		return M0111;
	}
	public void setM0111(String m0111) {
		M0111 = StringUtils.replaceNull(m0111);
	}
	public String getM0112() {
		return M0112;
	}
	public void setM0112(String m0112) {
		M0112 = StringUtils.replaceNull(m0112);
	}
	public String getM0113() {
		return M0113;
	}
	public void setM0113(String m0113) {
		M0113 = StringUtils.replaceNull(m0113);
	}
	public String getM0114() {
		return M0114;
	}
	public void setM0114(String m0114) {
		M0114 = StringUtils.replaceNull(m0114);
	}
	public String getM0115() {
		return M0115;
	}
	public void setM0115(String m0115) {
		M0115 = StringUtils.replaceNull(m0115);
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = StringUtils.replaceNull(zy);
	}
	public String getB0100() {
		return B0100;
	}
	public void setB0100(String b0100) {
		B0100 = b0100;
	}
	public String getB0101() {
		return StringUtils.null2Empty(B0101).replaceAll("当前机构：", "");
	}
	public void setB0101(String b0101) {
		B0101 = b0101;
	}
	public String getB0105() {
		return B0105;
	}
	public void setB0105(String b0105) {
		B0105 = b0105;
	}
	public String getB0301() {
		return B0301;
	}
	public void setB0301(String b0301) {
		B0301 = StringUtils.replaceNull(b0301);
	}
	public String getA0200() {
		return A0200;
	}
	public void setA0200(String a0200) {
		A0200 = StringUtils.replaceNull(a0200);
	}
	public String getB0300() {
		return B0300;
	}
	public void setB0300(String b0300) {
		B0300 = StringUtils.replaceNull(b0300);
	}
	public String getB0307() {
		return B0307;
	}
	public void setB0307(String b0307) {
		B0307 = StringUtils.replaceNull(b0307);
	}
	public String getB0303() {
		return B0303;
	}
	public void setB0303(String b0303) {
		B0303 = StringUtils.replaceNull(b0303);
	}
	public String getA5714() {
		return A5714;
	}
	public void setA5714(String a5714) {
		A5714 = a5714;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getA0144() {
		return A0144;
	}
	public void setA0144(String a0144) {
		A0144 = a0144;
	}
	public String getZx() {
		StringBuffer sb = new StringBuffer();
		if(StringUtils.notEmpty(M0106)){
			sb.append(M0106+"，");
		}
		if(StringUtils.notEmpty(M0108)){
			String date = Constants.dategs(M0108);
			sb.append(Constants.ageOfdate(M0108)+"岁"+(StringUtils.empty(date)?"":"("+date+"出生)")+"，");
		}
		if(StringUtils.notEmpty(M0107)){
			sb.append(M0107+"，");
		}
		if(StringUtils.notEmpty(M0109)){
			sb.append(M0109+"人，");
		}
		if(StringUtils.notEmpty(A0144)){
			String date = Constants.dategs(A0144);
			sb.append(StringUtils.empty(date)?"":date+"入党"+"，");
		}
		if(StringUtils.notEmpty(M0113)){
			String date = Constants.dategs(M0113);
			sb.append(StringUtils.empty(date)?"":date+"参加工作"+"，");
		}
		if(StringUtils.notEmpty(xl)){
			sb.append(xl+"，");
		}
		if(StringUtils.notEmpty(M0112)){
			sb.append(M0112+"，");
		}
		if(StringUtils.notEmpty(M0102)){
			sb.append("现任"+M0102+"。");
		}else{
			if(sb.length()>0){
				sb.replace(sb.length()-1, sb.length(), "。");
			}
		}
		return sb.toString();
	}
}
