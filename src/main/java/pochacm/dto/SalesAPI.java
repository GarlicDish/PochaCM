package pochacm.dto;

import java.util.ArrayList;


public class SalesAPI {

    public Pagination pagination;
    public ArrayList<Invoice> invoices;
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ArrayList<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(ArrayList<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "SalesAPI [pagination=" + pagination + ", invoices=" + invoices + "]";
	}

}