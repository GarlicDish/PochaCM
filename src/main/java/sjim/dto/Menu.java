package sjim.dto;



public class Menu {
	private int menuNum;
	private String menuName;
	private int menuPrice;
	@Override
	public String toString() {
		return "Menu [menuNum=" + menuNum + ", menuName=" + menuName + ", menuPrice=" + menuPrice + "]";
	}
	public int getMenuNum() {
		return menuNum;
	}
	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	
	
}
