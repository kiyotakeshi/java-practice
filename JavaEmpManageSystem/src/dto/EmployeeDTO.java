package dto;

import java.time.LocalDate;

/**
 *  employeeDAOで取得した情報を保存するための
 *  コンストラクタ,getter/setterを格納するクラス
 */
public class EmployeeDTO {
	/*
	 * employee_infoの変数
	 */
	private int employeeId;
	private String name;
	private String nameHiragana;
	private LocalDate birthday;
	private String sex;
	private String mailAddress;
	private String telephoneNumber;
	private int companyInfoId;
	private String businessManager;
	private String department;
	private String commissioningStatus;
	private String isDeleted;
	private LocalDate created;
	private LocalDate modified;
	private String createdId;
	private String modifiedId;
	/*
	 * employee_stateの変数
	 */
	private LocalDate enterDate;
	private LocalDate retireDate;
	private String status;
	private int age;
	public String strBirthday;
	private String strEnterDate;
	public String strRetireDate;
	/*
	 * company_infoの変数
	 */
	private String abbreviation;

	public EmployeeDTO() {
	}

	public EmployeeDTO(int employeeId, String name, String nameHiragana, LocalDate birthday, String sex,
			String mailAddress, String telephoneNumber, int companyInfoId, String businessManager, String department,
			String commissioningStatus, String isDeleted, LocalDate created, LocalDate modified, String createdId,
			String modifiedId, LocalDate enterDate, LocalDate retireDate, String status) {
		this.name = name;
		this.nameHiragana = nameHiragana;
		this.birthday = birthday;
		this.sex = sex;
		this.mailAddress = mailAddress;
		this.telephoneNumber = telephoneNumber;
		this.companyInfoId = companyInfoId;
		this.businessManager = businessManager;
		this.department = department;
		this.commissioningStatus = commissioningStatus;
		this.isDeleted = isDeleted;
		this.created = created;
		this.modified = modified;
		this.createdId = createdId;
		this.modifiedId = modifiedId;
		this.enterDate = enterDate;
		this.retireDate = retireDate;
		this.status = status;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameHiragana() {
		return nameHiragana;
	}

	public void setNameHiragana(String nameHiragana) {
		this.nameHiragana = nameHiragana;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public int getCompanyInfoId() {
		return companyInfoId;
	}

	public void setCompanyInfoId(int companyInfoId) {
		this.companyInfoId = companyInfoId;
	}

	public String getBusinessManager() {
		return businessManager;
	}

	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCommissioningStatus() {
		return commissioningStatus;
	}

	public void setCommissioningStatus(String commissioningStatus) {
		this.commissioningStatus = commissioningStatus;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDate getModified() {
		return modified;
	}

	public void setModified(LocalDate modified) {
		this.modified = modified;
	}

	public String getCreatedId() {
		return createdId;
	}

	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}

	public String getModifiedId() {
		return modifiedId;
	}

	public void setModifiedId(String modifiedId) {
		this.modifiedId = modifiedId;
	}

	public LocalDate getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(LocalDate enterDate) {
		this.enterDate = enterDate;
	}

	public LocalDate getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(LocalDate retireDate) {
		this.retireDate = retireDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStrBirthday() {
		return strBirthday;
	}

	public void setStrBirthday(String strBirthday) {
		this.strBirthday = strBirthday;
	}

	public String getStrEnterDate() {
		return strEnterDate;
	}

	public void setStrEnterDate(String strEnterDate) {
		this.strEnterDate = strEnterDate;
	}

	public String getStrRetireDate() {
		return strRetireDate;
	}

	public void setStrRetireDate(String strRetireDate) {
		this.strRetireDate = strRetireDate;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

}
