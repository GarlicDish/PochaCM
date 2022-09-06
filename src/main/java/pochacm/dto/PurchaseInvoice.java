package pochacm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PurchaseInvoice {

	private int purchaseInvoiceNum;
	private String purchaseInvoiceSerial;
	private int userNum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date purchaseInvoiceDate;
	private int supplierNum;
	
	@Override
	public String toString() {
		return "PurchaseInvoice [purchaseInvoiceNum=" + purchaseInvoiceNum + ", purchaseInvoiceSerial="
				+ purchaseInvoiceSerial + ", userNum=" + userNum + ", purchaseInvoiceDate=" + purchaseInvoiceDate
				+ ", supplierNum=" + supplierNum + "]";
	}
	public int getPurchaseInvoiceNum() {
		return purchaseInvoiceNum;
	}
	public void setPurchaseInvoiceNum(int purchaseInvoiceNum) {
		this.purchaseInvoiceNum = purchaseInvoiceNum;
	}
	public String getPurchaseInvoiceSerial() {
		return purchaseInvoiceSerial;
	}
	public void setPurchaseInvoiceSerial(String purchaseInvoiceSerial) {
		this.purchaseInvoiceSerial = purchaseInvoiceSerial;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public Date getPurchaseInvoiceDate() {
		return purchaseInvoiceDate;
	}
	public void setPurchaseInvoiceDate(Date purchaseInvoiceDate) {
		this.purchaseInvoiceDate = purchaseInvoiceDate;
	}
	public int getSupplierNum() {
		return supplierNum;
	}
	public void setSupplierNum(int supplierNum) {
		this.supplierNum = supplierNum;
	}
	
	
	
	
	
	
}
