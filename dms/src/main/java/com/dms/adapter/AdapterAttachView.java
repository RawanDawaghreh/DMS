package com.dms.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.dms.models.ViewAttachment;

import java.util.List;

import com.dms.R;

public class AdapterAttachView extends BaseAdapter implements Filterable {
    
    private Activity activity;
    private List<ViewAttachment>  data;
    private static LayoutInflater inflater=null;
    private Filter mFilter;
    private List<ViewAttachment> mStringFilterList;

    
    public AdapterAttachView(Activity a,List<ViewAttachment> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    
    
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	
        View vi= convertView;
        
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView AttachedName = (TextView)vi.findViewById(R.id.DocName); // Attached Name
        TextView Size = (TextView)vi.findViewById(R.id.Size); // Size
        TextView DateOfUpload = (TextView)vi.findViewById(R.id.Date); // Date
        Log.d("","getView "+data.get(position).toString());
        
        // Setting all values in listview
        try {
        	AttachedName.setText(data.get(position).getFileName());
        	Size.setText(data.get(position).getFileSize());
        	DateOfUpload.setText(data.get(position).getUploadedDate());

		} catch (Exception e) {
			// TODO: handle exception
	        Log.e("","Exception at index "+position);

		}

        return vi;
    }

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
