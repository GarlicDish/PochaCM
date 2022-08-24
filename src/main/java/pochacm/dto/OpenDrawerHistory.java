package pochacm.dto;

import java.util.ArrayList;

public class OpenDrawerHistory {

	private Pagination pagination;
	private ArrayList<Data> data;
	
	@Override
	public String toString() {
		return "OpenDrawerHistory [pagination=" + pagination + ", data=" + data + "]";
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public ArrayList<Data> getData() {
		return data;
	}
	public void setData(ArrayList<Data> data) {
		this.data = data;
	}
	
	
}
