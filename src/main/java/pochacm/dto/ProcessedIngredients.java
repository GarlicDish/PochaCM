package pochacm.dto;

public class ProcessedIngredients {
	private int processedIngredientsNum;
	private int recipeNum;
	private int ingredientsNum;
	private int itemQty;
	
	@Override
	public String toString() {
		return "ProcessedIngredients [processedIngredientsNum=" + processedIngredientsNum + ", recipeNum=" + recipeNum
				+ ", ingredientsNum=" + ingredientsNum + ", itemQty=" + itemQty + "]";
	}
	public int getProcessedIngredientsNum() {
		return processedIngredientsNum;
	}
	public void setProcessedIngredientsNum(int processedIngredientsNum) {
		this.processedIngredientsNum = processedIngredientsNum;
	}
	public int getRecipeNum() {
		return recipeNum;
	}
	public void setRecipeNum(int recipeNum) {
		this.recipeNum = recipeNum;
	}
	public int getIngredientsNum() {
		return ingredientsNum;
	}
	public void setIngredientsNum(int ingredientsNum) {
		this.ingredientsNum = ingredientsNum;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	


	
}
