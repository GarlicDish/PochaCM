package pochacm.dto;

public class Pagination{
    public int limit;
    public int page;
    public int total;
	@Override
	public String toString() {
		return "Pagination [limit=" + limit + ", page=" + page + ", total=" + total + "]";
	}
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
    
}
