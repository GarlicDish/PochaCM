package sjim.dto;

import java.sql.Date;

public class User {

	private int userNum;
	private String userName;
	private String userID;
	private String userPassword;
	private String userPhone;
	private String userEmail;
	private int termsAgree;
	private Date signUpDate;
	private int userActivate;
	private int branchNum;
	private int positionNum;
	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", userName=" + userName + ", userID=" + userID + ", userPassword="
				+ userPassword + ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", termsAgree=" + termsAgree
				+ ", signUpDate=" + signUpDate + ", userActivate=" + userActivate + ", branchNum=" + branchNum
				+ ", positionNum=" + positionNum + "]";
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getTermsAgree() {
		return termsAgree;
	}
	public void setTermsAgree(int termsAgree) {
		this.termsAgree = termsAgree;
	}
	public Date getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	public int getUserActivate() {
		return userActivate;
	}
	public void setUserActivate(int userActivate) {
		this.userActivate = userActivate;
	}
	public int getBranchNum() {
		return branchNum;
	}
	public void setBranchNum(int branchNum) {
		this.branchNum = branchNum;
	}
	public int getPositionNum() {
		return positionNum;
	}
	public void setPositionNum(int positionNum) {
		this.positionNum = positionNum;
	}
	
}
