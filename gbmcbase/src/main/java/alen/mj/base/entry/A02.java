package alen.mj.base.entry;

import java.io.Serializable;

import allen.frame.tools.StringUtils;

//	职务信息集
public class A02 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String A0000, A0201A, A0201B, A0201D, A0201E, A0215A, A0219, A0223, A0225, A0243, A0245, A0247, A0251B,
			A0255, A0265, A0267, A0272, A0281, A0279;
	private String A0215B; // 原任职务
	
	public String getA0215B() {
		return A0215B;
	}

	public void setA0215B(String a0215b) {
		A0215B = a0215b;
	}

	public String getA0000() {
		return A0000;
	}

	public void setA0000(String a0000) {
		A0000 = a0000;
	}

	public String getA0201A() {
		return A0201A;
	}

	public void setA0201A(String a0201a) {
		A0201A = a0201a;
	}

	public String getA0201B() {
		return A0201B;
	}

	public void setA0201B(String a0201b) {
		A0201B = a0201b;
	}

	public String getA0201D() {
		return A0201D;
	}

	public void setA0201D(String a0201d) {
		A0201D = a0201d;
	}

	public String getA0201E() {
		return A0201E;
	}

	public void setA0201E(String a0201e) {
		A0201E = a0201e;
	}

	public String getA0215A() {
		return A0215A;
	}

	public void setA0215A(String a0215a) {
		A0215A = a0215a;
	}

	public String getA0219() {
		return A0219;
	}

	public void setA0219(String a0219) {
		A0219 = a0219;
	}

	public String getA0223() {
		if(StringUtils.empty(A0223) || "NULL".equals(A0223) || "null".equals(A0223)){
			return "0";
		}
		return A0223;
	}

	public void setA0223(String a0223) {
		A0223 = a0223;
	}

	public String getA0225() {
		if(StringUtils.empty(A0225) || "NULL".equals(A0225) || "null".equals(A0225)){
			return "0";
		}
		return A0225;
	}

	public void setA0225(String a0225) {
		A0225 = a0225;
	}

	public String getA0243() {
		return A0243;
	}

	public void setA0243(String a0243) {
		A0243 = a0243;
	}

	public String getA0245() {
		return A0245;
	}

	public void setA0245(String a0245) {
		A0245 = a0245;
	}

	public String getA0247() {
		return A0247;
	}

	public void setA0247(String a0247) {
		A0247 = a0247;
	}

	public String getA0251B() {
		return A0251B;
	}

	public void setA0251B(String a0251b) {
		A0251B = a0251b;
	}

	public String getA0255() {
		return A0255;
	}

	public void setA0255(String a0255) {
		A0255 = a0255;
	}

	public String getA0265() {
		return A0265;
	}

	public void setA0265(String a0265) {
		A0265 = a0265;
	}

	public String getA0267() {
		return A0267;
	}

	public void setA0267(String a0267) {
		A0267 = a0267;
	}

	public String getA0272() {
		return A0272;
	}

	public void setA0272(String a0272) {
		A0272 = a0272;
	}

	public String getA0281() {
		return A0281;
	}

	public void setA0281(String a0281) {
		A0281 = a0281;
	}

	public String getA0279() {
		return A0279;
	}

	public void setA0279(String a0279) {
		A0279 = a0279;
	}

}
