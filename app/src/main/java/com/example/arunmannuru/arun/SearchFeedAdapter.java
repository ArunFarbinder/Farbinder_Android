package com.example.arunmannuru.arun;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

/**
 * Created by ArunMannuru on 4/22/17.
 */

public class SearchFeedAdapter extends ArrayAdapter<String> {

    public int layout;
    public class ViewHolder2 {
        TextView pf_businessTitle;
        TextView pf_addressDistance;
        TextView pf_businessAddress;
        TextView pf_businessLocation;
        TextView pf_businessState;
        TextView pf_businessZipCode;
    }

    // MyEvents myEvents;
    public SearchFeedAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        layout=resource;
    }
    Context mContext;



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){

        SearchFeedAdapter.ViewHolder2 mainViewHolder=null;
        mContext = parent.getContext();

        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView= inflater.inflate(layout,parent,false );

        final SearchFeedAdapter.ViewHolder2 viewHolder=new SearchFeedAdapter.ViewHolder2();
        viewHolder.pf_businessTitle =(TextView)convertView.findViewById(R.id.pf_businessTitle);
        viewHolder.pf_addressDistance =(TextView)convertView.findViewById(R.id.pf_addressDistance);
        viewHolder.pf_businessAddress=(TextView)convertView.findViewById(R.id.pf_businessAddress);
        viewHolder.pf_businessLocation=(TextView)convertView.findViewById(R.id.pf_businessLocation);
        viewHolder.pf_businessState=(TextView)convertView.findViewById(R.id.pf_businessState);
        viewHolder.pf_businessZipCode=(TextView)convertView.findViewById(R.id.pf_businessZipCode);
        convertView.setTag(viewHolder);

        viewHolder.pf_businessTitle.setText(getItem(position));

        mainViewHolder=(SearchFeedAdapter.ViewHolder2)convertView.getTag();
       //  mainViewHolder.pf_businessTitle.setText(getItem(position));
       // String distance;
       // mainViewHolder.pf_addressDistance.setText(getItem(position));
        return convertView;


    }

}
