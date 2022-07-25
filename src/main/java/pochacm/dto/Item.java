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
	private String primaryUnitNum;
	private String secondaryUnitNum;
	private int targetWastePercentage;
	private Date itemExpire;
	private Date lastUpdateDate;
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
	public String getPrimaryUnitNum() {
		return primaryUnitNum;
	}
	public void setPrimaryUnitNum(String primaryUnitNum) {
		this.primaryUnitNum = primaryUnitNum;
	}
	public String getSecondaryUnitNum() {
		return secondaryUnitNum;
	}
	public void setSecondaryUnitNum(String secondaryUnitNum) {
		this.secondaryUnitNum = secondaryUnitNum;
	}
	public int getTargetWastePercentage() {
		return targetWastePercentage;
	}
	public void setTargetWastePercentage(int targetWastePercentage) {
		this.targetWastePercentage = targetWastePercentage;
	}
	public Date getItemExpire() {
		return itemExpire;
	}
	public void setItemExpire(Date itemExpire) {
		this.itemExpire = itemExpire;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	@Override
	public String toString() {
		return "Item [itemNum=" + itemNum + ", brandNum=" + brandNum + ", supplierNum=" + supplierNum + ", itemCateNum="
				+ itemCateNum + ", itemName=" + itemName + ", itemCode=" + itemCode + ", orderUnitNum=" + orderUnitNum
				+ ", itemUnitQty=" + itemUnitQty + ", itemOrderUnitPrice=" + itemOrderUnitPrice + ", primaryUnitNum="
				+ primaryUnitNum + ", secondaryUnitNum=" + secondaryUnitNum + ", targetWastePercentage="
				+ targetWastePercentage + ", itemExpire=" + itemExpire + ", lastUpdateDate=" + lastUpdateDate + "]";
	}
	
	
	
	
}
