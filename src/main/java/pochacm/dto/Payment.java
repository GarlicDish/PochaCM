package pochacm.dto;

public class Payment{
    public String paymentMethod;
    public float amount;
    public String transactionNumber;
    public String transactionReference;
    public String merchantCopy;
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
