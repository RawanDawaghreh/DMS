package com.dms.models;


public class ViewAttachment {
	private String FileName,FileSize,FileType,UploadedDate,FilePath;
	private int AttachID;
	

	public ViewAttachment(String fileName, String fileSize, String fileType,
			String uploadedDate, String filePath, int attachID) {
		FileName = fileName;
		FileSize = fileSize;
		FileType = fileType;
		UploadedDate = uploadedDate;
		FilePath = filePath;
		this.AttachID = attachID;		
	}

	
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileSize() {
		return FileSize;
	}
	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public String getUploadedDate() {
		return UploadedDate;
	}
	public void setUploadedDate(String uploadedDate) {
		UploadedDate = uploadedDate;
	}
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public int getAttachID() {
		return AttachID;
	}
	public void setAttachID(int attachID) {
		AttachID = attachID;
	}
	
	@Override
	public String toString() {
		return "ViewAttachment [FileName=" + FileName + ", FileSize="
				+ FileSize + ", FileType=" + FileType + ", UploadedDate="
				+ UploadedDate + ", FilePath=" + FilePath + ", AttachID="
				+ AttachID + ", Hosting="  + "]";
	}
	
	
}
