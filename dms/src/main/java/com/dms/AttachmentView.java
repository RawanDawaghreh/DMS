package com.dms;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.dms.adapter.AdapterAttachView;
import com.dms.library.UserFunctions;
import com.dms.models.ViewAttachment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AttachmentView extends Activity {

	List<ViewAttachment> listOfAttach = new ArrayList<ViewAttachment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewattachment);
		new GetAttach().execute();

	}

	private class GetAttach extends AsyncTask<String, String, String> {

		private boolean isSucces = false;

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... params) {

			Bundle b = getIntent().getExtras();
			UserFunctions userFunction = new UserFunctions();
			SharedPreferences sh = getSharedPreferences("UserInfo",
					getBaseContext().MODE_PRIVATE);

			String BranchID = sh.getString("BranchID", "");
			
			

			Log.d("doInBackground", b.getInt("id") + "");

			JSONObject jsonAttach = userFunction.getDocumentsAttachment(
					BranchID, "9", b.getInt("id"));

			try {
				JSONArray jsonArray = jsonAttach.getJSONArray("Attachments");

				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject ob = jsonArray.getJSONObject(i);
					
					ViewAttachment attach = new ViewAttachment(ob.getString("FileName"),
							ob.getString("FileSize"),
							ob.getString("FileType"),
							ob.getString("UploadedDate"),
							ob.getString("FilePath"),
							ob.getInt("AttachID"));
					
					
				/*	ViewAttachment attach = new ViewAttachment(
							ob.getString("FileName"),
							ob.getString("UploadedDate"),
							ob.getString("FileSize"), ob.getInt("AttachID"),
							ob.getString("FilePath"), ob.getString("FileType"));*/

					listOfAttach.add(attach);
					isSucces = true;

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		@Override
		protected void onPostExecute(String result) {

			if (isSucces) {

				ListView listView = (ListView) findViewById(R.id.listView1);
				AdapterAttachView adapter;
				// Getting adapter
				adapter = new AdapterAttachView(AttachmentView.this,
						listOfAttach);
				listView.setAdapter(adapter);

				// Click event for single list row
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						    						    
						    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://hackbbs.org/article/book/Guide-to-Hacking-with-sub7%20%281%29.doc"));
						    startActivity(Intent.createChooser(intent, "Chose browser"));
		
						// Intent i = new Intent(AttachmentView.this,
						// DetailsView.class);
						// i.putExtra("id",listOfAttach.get(position).getAttachID());
						// startActivity(i);
					}
				});

			} else {
				Toast.makeText(AttachmentView.this, "Erorr", Toast.LENGTH_LONG)
						.show();
			}

		}

	}
}
