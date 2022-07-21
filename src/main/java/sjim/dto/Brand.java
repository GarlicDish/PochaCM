package sjim.dto;


public class Brand {
	private int brandNum;
	private String brandName;

	@Override
	public String toString() {
		return "Brand [brandNum=" + brandNum + ", brandName=" + brandName + "]";
	}

	public int getBrandNum() {
		return brandNum;
	}

	public void setBrandNum(int brandNum) {
		this.brandNum = brandNum;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	
}
