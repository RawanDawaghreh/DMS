
package com.dms.library;

import android.content.Context;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserFunctions {
	
	private JSONParser jsonParser;

	private static String LOGIN_API = "IDMS/idms.asmx/CheckLogin";
	private static String GET_DOC_BY_UID_API = "/IDMS/idms.asmx/GetDecumantsByUserID";
	private static String GET_DOC_VIEW_API = "/IDMS/idms.asmx/GetDecumant_view";
	private static String GET_ATTACH_VIEW_API = "/IDMS/idms.asmx/GetDecumant_Attachment";
	private static String SERVER_IP = "http://192.168.11.128/";


	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param UserName
	 * @param password
	 * @param companyID
	 * */
	public JSONObject loginUser(String UserName, String password , String companyID){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("UserName", UserName));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("companyID", companyID));
		
		Log.d("companyID",companyID);
		JSONObject json = jsonParser.makeHttpRequest(SERVER_IP+LOGIN_API, "GET", params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	
	
	/**
	 * function make get Document By user Request
	 * @param BranchID
	 * @param Language
	 * @param UserID
	 * */
	public JSONObject getDocumentsByUserID(String BranchID, String Language , String UserID){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("BranchID", BranchID));
		params.add(new BasicNameValuePair("Language", Language));
		params.add(new BasicNameValuePair("UserID", UserID));
		
		JSONObject json = jsonParser.makeHttpRequest(SERVER_IP+GET_DOC_BY_UID_API, "GET", params);
		return json;
	}
	
	/**
	 * function make get Document details Request
	 * @param BranchID
	 * @param Language
	 * @param DocID
	 * */
	public JSONObject getDocumentsView(String BranchID, String Language , int DocID){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("BranchID", BranchID));
		params.add(new BasicNameValuePair("Language", Language));
		params.add(new BasicNameValuePair("DocID", DocID+""));
		
		JSONObject json = jsonParser.makeHttpRequest(SERVER_IP+GET_DOC_VIEW_API, "GET", params);
		return json;
	}	
	
	
	/**
	 * function make get Document details Request
	 * @param BranchID
	 * @param Language
	 * @param DocID
	 * */
	public JSONObject getDocumentsAttachment(String BranchID, String Language , int DocID){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("BranchID", BranchID));
		params.add(new BasicNameValuePair("Language", Language));
		params.add(new BasicNameValuePair("DocID", DocID+""));

		
		JSONObject json = jsonParser.makeHttpRequest(SERVER_IP+GET_ATTACH_VIEW_API, "GET", params);
		return json;
	}
	
	
	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	
	/**
	 * Function to logout user
	 * Reset Database
	 * */
	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
}
