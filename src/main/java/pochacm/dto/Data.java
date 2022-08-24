package pochacm.dto;

public class Data {

	private String createdOn;
	private int createdBy;
	private String createdUsername;
	
	@Override
	public String toString() {
		return "Data [createdOn=" + createdOn + ", createdBy=" + createdBy + ", createdUsername=" + createdUsername
				+ "]";
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedUsername() {
		return createdUsername;
	}
	public void setCreatedUsername(String createdUsername) {
		this.createdUsername = createdUsername;
	}
	
}
