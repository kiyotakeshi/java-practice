package dto;

import java.time.LocalDate;

public class CompanyDTO {
	private int companyId;
	private String companyName;
	private String abbreviation;
	private LocalDate created;
	private LocalDate modified;
	private String createdId;
	private String modifiedId;

	public CompanyDTO() {
	}

	public CompanyDTO(int companyId, String companyName, String abbreviation, LocalDate created, LocalDate modified,
			String createdId, String modifiedId) {

		this.companyId = companyId;
		this.companyName = companyName;
		this.abbreviation = abbreviation;
		this.created = created;
		this.modified = modified;
		this.createdId = createdId;
		this.modifiedId = modifiedId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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
}
