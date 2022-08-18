package pochacm.dto;

import java.util.ArrayList;

public class SalesAPI {
	
	private Pagination pagination;
	private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

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

	static class Pagination{
		private int limit;
		private int page;
		private int total;
		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		@Override
		public String toString() {
			return "Pagination [limit=" + limit + ", page=" + page + ", total=" + total + "]";
		}
		
	}
	
	
}