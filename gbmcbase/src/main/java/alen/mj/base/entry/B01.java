package alen.mj.base.entry;

import java.io.Serializable;

import allen.frame.tools.StringUtils;
// 单位基本情况信息集
public class B01 implements Serializable{

	private static final long serialVersionUID = 1L;
	private String B0101, B0104, B0107, B0114, B0111, B0100, B0117, B0121, B0124, B0127, B0131, B0194, B0227, B0232,
			B0233, B0236, B0234, B0238, B0239, B0150, B0183, B0185, SORTID; 

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

	public String getB0107() {
		return B0107;
	}

	public void setB0107(String b0107) {
		B0107 = b0107;
	}

	public String getB0114() {
		return B0114;
	}

	public void setB0114(String b0114) {
		B0114 = b0114;
	}

	public String getB0111() {
		return B0111;
	}

	public void setB0111(String b0111) {
		B0111 = b0111;
	}

	public String getB0100() {
		return B0100;
	}

	public void setB0100(String b0100) {
		B0100 = b0100;
	}

	public String getB0117() {
		return B0117;
	}

	public void setB0117(String b0117) {
		B0117 = b0117;
	}

	public String getB0121() {
		return B0121;
	}

	public void setB0121(String b0121) {
		B0121 = b0121;
	}

	public String getB0124() {
		return B0124;
	}

	public void setB0124(String b0124) {
		B0124 = b0124;
	}

	public String getB0127() {
		return B0127;
	}

	public void setB0127(String b0127) {
		B0127 = b0127;
	}

	public String getB0131() {
		return B0131;
	}

	public void setB0131(String b0131) {
		B0131 = b0131;
	}

	public String getB0194() {
		return B0194;
	}

	public void setB0194(String b0194) {
		B0194 = b0194;
	}

	public String getB0227() {
		return B0227;
	}

	public void setB0227(String b0227) {
		B0227 = b0227;
	}

	public String getB0232() {
		return B0232;
	}

	public void setB0232(String b0232) {
		B0232 = b0232;
	}

	public String getB0233() {
		return B0233;
	}

	public void setB0233(String b0233) {
		B0233 = b0233;
	}

	public String getB0236() {
		return B0236;
	}

	public void setB0236(String b0236) {
		B0236 = b0236;
	}

	public String getB0234() {
		return B0234;
	}

	public void setB0234(String b0234) {
		B0234 = b0234;
	}

	public String getB0238() {
		return B0238;
	}

	public void setB0238(String b0238) {
		B0238 = b0238;
	}

	public String getB0239() {
		return B0239;
	}

	public void setB0239(String b0239) {
		B0239 = b0239;
	}

	public String getB0150() {
		return B0150;
	}

	public void setB0150(String b0150) {
		B0150 = b0150;
	}

	public String getB0183() {
		return B0183;
	}

	public void setB0183(String b0183) {
		B0183 = b0183;
	}

	public String getB0185() {
		return B0185;
	}

	public void setB0185(String b0185) {
		B0185 = b0185;
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
