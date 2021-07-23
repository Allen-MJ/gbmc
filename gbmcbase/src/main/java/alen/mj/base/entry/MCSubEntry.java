package alen.mj.base.entry;

import java.io.Serializable;

import allen.frame.tools.StringUtils;
/**
 * M01
 * @author Administrator
 *
 */
public class MCSubEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String M0111,M0101,SortID,M0105;

	public String getM0111() {
		return M0111;
	}

	public void setM0111(String b0101) {
		M0111 = b0101;
	}

	public String getM0101() {
		return M0101;
	}

	public void setM0101(String b0111) {
		M0101 = b0111;
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

	public String getM0105() {
		return M0105;
	}

	public void setM0105(String m0105) {
		M0105 = m0105;
	}

	public MCSubEntry() {
		super();
	}
	
}
