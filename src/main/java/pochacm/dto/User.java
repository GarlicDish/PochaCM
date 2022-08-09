package pochacm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	private int userNum;
	private String userEmail;
	private String userPassword;
	private String userName;
	private int userGender;
	private String userPhone;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	private String addressL1;
	private String addressL2;
	private String addressState;
	private String addressSub;
	private int addressPostCode;
	private int branchNum;
	private int positionNum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date workStartDate;
	private String emerName;
	private String emerPhone;
	private int taxFileCheck;
	private long taxFileNum;
	private int bsbNum;
	private long bankAccountNum;
	private String saFundName;
	private String saFundNum;
	private int termsAgree;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date signUpDate;
	private int validationBySup;
	private int userActivate;
	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", userEmail=" + userEmail + ", userPassword=" + userPassword
				+ ", userName=" + userName + ", userGender=" + userGender + ", userPhone=" + userPhone
				+ ", dateOfBirth=" + dateOfBirth + ", addressL1=" + addressL1 + ", addressL2=" + addressL2
				+ ", addressState=" + addressState + ", addressSub=" + addressSub + ", addressPostCode="
				+ addressPostCode + ", branchNum=" + branchNum + ", positionNum=" + positionNum + ", workStartDate="
				+ workStartDate + ", emerName=" + emerName + ", emerPhone=" + emerPhone + ", taxFileCheck="
				+ taxFileCheck + ", taxFileNum=" + taxFileNum + ", bsbNum=" + bsbNum + ", bankAccountNum="
				+ bankAccountNum + ", saFundName=" + saFundName + ", saFundNum=" + saFundNum + ", termsAgree="
				+ termsAgree + ", signUpDate=" + signUpDate + ", validationBySup=" + validationBySup + ", userActivate="
				+ userActivate + "]";
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
	public String getAddressL1() {
		return addressL1;
	}
	public void setAddressL1(String addressL1) {
		this.addressL1 = addressL1;
	}
	public String getAddressL2() {
		return addressL2;
	}
	public void setAddressL2(String addressL2) {
		this.addressL2 = addressL2;
	}
	public String getAddressState() {
		return addressState;
	}
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	public String getAddressSub() {
		return addressSub;
	}
	public void setAddressSub(String addressSub) {
		this.addressSub = addressSub;
	}
	public int getAddressPostCode() {
		return addressPostCode;
	}
	public void setAddressPostCode(int addressPostCode) {
		this.addressPostCode = addressPostCode;
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
	public long getTaxFileNum() {
		return taxFileNum;
	}
	public void setTaxFileNum(long taxFileNum) {
		this.taxFileNum = taxFileNum;
	}
	public int getBsbNum() {
		return bsbNum;
	}
	public void setBsbNum(int bsbNum) {
		this.bsbNum = bsbNum;
	}
	public long getBankAccountNum() {
		return bankAccountNum;
	}
	public void setBankAccountNum(long bankAccountNum) {
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
	public int getValidationBySup() {
		return validationBySup;
	}
	public void setValidationBySup(int validationBySup) {
		this.validationBySup = validationBySup;
	}
	public int getUserActivate() {
		return userActivate;
	}
	public void setUserActivate(int userActivate) {
		this.userActivate = userActivate;
	}
	
	
	
}
