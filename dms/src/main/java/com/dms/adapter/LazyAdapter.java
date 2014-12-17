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

import com.dms.models.Decumant;

import java.util.List;

import com.dms.R;


public class LazyAdapter extends BaseAdapter implements Filterable {
    
    private Activity activity;
    private List<Decumant> data;
    private static LayoutInflater inflater=null;
    private Filter mFilter;
    private List<Decumant> mStringFilterList;

    
    public LazyAdapter(Activity a,List<Decumant> d) {
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

        TextView DocumentName = (TextView)vi.findViewById(R.id.DocName); // Document Name
        TextView Size = (TextView)vi.findViewById(R.id.Size); // Size
        TextView DateOfUpload = (TextView)vi.findViewById(R.id.Date); // Date
        Log.d("","getView "+data.get(position).toString());
        
        // Setting all values in listview
        try {
        	DocumentName.setText(data.get(position).getNameEn());
        	Size.setText(data.get(position).getFilesSize());
        	DateOfUpload.setText(data.get(position).getCreateDate());

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

/*	@Override
	public Filter getFilter() {
	    if (mFilter == null) {
	        mFilter = new CustomFilter();
	    }
	    return mFilter;
	}
	
	private class CustomFilter extends Filter {

	    @Override
	    protected FilterResults performFiltering(CharSequence constraint) {
	        FilterResults results = new FilterResults();

	        if(constraint == null || constraint.length() == 0) {
	        	List<Decumant>  list = new ArrayList<Decumant>(data);
	            results.values = list;
	            results.count = list.size();
	        } else {
	            ArrayList<String> newValues = new ArrayList<String>();
	            for(int i = 0; i < data.size(); i++) {
	            	Decumant item = data.get(i);
	                if(item.get) {
	                    newValues.add(item);
	                }
	            }
	            results.values = newValues;
	            results.count = newValues.size();
	        }       

	        return results;
	    }

	    @SuppressWarnings("unchecked")
	    @Override
	    protected void publishResults(CharSequence constraint,
	            FilterResults results) {
	        mObjects = (List<String>) results.values;
	        Log.d("CustomArrayAdapter", String.valueOf(results.values));
	        Log.d("CustomArrayAdapter", String.valueOf(results.count));
	        notifyDataSetChanged();
	    }

	}

*/
}