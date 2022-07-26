package pochacm.dto;

import java.sql.Date;

public class User {

	private int userNum;
	private String userEmail;
	private String userPassword;
	private String userName;
	private int userGender;
	private String userPhone;
	private Date dateOfBirth;
	private int branchNum;
	private int positionNum;
	private Date workStartDate;
	private String emerName;
	private String emerPhone;
	private int taxFileCheck;
	private int taxFileNum;
	private int bsbNum;
	private int bankAccountNum;
	private String saFundName;
	private String saFundNum;
	private String homeAddressL1;
	private String homeAddressL2;
	private String homeAddressState;
	private String homeAddressSub;
	private int homeAddressPostCode;
	private int termsAgree;
	private Date signUpDate;
	private int userActivate;
	
	@Override
	public String toString() {
		return "User [bankAccountNum=" + bankAccountNum + ", branchNum=" + branchNum + ", bsbNum=" + bsbNum
				+ ", dateOfBirth=" + dateOfBirth + ", emerName=" + emerName + ", emerPhone=" + emerPhone
				+ ", homeAddressL1=" + homeAddressL1 + ", homeAddressL2=" + homeAddressL2 + ", homeAddressPostCode="
				+ homeAddressPostCode + ", homeAddressState=" + homeAddressState + ", homeAddressSub=" + homeAddressSub
				+ ", positionNum=" + positionNum + ", saFundName=" + saFundName + ", saFundNum=" + saFundNum
				+ ", signUpDate=" + signUpDate + ", taxFileCheck=" + taxFileCheck + ", taxFileNum=" + taxFileNum
				+ ", termsAgree=" + termsAgree + ", userActivate=" + userActivate + ", userEmail=" + userEmail
				+ ", userGender=" + userGender + ", userName=" + userName + ", userNum=" + userNum + ", userPassword="
				+ userPassword + ", userPhone=" + userPhone + ", workStartDate=" + workStartDate + "]";
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserGender() {
		return userGender;
	}
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public Date getWorkStartDate() {
		return workStartDate;
	}
	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}
	public String getEmerName() {
		return emerName;
	}
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	public String getEmerPhone() {
		return emerPhone;
	}
	public void setEmerPhone(String emerPhone) {
		this.emerPhone = emerPhone;
	}
	public int getTaxFileCheck() {
		return taxFileCheck;
	}
	public void setTaxFileCheck(int taxFileCheck) {
		this.taxFileCheck = taxFileCheck;
	}
	public int getTaxFileNum() {
		return taxFileNum;
	}
	public void setTaxFileNum(int taxFileNum) {
		this.taxFileNum = taxFileNum;
	}
	public int getBsbNum() {
		return bsbNum;
	}
	public void setBsbNum(int bsbNum) {
		this.bsbNum = bsbNum;
	}
	public int getBankAccountNum() {
		return bankAccountNum;
	}
	public void setBankAccountNum(int bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}
	public String getSaFundName() {
		return saFundName;
	}
	public void setSaFundName(String saFundName) {
		this.saFundName = saFundName;
	}
	public String getSaFundNum() {
		return saFundNum;
	}
	public void setSaFundNum(String saFundNum) {
		this.saFundNum = saFundNum;
	}
	public String getHomeAddressL1() {
		return homeAddressL1;
	}
	public void setHomeAddressL1(String homeAddressL1) {
		this.homeAddressL1 = homeAddressL1;
	}
	public String getHomeAddressL2() {
		return homeAddressL2;
	}
	public void setHomeAddressL2(String homeAddressL2) {
		this.homeAddressL2 = homeAddressL2;
	}
	public String getHomeAddressState() {
		return homeAddressState;
	}
	public void setHomeAddressState(String homeAddressState) {
		this.homeAddressState = homeAddressState;
	}
	public String getHomeAddressSub() {
		return homeAddressSub;
	}
	public void setHomeAddressSub(String homeAddressSub) {
		this.homeAddressSub = homeAddressSub;
	}
	public int getHomeAddressPostCode() {
		return homeAddressPostCode;
	}
	public void setHomeAddressPostCode(int homeAddressPostCode) {
		this.homeAddressPostCode = homeAddressPostCode;
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
	
	
	
	
}
