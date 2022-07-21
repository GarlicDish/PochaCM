package sjim.dto;

import java.sql.Date;

public class InventoryChange {

	private int inventoryChangeNum;
	private int userNum;
	private int itemNum;
	private Date changeDate;
	private int changeQty;
	private int incDec;

	@Override
	public String toString() {
		return "InventoryChange [inventoryChangeNum=" + inventoryChangeNum + ", userNum=" + userNum + ", itemNum="
				+ itemNum + ", changeDate=" + changeDate + ", changeQty=" + changeQty + ", incDec=" + incDec + "]";
	}
	public int getInventoryChangeNum() {
		return inventoryChangeNum;
	}
	public void setInventoryChangeNum(int inventoryChangeNum) {
		this.inventoryChangeNum = inventoryChangeNum;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public int getChangeQty() {
		return changeQty;
	}
	public void setChangeQty(int changeQty) {
		this.changeQty = changeQty;
	}
	public int getIncDec() {
		return incDec;
	}
	public void setIncDec(int incDec) {
		this.incDec = incDec;
	}
	
	
	
}
