package pochacm.dto;

public class InvoiceItem{

	private int invoiceItemNum;
	private int itemNum;
	private int invoiceNum;
	private int qty;
	
	@Override
	public String toString() {
		return "invoiceItem [invoiceItemNum=" + invoiceItemNum + ", itemNum=" + itemNum + ", invoiceNum=" + invoiceNum
				+ ", qty=" + qty + "]";
	}
	public int getInvoiceItemNum() {
		return invoiceItemNum;
	}
	public void setInvoiceItemNum(int invoiceItemNum) {
		this.invoiceItemNum = invoiceItemNum;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(int invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
