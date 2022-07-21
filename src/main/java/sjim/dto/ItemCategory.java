package sjim.dto;

public class ItemCategory {
	private int itemCateNum;
	private String itemCateName;
	 

	public int getItemCateNum() {
		return itemCateNum;
	}


	public void setItemCateNum(int itemCateNum) {
		this.itemCateNum = itemCateNum;
	}


	public String getItemCateName() {
		return itemCateName;
	}


	public void setItemCateName(String itemCateName) {
		this.itemCateName = itemCateName;
	}


	@Override
	public String toString() {
		return "ItemCategory [itemCateNum=" + itemCateNum + ", itemCateName=" + itemCateName + "]";
	}
}
