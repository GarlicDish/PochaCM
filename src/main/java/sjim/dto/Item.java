package sjim.dto;

import java.util.Date;

public class Item {
	private int itemNum;
	private String itemName;
	private int itemUnitQty;
	private int itemOrderUnitPrice;
	private Date itemExpire;
	private int targetWastePercentage;
	private int itemCateNum;
	private int unitNum;
	private int supplierNum;
	private int brandNum;
	private int orderUnitNum;
	
	@Override
	public String toString() {
		return "Item [brandNum=" + brandNum + ", itemCateNum=" + itemCateNum + ", itemExpire=" + itemExpire
				+ ", itemName=" + itemName + ", itemNum=" + itemNum + ", itemOrderUnitPrice=" + itemOrderUnitPrice
				+ ", itemUnitQty=" + itemUnitQty + ", orderUnitNum=" + orderUnitNum + ", supplierNum=" + supplierNum
				+ ", targetWastePercentage=" + targetWastePercentage + ", unitNum=" + unitNum + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public Date getItemExpire() {
		return itemExpire;
	}
	public void setItemExpire(Date itemExpire) {
		this.itemExpire = itemExpire;
	}
	public int getTargetWastePercentage() {
		return targetWastePercentage;
	}
	public void setTargetWastePercentage(int targetWastePercentage) {
		this.targetWastePercentage = targetWastePercentage;
	}
	public int getItemCateNum() {
		return itemCateNum;
	}
	public void setItemCateNum(int itemCateNum) {
		this.itemCateNum = itemCateNum;
	}
	public int getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(int unitNum) {
		this.unitNum = unitNum;
	}
	public int getSupplierNum() {
		return supplierNum;
	}
	public void setSupplierNum(int supplierNum) {
		this.supplierNum = supplierNum;
	}
	public int getBrandNum() {
		return brandNum;
	}
	public void setBrandNum(int brandNum) {
		this.brandNum = brandNum;
	}
	public int getOrderUnitNum() {
		return orderUnitNum;
	}
	public void setOrderUnitNum(int orderUnitNum) {
		this.orderUnitNum = orderUnitNum;
	}
	
	
	
}
