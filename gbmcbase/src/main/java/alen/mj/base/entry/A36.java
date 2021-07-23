package alen.mj.base.entry;

import java.io.Serializable;

import allen.frame.tools.StringUtils;
//	家庭成员及社会关系信息集
public class A36 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String A0000, A3601,A3604A, A3607, A3627, A3611, SORTID ;

	public String getA0000() {
		return A0000;
	}

	public void setA0000(String a0000) {
		A0000 = a0000;
	}

	public String getA3601() {
		return A3601;
	}

	public void setA3601(String a3601) {
		A3601 = a3601;
	}

	public String getA3604A() {
		return A3604A;
	}

	public void setA3604A(String a3604a) {
		A3604A = a3604a;
	}

	public String getA3607() {
		return A3607;
	}

	public void setA3607(String a3607) {
		A3607 = a3607;
	}

	public String getA3627() {
		return A3627;
	}

	public void setA3627(String a3627) {
		A3627 = a3627;
	}

	public String getA3611() {
		return A3611;
	}

	public void setA3611(String a3611) {
		A3611 = a3611;
	}

	public String getSORTID() {
		if(StringUtils.empty(SORTID) || "NULL".equals(SORTID) || "null".equals(SORTID)){
			return "0";
		}
		return SORTID;
	}

	public void setSORTID(String sORTID) {
		SORTID = sORTID;
	}
	
}
