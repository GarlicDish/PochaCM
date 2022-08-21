package pochacm.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Invoice{
    public ArrayList<Item> items;
    public String updatedAt;
    public String refundReason;
    public String referenceId;
    public String invoiceNumber;
    public int type;
    public int status;
    public String source;
    public String location;
    public String table;
    public int guest;
    public String createdAt;
    public float surcharge;
    public float tips;
    public int rounding;
    public float totalExcludeTax;
    public float total;
    public float discountExcludeTax;
    public float discountTax;
    public float shippingCost;
    public String shippingDate;
    public float tax;
    public float taxRate;
    public boolean isPaid;
    public boolean isLiability;
    public String memberCode;
    public String firstName;
    public String lastName;
    public String email;
    public String uniqueCode;
    public ShippingAddress shippingAddress;
    public BillingAddress billingAddress;
    public ArrayList<Payment> payments;
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
				+ ", payments=" + payments + "]";
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
	public ArrayList<Payment> getPayments() {
		return payments;
	}
	public void setPayments(ArrayList<Payment> payments) {
		this.payments = payments;
	}
    
    
}
