package pochacm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Pagination{
    public int limit;
    public int page;
    public int total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date dateParam;
	@Override
	public String toString() {
		return "Pagination [limit=" + limit + ", page=" + page + ", total=" + total + ", dateParam=" + dateParam + "]";
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
	public Date getDateParam() {
		return dateParam;
	}
	public void setDateParam(Date dateParam) {
		this.dateParam = dateParam;
	}
	
    
    
}
