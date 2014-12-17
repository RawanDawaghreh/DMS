package com.dms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dms.library.UserFunctions;
import com.dms.models.DocumentView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailsView extends Activity {

	private DocumentView documentView  ;


	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_detailsview);
		new GetDocumentDetails().execute();
		Button btnViewAttached = (Button) findViewById(R.id.btnViewAttachment);
		//Listening to button event
		btnViewAttached.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						//Starting a new Intent
						Intent ViewAttach = new Intent(DetailsView.this, AttachmentView.class);
						// starting new activity
						
						Bundle b = getIntent().getExtras();
						ViewAttach.putExtra("id", b.getInt("id"));
						startActivity(ViewAttach);
						
						
						Log.d("btnViewAttached", b.getInt("id")+"");
						
					}
				});
		
	}
	 
	 
	public void notifyDetailsReady()
	 {
	    Log.d("documentView", documentView.toString());
	    
	    TextView txtDocumentName = (TextView)findViewById(R.id.ViewName); // Document Name
        TextView txtRFCode = (TextView)findViewById(R.id.ViewRfcode); // RFCode
        TextView txtCountry = (TextView)findViewById(R.id.ViewCountry); // Country
        TextView txtFolder = (TextView)findViewById(R.id.ViewFolder); // Folder
        TextView txtCategory = (TextView)findViewById(R.id.ViewCategories); // Category
	    
        // displaying style
        TextView FC_Documentname = (TextView)findViewById(R.id.txtViewName);
        TextView FC_RFCode = (TextView)findViewById(R.id.txtViewRfCode);
        TextView FC_Country = (TextView)findViewById(R.id.txtViewCountry);
        TextView FC_Folder = (TextView)findViewById(R.id.txtFolder);
        TextView FC_Category = (TextView)findViewById(R.id.txtViewCategory);
        
        FC_Documentname.setTextColor(getResources().getColor(R.color.gray));
        FC_RFCode.setTextColor(getResources().getColor(R.color.gray));
        FC_Country.setTextColor(getResources().getColor(R.color.gray));
        FC_Folder.setTextColor(getResources().getColor(R.color.gray));
        FC_Category.setTextColor(getResources().getColor(R.color.gray));
        
   	 // displaying selected
    
        String DocumentName = documentView.getDocumantName();
    	String RFCode = documentView.getRFCode();
    	String Country = documentView.getCountry();
    	String Folder = documentView.getFolder();
    	String Category = documentView.getCategories();

    	txtDocumentName.setText(DocumentName);
    	txtRFCode.setText(RFCode);
    	txtCountry.setText(Country);
    	txtFolder.setText(Folder);
    	txtCategory.setText(Category);
   
	    
	    
	 }
	 
	 private class GetDocumentDetails extends AsyncTask<String, String, String>
	    {
	    	
	    	private boolean isSucces=false;
	    	ProgressDialog progressDialog;
	    	
	    	
	    	@Override
	    	protected void onPreExecute() {
	    		
	    		progressDialog = new ProgressDialog(DetailsView.this);
	    		progressDialog.setTitle("loading");
	            progressDialog.show();
	    	}
			
			@Override
			protected String doInBackground(String... params) {

				Bundle b = getIntent().getExtras();
				UserFunctions userFunction = new UserFunctions();
				SharedPreferences sh = getSharedPreferences("UserInfo",getBaseContext().MODE_PRIVATE);
			    String UserID =	sh.getString("UserID", "");
			    String BranchID =	sh.getString("BranchID", "");	
				JSONObject jsonDoc =  userFunction.getDocumentsView(BranchID, "9", b.getInt("id"));
				
				
				try {
					JSONArray jsonArray =  jsonDoc.getJSONArray("Decumant");
					
					for (int i = 0; i < jsonArray.length(); i++) {
						
						JSONObject ob = jsonArray.getJSONObject(i);
						
						documentView = new DocumentView(
								ob.getString("RFCode"),
								ob.getString("DocumantName"),
								ob.getString("Country"),
								ob.getString("Folder") ,
								ob.getString("Categories"));
						isSucces = true;
						
						
					
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				return null;
				
			}
			
			@Override
			protected void onPostExecute(String result) {
				progressDialog.dismiss();
				if (isSucces) {
	               notifyDetailsReady();
				}
				else
				{
					Toast.makeText(DetailsView.this, "Erorr", Toast.LENGTH_LONG).show();
				}
				
				
				
			}
	    }
	 
	 
}
