package pochacm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Items {
	private int itemNum;
	private int brandNum;
	private int cateNum;
	private String itemName;
	private String itemCode;
	private int orderUnitNum;
	private float unitPrice;
	private int primaryUnitNum;
	private int secondaryUnitNum;
	private int targetWastePercentage;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiryDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lastUpdateDate;
	private int userNum;
	@Override
	public String toString() {
		return "Item [itemNum=" + itemNum + ", brandNum=" + brandNum + ", cateNum=" + cateNum + ", itemName=" + itemName
				+ ", itemCode=" + itemCode + ", orderUnitNum=" + orderUnitNum + ", unitPrice=" + unitPrice
				+ ", primaryUnitNum=" + primaryUnitNum + ", secondaryUnitNum=" + secondaryUnitNum
				+ ", targetWastePercentage=" + targetWastePercentage + ", expiryDate=" + expiryDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", userNum=" + userNum + "]";
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
	public int getCateNum() {
		return cateNum;
	}
	public void setCateNum(int cateNum) {
		this.cateNum = cateNum;
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
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
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
	public int getTargetWastePercentage() {
		return targetWastePercentage;
	}
	public void setTargetWastePercentage(int targetWastePercentage) {
		this.targetWastePercentage = targetWastePercentage;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}


	
	
	
}
