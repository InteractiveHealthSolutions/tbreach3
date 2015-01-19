package org.ird.tbr3.videoeducator.view;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GridViewRadioButtonAdapter extends BaseAdapter {
    List<OptionRadioButton> radioButtonsList;

    public GridViewRadioButtonAdapter(Context c, List<OptionRadioButton> radioButtonsList) {
        this.radioButtonsList = radioButtonsList;
    }

    public int getCount() {
        return radioButtonsList.size();
    }

    public Object getItem(int position) {
    	return radioButtonsList.get(position);
    }

    public long getItemId(int position) {
    	return radioButtonsList.get(position).getId();
    }
    
    @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        return radioButtonsList.get(position);
    }
	
	
}