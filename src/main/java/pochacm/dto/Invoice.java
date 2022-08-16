package pochacm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Invoice {

	private int invoiceNum;
	private String invoiceSerial;
	private int userNum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date invoiceDate;
	private int qty;
	private int supplierNum;
	@Override
	public String toString() {
		return "Invoice [invoiceNum=" + invoiceNum + ", invoiceSerial=" + invoiceSerial + ", userNum=" + userNum
				+ ", invoiceDate=" + invoiceDate + ", qty=" + qty + ", supplierNum=" + supplierNum + "]";
	}
	public int getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(int invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public String getInvoiceSerial() {
		return invoiceSerial;
	}
	public void setInvoiceSerial(String invoiceSerial) {
		this.invoiceSerial = invoiceSerial;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
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
	public int getSupplierNum() {
		return supplierNum;
	}
	public void setSupplierNum(int supplierNum) {
		this.supplierNum = supplierNum;
	}
	
	
	
}
