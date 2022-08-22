package pochacm.dto;

public class SalesInvoice {
	private int salesNum;
	private String menu;
	private String salesInvoiceSerialNum;
	private String salesDate;
	private float tax;
	private float price;
	private int qty;
	private String salesSource;
	private Boolean isRefunded;
	@Override
	public String toString() {
		return "SalesInvoice [salesNum=" + salesNum + ", menu=" + menu + ", salesInvoiceSerialNum="
				+ salesInvoiceSerialNum + ", salesDate=" + salesDate + ", tax=" + tax + ", price=" + price + ", qty="
				+ qty + ", salesSource=" + salesSource + ", isRefunded=" + isRefunded + "]";
	}
	public int getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getSalesInvoiceSerialNum() {
		return salesInvoiceSerialNum;
	}
	public void setSalesInvoiceSerialNum(String salesInvoiceSerialNum) {
		this.salesInvoiceSerialNum = salesInvoiceSerialNum;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getSalesSource() {
		return salesSource;
	}
	public void setSalesSource(String salesSource) {
		this.salesSource = salesSource;
	}
	public Boolean getIsRefunded() {
		return isRefunded;
	}
	public void setIsRefunded(Boolean isRefunded) {
		this.isRefunded = isRefunded;
	}
	
	
	
}
