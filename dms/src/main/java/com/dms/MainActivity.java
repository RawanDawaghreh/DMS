package com.dms;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.dms.library.UserFunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends Activity {
	
	Button btnLogin;
    EditText editTextUser,editTextPassword,editTextCompany; 
	
 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     // Importing all assets like buttons, text fields
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextCompany = (EditText) findViewById(R.id.editTextCompany);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editTextCompany.setText("mena");
        editTextPassword.setText("sa");
        editTextUser.setText("sa");
     		
     		//loginErrorMsg = (TextView) findViewById(R.id.login_error);
         
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
      	@Override
			public void onClick(View v) {
        	new checkuser().execute();
        	}
        });
    }

    
    private class checkuser extends AsyncTask<String, String, String>
    {
    	
    	private boolean isSucces=false;
		
		@Override
		protected String doInBackground(String... params) {
			
			String UserName = editTextUser.getText().toString();
			String password = editTextPassword.getText().toString();
			String companyID = editTextCompany.getText().toString();

			
			UserFunctions userFunction = new UserFunctions();
			Log.d("Button", "Login");
			Log.d("Button", "companyID"+companyID);
			JSONObject json = userFunction.loginUser(UserName, password,companyID);
			// check for login response
			try {
				JSONArray jsonArray = json.getJSONArray("Login");
				
				JSONObject loginResponse = jsonArray.getJSONObject(0);
				
				if (loginResponse.getString("CheckLogIn") != null) {
					String res = loginResponse.getString("CheckLogIn"); 
					String UserID = loginResponse.getString("UserID"); 
					String CompanyID = loginResponse.getString("CompanyID"); 
					String BranchID = loginResponse.getString("BranchID");
					
					if(res.equals("Success")){
						isSucces= true;
						SharedPreferences sh = getSharedPreferences("UserInfo",getBaseContext().MODE_PRIVATE);
						sh.edit().putString("UserID", UserID).apply();
						sh.edit().putString("CompanyID", CompanyID).apply();
						sh.edit().putString("BranchID", BranchID).apply();
						
						return "success";
					}else{
						// Error in login
					//	loginErrorMsg.setText("Incorrect username/password");
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}	
			return null;
			
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			if (isSucces) {
				// Launch Dashboard Screen
				Intent dashboard = new Intent(MainActivity.this, ViewDocument.class);
				// Close all views before launching Dashboard
				dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(dashboard);
				// Close Login Screen
				finish();
			}
			else
			{
				Toast.makeText(MainActivity.this, "Erorr"+ isSucces, Toast.LENGTH_LONG).show();
			}
			
			
		}
    	
    }

   

}
