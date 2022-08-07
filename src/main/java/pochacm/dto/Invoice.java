package pochacm.dto;

import java.util.Date;

public class Invoice {

	private int invoiceNum;
	private String invoiceSerial;
	private int userNum;
	private int itemNum;
	private Date invoiceDate;
	private int qty;
	
	@Override
	public String toString() {
		return "Invoice [invoiceNum=" + invoiceNum + ", invoiceSerial=" + invoiceSerial + ", userNum=" + userNum
				+ ", itemNum=" + itemNum + ", invoiceDate=" + invoiceDate + ", qty=" + qty + "]";
	}
	public int getInvoiceNum() {
		return invoiceNum;
	}
	public String getInvoiceSerial() {
		return invoiceSerial;
	}
	public void setInvoiceSerial(String invoiceSerial) {
		this.invoiceSerial = invoiceSerial;
	}
	public void setInvoiceNum(int invoiceNum) {
		this.invoiceNum = invoiceNum;
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
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
}
