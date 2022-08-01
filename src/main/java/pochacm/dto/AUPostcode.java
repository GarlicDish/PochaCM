package pochacm.dto;

public class AUPostcode {
	private int postcode;
	   private String place_name;
	   private String state_name;
	   private String state_code;
	   private int latitude;
	   private int longitude;
	   private int accuracy;
	@Override
	public String toString() {
		return "AUPostcode [postcode=" + postcode + ", place_name=" + place_name + ", state_name=" + state_name
				+ ", state_code=" + state_code + ", latitude=" + latitude + ", longitude=" + longitude + ", accuracy="
				+ accuracy + "]";
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
}
