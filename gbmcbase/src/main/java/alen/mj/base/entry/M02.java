package alen.mj.base.entry;

import java.io.Serializable;

import allen.frame.tools.StringUtils;
/**
 *  名册  单位  关联表
 * @author Administrator
 *
 */
public class M02 implements Serializable{
	private static final long serialVersionUID = 1L;
	// M0100  为名册的 编号如：001.002
	private String M0200, M0100, B0100, B0101, B0104, SortID;

	public String getM0200() {
		return M0200;
	}

	public void setM0200(String m0200) {
		M0200 = m0200;
	}

	public String getM0100() {
		return M0100;
	}

	public void setM0100(String m0100) {
		M0100 = m0100;
	}

	public String getB0100() {
		return B0100;
	}

	public void setB0100(String b0100) {
		B0100 = b0100;
	}

	public String getB0101() {
		return B0101;
	}

	public void setB0101(String b0101) {
		B0101 = b0101;
	}

	public String getB0104() {
		return B0104;
	}

	public void setB0104(String b0104) {
		B0104 = b0104;
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
