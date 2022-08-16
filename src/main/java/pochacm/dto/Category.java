package pochacm.dto;

public class Category {
	private int cateNum;
	private String cateName;
	
	@Override
	public String toString() {
		return "Category [cateNum=" + cateNum + ", cateName=" + cateName + "]";
	}
	public int getCateNum() {
		return cateNum;
	}
	public void setCateNum(int cateNum) {
		this.cateNum = cateNum;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
}
