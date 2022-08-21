package pochacm.dto;

public class SalesShowAPI{
    public Status status;
    public Invoice invoice;
	@Override
	public String toString() {
		return "SalesShowAPI [status=" + status + ", invoice=" + invoice + "]";
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
    
    
}
