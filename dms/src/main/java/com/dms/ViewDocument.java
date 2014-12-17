package com.dms;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.dms.adapter.LazyAdapter;
import com.dms.library.DatabaseHandler;
import com.dms.library.UserFunctions;
import com.dms.models.Decumant;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ViewDocument extends Activity {
	
	List<Decumant> listOfDoc = new ArrayList<Decumant>();
	// action bar
	private ActionBar actionBar;
	// Refresh menu item
		private MenuItem refreshMenuItem;
		 private Menu optionsMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewdocument);

        FloatingActionButton buttonAction = (FloatingActionButton)findViewById(R.id.floatButton);
        buttonAction.setSize(FloatingActionButton.SIZE_MINI);
        //buttonAction.setColorNormalResId(R.color.pink);
        // buttonAction.setColorPressedResId(R.color.pink_pressed);
        //buttonAction.setIcon(R.drawable.ic_fab_star);

        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(this,SearchActivity.class));

                new SweetAlertDialog(ViewDocument.this)
                        .setTitleText("Here's a message!")
                        .setContentText("please add search activity!")
                        .show();
                //
            }
        });
		

		actionBar = getActionBar();

		// Hide the action bar title
		actionBar.setDisplayShowTitleEnabled(false);

		// Enabling Spinner dropdown navigation
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		new GetDocTask().execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        
        if (id == R.id.action_logout) {
        	
        	Intent homescreen=new Intent(this,DatabaseHandler.class);
        	//SearchHelper.logout=1;
        	homescreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(homescreen);
        	this.finish();  
        	
            return true;
        }
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_refresh) {		
		 // showToast(getResources().getString(R.string.refresh));
		   setRefreshActionButtonState(true);
		   Intent intent = getIntent();
		   finish();
		   startActivity(intent);
		   // setRefreshActionButtonState(false);
		return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private class GetDocTask extends AsyncTask<String, String, String>
    {
    	
    	private boolean isSucces=false;
    	ProgressDialog progressDialog;
    	
    	@Override
    	protected void onPreExecute() {
    		
    		progressDialog = new ProgressDialog(ViewDocument.this);
    		progressDialog.setTitle("loading");
            progressDialog.show();
    	}
		
		@Override
		protected String doInBackground(String... params) {

			
			UserFunctions userFunction = new UserFunctions();
			SharedPreferences sh = getSharedPreferences("UserInfo",getBaseContext().MODE_PRIVATE);
		String UserID =	sh.getString("UserID", "");
		String BranchID =	sh.getString("BranchID", "");	
			JSONObject jsonDoc =  userFunction.getDocumentsByUserID(BranchID, "9", UserID);
			
			try {
				JSONArray jsonArray =  jsonDoc.getJSONArray("Decumants");
				
				for (int i = 0; i < jsonArray.length(); i++) {
					
					JSONObject ob = jsonArray.getJSONObject(i);
					
					Decumant doc = new Decumant(ob.getString("NameEn"), ob.getString("CreateDate"),
							ob.getString("FilesSize"), ob.getInt("Code"));
					
					listOfDoc.add(doc);
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
               
				ListView  listView = (ListView)findViewById(R.id.listView1);
				
				
	
				LazyAdapter lazyAdapter = new LazyAdapter(ViewDocument.this, listOfDoc);
				listView.setAdapter(lazyAdapter);
				
			       // Click event for single list row
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {	               			             
			              Intent i = new Intent(ViewDocument.this, DetailsView.class);			             
			              i.putExtra("id",listOfDoc.get(position).getCode());
			              startActivity(i);
					}
				});	
			
			}
			else
			{
				Toast.makeText(ViewDocument.this, "Erorr", Toast.LENGTH_LONG).show();
			}
			
			
			
		}
    	
    }

    public void setRefreshActionButtonState(final boolean refreshing) {
		  if (optionsMenu != null) {
		   final MenuItem refreshItem = optionsMenu.findItem(R.id.action_refresh);
		   if (refreshItem != null) {
		    if (refreshing) {
		     refreshItem
		       .setActionView(R.layout.action_progressbar);
		    } else {
		     refreshItem.setActionView(null);
		    }
		   }
		  }
		 }
    
}
