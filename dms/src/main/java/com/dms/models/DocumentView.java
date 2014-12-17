package com.dms.models;

public class DocumentView {

	private String RFCode,DocumantName,Country,Folder,Categories;

	public DocumentView(String rFCode, String documantName, String country,
			String folder, String categories) {
		super();
		RFCode = rFCode;
		DocumantName = documantName;
		Country = country;
		Folder = folder;
		Categories = categories;
	}
	
	
	public String getRFCode() {
		return RFCode;
	}

	public void setRFCode(String rFCode) {
		RFCode = rFCode;
	}

	public String getDocumantName() {
		return DocumantName;
	}

	public void setDocumantName(String documantName) {
		DocumantName = documantName;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getFolder() {
		return Folder;
	}

	public void setFolder(String folder) {
		Folder = folder;
	}

	public String getCategories() {
		return Categories;
	}

	public void setCategories(String categories) {
		Categories = categories;
	}

	@Override
	public String toString() {
		return "DocumentView [RFCode=" + RFCode + ", DocumantName="
				+ DocumantName + ", Country=" + Country + ", Folder=" + Folder
				+ ", Categories=" + Categories + "]";
	}


}
