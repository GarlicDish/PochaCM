package pochacm.dto;

public class PurchaseInvoiceItem{

	private int purchaseInvoiceItemNum;
	private int itemNum;
	private int purchaseInvoiceNum;
	private int qty;
	@Override
	public String toString() {
		return "PurchaseInvoiceItem [purchaseInvoiceItemNum=" + purchaseInvoiceItemNum + ", itemNum=" + itemNum
				+ ", purchaseInvoiceNum=" + purchaseInvoiceNum + ", qty=" + qty + "]";
	}
	public int getPurchaseInvoiceItemNum() {
		return purchaseInvoiceItemNum;
	}
	public void setPurchaseInvoiceItemNum(int purchaseInvoiceItemNum) {
		this.purchaseInvoiceItemNum = purchaseInvoiceItemNum;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getPurchaseInvoiceNum() {
		return purchaseInvoiceNum;
	}
	public void setPurchaseInvoiceNum(int purchaseInvoiceNum) {
		this.purchaseInvoiceNum = purchaseInvoiceNum;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
