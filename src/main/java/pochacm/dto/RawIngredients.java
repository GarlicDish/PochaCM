package pochacm.dto;

public class RawIngredients {
	private int rawIngredientsNum;
	private int recipeNum;
	private int itemNum;
	private int itemQty;
	
	@Override
	public String toString() {
		return "RawIngredients [rawIngredientsNum=" + rawIngredientsNum + ", recipeNum=" + recipeNum + ", itemNum="
				+ itemNum + ", itemQty=" + itemQty + "]";
	}
	public int getRawIngredientsNum() {
		return rawIngredientsNum;
	}
	public void setRawIngredientsNum(int rawIngredientsNum) {
		this.rawIngredientsNum = rawIngredientsNum;
	}
	public int getRecipeNum() {
		return recipeNum;
	}
	public void setRecipeNum(int recipeNum) {
		this.recipeNum = recipeNum;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	

}
