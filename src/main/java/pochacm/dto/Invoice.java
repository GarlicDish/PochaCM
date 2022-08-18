package pochacm.dto;

import java.util.ArrayList;

public class Invoice {

	private ArrayList<Item> items = new ArrayList<Item>();
	private String updatedAt;
	private String refundReason;
	private String referenceId;	
	private String invoiceNumber;
	private int type;
	private int status;
	private String source;
	private String location;
	private String table;
	private int guest;
	private String createdAt;
	private float surcharge;
	private float tips;
	private int rounding;
	private float totalExcludeTax;
	private float total;
	private float discountExcludeTax;
	private float discountTax;
	private float shippingCost;
	private String shippingDate;
	private float tax;
	private float taxRate;
	private boolean isPaid;
	private boolean isLiability;
	private String memberCode;
	private String firstName;
	private String lastName;
	private String email;
	private String uniqueCode;
	private ShippingAddress shippingAddress;
	private BillingAddress billingAddress;
	private ArrayList<Payment> payment = new ArrayList<Payment>();
	@Override
	public String toString() {
		return "Invoice [items=" + items + ", updatedAt=" + updatedAt + ", refundReason=" + refundReason
				+ ", referenceId=" + referenceId + ", invoiceNumber=" + invoiceNumber + ", type=" + type
				+ ", status=" + status + ", source=" + source + ", location=" + location + ", table=" + table
				+ ", guest=" + guest + ", createdAt=" + createdAt + ", surcharge=" + surcharge + ", tips=" + tips
				+ ", rounding=" + rounding + ", totalExcludeTax=" + totalExcludeTax + ", total=" + total
				+ ", discountExcludeTax=" + discountExcludeTax + ", discountTax=" + discountTax + ", shippingCost="
				+ shippingCost + ", shippingDate=" + shippingDate + ", tax=" + tax + ", taxRate=" + taxRate
				+ ", isPaid=" + isPaid + ", isLiability=" + isLiability + ", memberCode=" + memberCode
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", uniqueCode="
				+ uniqueCode + ", shippingAddress=" + shippingAddress + ", billingAddress=" + billingAddress
				+ ", payment=" + payment + "]";
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public int getGuest() {
		return guest;
	}
	public void setGuest(int guest) {
		this.guest = guest;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public float getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(float surcharge) {
		this.surcharge = surcharge;
	}
	public float getTips() {
		return tips;
	}
	public void setTips(float tips) {
		this.tips = tips;
	}
	public int getRounding() {
		return rounding;
	}
	public void setRounding(int rounding) {
		this.rounding = rounding;
	}
	public float getTotalExcludeTax() {
		return totalExcludeTax;
	}
	public void setTotalExcludeTax(float totalExcludeTax) {
		this.totalExcludeTax = totalExcludeTax;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public float getDiscountExcludeTax() {
		return discountExcludeTax;
	}
	public void setDiscountExcludeTax(float discountExcludeTax) {
		this.discountExcludeTax = discountExcludeTax;
	}
	public float getDiscountTax() {
		return discountTax;
	}
	public void setDiscountTax(float discountTax) {
		this.discountTax = discountTax;
	}
	public float getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public float getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public boolean isLiability() {
		return isLiability;
	}
	public void setLiability(boolean isLiability) {
		this.isLiability = isLiability;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	public ArrayList<Payment> getPayment() {
		return payment;
	}
	public void setPayment(ArrayList<Payment> payment) {
		this.payment = payment;
	}
	static class ShippingAddress {
		private String firstName;
		private String lastName;
		private String email;
		private String phone;
		private String address;
		private String suburb;
		private String postcode;
		private String state;
		private String country;
		private String companyName;
		@Override
		public String toString() {
			return "ShippingAddress [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", phone=" + phone + ", address=" + address + ", suburb=" + suburb + ", postcode=" + postcode
					+ ", state=" + state + ", country=" + country + ", companyName=" + companyName + "]";
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getSuburb() {
			return suburb;
		}
		public void setSuburb(String suburb) {
			this.suburb = suburb;
		}
		public String getPostcode() {
			return postcode;
		}
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		
		
	}
	
	static class BillingAddress{
		
		private String firstName;
		private String lastName;
		private String email;
		private String phone;
		private String address;
		private String suburb;
		private String postcode;
		private String state;
		private String country;
		private String companyName;
		@Override
		public String toString() {
			return "BillingAddress [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone="
					+ phone + ", address=" + address + ", suburb=" + suburb + ", postcode=" + postcode + ", state="
					+ state + ", country=" + country + ", companyName=" + companyName + "]";
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getSuburb() {
			return suburb;
		}
		public void setSuburb(String suburb) {
			this.suburb = suburb;
		}
		public String getPostcode() {
			return postcode;
		}
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
      
	}
	static class Payment{
		private String paymentMethod;
		private float amount;
        private String transactionNumber;
        private String transactionReference;
        private String merchantCopy;
		@Override
		public String toString() {
			return "Payment [paymentMethod=" + paymentMethod + ", amount=" + amount + ", transactionNumber="
					+ transactionNumber + ", transactionReference=" + transactionReference + ", merchantCopy="
					+ merchantCopy + "]";
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
		public float getAmount() {
			return amount;
		}
		public void setAmount(float amount) {
			this.amount = amount;
		}
		public String getTransactionNumber() {
			return transactionNumber;
		}
		public void setTransactionNumber(String transactionNumber) {
			this.transactionNumber = transactionNumber;
		}
		public String getTransactionReference() {
			return transactionReference;
		}
		public void setTransactionReference(String transactionReference) {
			this.transactionReference = transactionReference;
		}
		public String getMerchantCopy() {
			return merchantCopy;
		}
		public void setMerchantCopy(String merchantCopy) {
			this.merchantCopy = merchantCopy;
		}
        
	}
	     
	static class Item{
		private String refundOn;
		private String refundReason;
		private boolean isRefund;
		private String description;
		private String pictureURL;
		private String itemName;
		private int quantity;
		private float price;
		private float tax;
		private int productId;
		private float discount;
		private boolean isGiftCard;
		private int productVariant;
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
}
