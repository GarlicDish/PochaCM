package sjim.dto;

public class Supplier {
	private int supplierNum;
	private String supplierName;
	@Override
	public String toString() {
		return "Supplier [supplierNum=" + supplierNum + ", supplierName=" + supplierName + "]";
	}
	public int getSupplierNum() {
		return supplierNum;
	}
	public void setSupplierNum(int supplierNum) {
		this.supplierNum = supplierNum;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	
}
