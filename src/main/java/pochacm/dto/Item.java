package pochacm.dto;

public class Item{
	
    public String refundOn;
    public String refundReason;
    public boolean isRefund;
    public String description;
    public String pictureURL;
    public String itemName;
    public int quantity;
    public float price;
    public float tax;
    public int productId;
    public float discount;
    public boolean isGiftCard;
    public int productVariant;
    
	@Override
	public String toString() {
		return "Item [refundOn=" + refundOn + ", refundReason=" + refundReason + ", isRefund=" + isRefund
				+ ", description=" + description + ", pictureURL=" + pictureURL + ", itemName=" + itemName
				+ ", quantity=" + quantity + ", price=" + price + ", tax=" + tax + ", productId=" + productId
				+ ", discount=" + discount + ", isGiftCard=" + isGiftCard + ", productVariant=" + productVariant
				+ "]";
	}
	public String getRefundOn() {
		return refundOn;
	}
	public void setRefundOn(String refundOn) {
		this.refundOn = refundOn;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public boolean isRefund() {
		return isRefund;
	}
	public void setRefund(boolean isRefund) {
		this.isRefund = isRefund;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public boolean isGiftCard() {
		return isGiftCard;
	}
	public void setGiftCard(boolean isGiftCard) {
		this.isGiftCard = isGiftCard;
	}
	public int getProductVariant() {
		return productVariant;
	}
	public void setProductVariant(int productVariant) {
		this.productVariant = productVariant;
	}
    
    
}
