package pochacm.dto;

import java.sql.Date;

public class Invoice {

	private int invoice_num;
	private int user_num;
	private int item_num;
	private Date invoice_date;
	private int qty;
	public int getInvoice_num() {
		return invoice_num;
	}
	public void setInvoice_num(int invoice_num) {
		this.invoice_num = invoice_num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public Date getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "Invoice [invoice_num=" + invoice_num + ", user_num=" + user_num + ", item_num=" + item_num
				+ ", invoice_date=" + invoice_date + ", qty=" + qty + "]";
	}
	
	
}
