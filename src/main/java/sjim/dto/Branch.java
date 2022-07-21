package sjim.dto;

public class Branch {
	private int branchNum;
	private String branchName;
	private String branchAddress;
	private String branchPhone;

	@Override
	public String toString() {
		return "Branch [branchNum=" + branchNum + ", branchName=" + branchName + ", branchAddress=" + branchAddress
				+ ", branchPhone=" + branchPhone + "]";
	}

	public int getBranchNum() {
		return branchNum;
	}

	public void setBranchNum(int branchNum) {
		this.branchNum = branchNum;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchPhone() {
		return branchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

	
}
