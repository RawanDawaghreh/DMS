package com.dms.models;

public class Decumant {

	private String NameEn,CreateDate,FilesSize;
	private int code;
	
	
	public Decumant(String nameEn, String createDate, String filesSize, int code) {
		super();
		NameEn = nameEn;
		CreateDate = createDate;
		FilesSize = filesSize;
		this.code = code;
	}


	public String getNameEn() {
		return NameEn;
	}


	public void setNameEn(String nameEn) {
		NameEn = nameEn;
	}


	public String getCreateDate() {
		return CreateDate;
	}


	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}


	public String getFilesSize() {
		return FilesSize;
	}


	public void setFilesSize(String filesSize) {
		FilesSize = filesSize;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return "Decumant [NameEn=" + NameEn + ", CreateDate=" + CreateDate
				+ ", FilesSize=" + FilesSize + ", code=" + code + "]";
	}
	
	
	
	
	
	
}
