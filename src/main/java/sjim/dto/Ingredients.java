package sjim.dto;

public class Ingredients {
	private int ingredientsNum;
	private int menuNum;
	private int itemNum;
	private int itemQty;

	@Override
	public String toString() {
		return "Ingredients [ingredientsNum=" + ingredientsNum + ", menuNum=" + menuNum + ", itemNum=" + itemNum
				+ ", itemQty=" + itemQty + "]";
	}

	public int getIngredientsNum() {
		return ingredientsNum;
	}

	public void setIngredientsNum(int ingredientsNum) {
		this.ingredientsNum = ingredientsNum;
	}

	public int getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
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
