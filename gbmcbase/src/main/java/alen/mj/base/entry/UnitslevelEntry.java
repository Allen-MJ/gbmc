package alen.mj.base.entry;

import java.io.Serializable;
import java.util.List;
/**
 *  单位 树的构建
 * @author Administrator
 *
 */
public class UnitslevelEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// 单位 标识  id  // 单位编码， 名称， 父 级 pid 
	private String id, code, name, pid, fullName;
	private int level = 0; // 当前级别
	private boolean isOpen = false; // 是否打开
	private boolean isCheck = false; // 是否被选中
	
	private boolean leaf = false; // 是否叶子
	private List<UnitslevelEntry> subList = null; // 子列表树
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<UnitslevelEntry> getSubList() {
		return subList;
	}
	public void setSubList(List<UnitslevelEntry> subList) {
		this.subList = subList;
	}
	
	@Override
	public boolean equals(Object o) {
		UnitslevelEntry entry = (UnitslevelEntry) o;
		return entry.getCode().equals(code);
	}
	@Override  
	public int hashCode() {  
		return code.hashCode();  
	}  
	
}
