package alen.mj.base.entry;

import java.io.Serializable;

import allen.frame.tools.StringUtils;
/**
 * 人员   单位  名册   关联表
 * @author Administrator
 *
 */
public class M03 implements Serializable {
	private static final long serialVersionUID = 1L;
	// M0111  为名册的 编号如：001.002 
	private String M0300, M0111, B0100, A0000, SortID;

	public String getM0300() {
		return M0300;
	}

	public void setM0300(String m0300) {
		M0300 = m0300;
	}

	public String getM0111() {
		return M0111;
	}

	public void setM0111(String m0111) {
		M0111 = m0111;
	}

	public String getB0100() {
		return B0100;
	}

	public void setB0100(String b0100) {
		B0100 = b0100;
	}

	public String getA0000() {
		return A0000;
	}

	public void setA0000(String a0000) {
		A0000 = a0000;
	}

	public String getSortID() {
		if(StringUtils.empty(SortID) || "NULL".equals(SortID) || "null".equals(SortID)){
			return "0";
		}
		return SortID;
	}

	public void setSortID(String sortID) {
		SortID = sortID;
	}
	
	
}
