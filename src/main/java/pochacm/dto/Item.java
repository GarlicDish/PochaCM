package pochacm.dto;

import java.util.Date;

public class Item {
	private int itemNum;
	private int brandNum;
	private int supplierNum;
	private int itemCateNum;
	private String itemName;
	private String itemCode;
	private int orderUnitNum;
	private int itemUnitQty;
	private int itemOrderUnitPrice;
	private int primaryUnitNum;
	private int secondaryUnitNum;
	private int itemTarget_waste_percent;
	private Date itemExpiryDate;
	private Date itemLastUpdate;
	private int userNum;
	
	@Override
	public String toString() {
		return "Item [itemNum=" + itemNum + ", brandNum=" + brandNum + ", supplierNum=" + supplierNum + ", itemCateNum="
				+ itemCateNum + ", itemName=" + itemName + ", itemCode=" + itemCode + ", orderUnitNum=" + orderUnitNum
				+ ", itemUnitQty=" + itemUnitQty + ", itemOrderUnitPrice=" + itemOrderUnitPrice + ", primaryUnitNum="
				+ primaryUnitNum + ", secondaryUnitNum=" + secondaryUnitNum + ", itemTarget_waste_percent="
				+ itemTarget_waste_percent + ", itemExpiryDate=" + itemExpiryDate + ", itemLastUpdate=" + itemLastUpdate
				+ ", userNum=" + userNum + "]";
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getBrandNum() {
		return brandNum;
	}
	public void setBrandNum(int brandNum) {
		this.brandNum = brandNum;
	}
	public int getSupplierNum() {
		return supplierNum;
	}
	public void setSupplierNum(int supplierNum) {
		this.supplierNum = supplierNum;
	}
	public int getItemCateNum() {
		return itemCateNum;
	}
	public void setItemCateNum(int itemCateNum) {
		this.itemCateNum = itemCateNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getOrderUnitNum() {
		return orderUnitNum;
	}
	public void setOrderUnitNum(int orderUnitNum) {
		this.orderUnitNum = orderUnitNum;
	}
	public int getItemUnitQty() {
		return itemUnitQty;
	}
	public void setItemUnitQty(int itemUnitQty) {
		this.itemUnitQty = itemUnitQty;
	}
	public int getItemOrderUnitPrice() {
		return itemOrderUnitPrice;
	}
	public void setItemOrderUnitPrice(int itemOrderUnitPrice) {
		this.itemOrderUnitPrice = itemOrderUnitPrice;
	}
	public int getPrimaryUnitNum() {
		return primaryUnitNum;
	}
	public void setPrimaryUnitNum(int primaryUnitNum) {
		this.primaryUnitNum = primaryUnitNum;
	}
	public int getSecondaryUnitNum() {
		return secondaryUnitNum;
	}
	public void setSecondaryUnitNum(int secondaryUnitNum) {
		this.secondaryUnitNum = secondaryUnitNum;
	}
	public int getItemTarget_waste_percent() {
		return itemTarget_waste_percent;
	}
	public void setItemTarget_waste_percent(int itemTarget_waste_percent) {
		this.itemTarget_waste_percent = itemTarget_waste_percent;
	}
	public Date getItemExpiryDate() {
		return itemExpiryDate;
	}
	public void setItemExpiryDate(Date itemExpiryDate) {
		this.itemExpiryDate = itemExpiryDate;
	}
	public Date getItemLastUpdate() {
		return itemLastUpdate;
	}
	public void setItemLastUpdate(Date itemLastUpdate) {
		this.itemLastUpdate = itemLastUpdate;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	
	
	
	
}
