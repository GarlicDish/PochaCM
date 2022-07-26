package pochacm.dto;



public class Recipe {
	private int recipeNum;
	private String recipeName;
	private int recipePrice;
	private int classfication;
	
	@Override
	public String toString() {
		return "Recipe [recipeNum=" + recipeNum + ", recipeName=" + recipeName + ", recipePrice=" + recipePrice
				+ ", classfication=" + classfication + "]";
	}
	public int getRecipeNum() {
		return recipeNum;
	}
	public void setRecipeNum(int recipeNum) {
		this.recipeNum = recipeNum;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public int getRecipePrice() {
		return recipePrice;
	}
	public void setRecipePrice(int recipePrice) {
		this.recipePrice = recipePrice;
	}
	public int getClassfication() {
		return classfication;
	}
	public void setClassfication(int classfication) {
		this.classfication = classfication;
	}

	
	
 
	
	
	
}
