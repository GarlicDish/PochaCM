package pochacm.dto;

public class Ingredients {
	private int ingredientsNum;
	private int recipeNum;
	private int itemNum;
	private int masterNum;
	private int itemQty;
	
	public int getIngredientsNum() {
		return ingredientsNum;
	}
	public void setIngredientsNum(int ingredientsNum) {
		this.ingredientsNum = ingredientsNum;
	}
	public int getrecipeNum() {
		return recipeNum;
	}
	public void setrecipeNum(int recipeNum) {
		this.recipeNum = recipeNum;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getMasterNum() {
		return masterNum;
	}
	public void setMasterNum(int masterNum) {
		this.masterNum = masterNum;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	@Override
	public String toString() {
		return "Ingredients [ingredientsNum=" + ingredientsNum + ", recipeNum=" + recipeNum + ", itemNum=" + itemNum
				+ ", masterNum=" + masterNum + ", itemQty=" + itemQty + "]";
	}


	
}
