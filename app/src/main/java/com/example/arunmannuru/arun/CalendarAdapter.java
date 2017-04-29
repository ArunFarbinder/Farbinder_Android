package com.example.arunmannuru.arun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by ArunMannuru on 4/28/17.
 */

public class CalendarAdapter extends ArrayAdapter<String> {


    public int layout;

    public CalendarAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        layout=resource;
    }
    Context mContext;



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        CalendarAdapter.ViewHolder2 mainViewHolder=null;
        mContext = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView= inflater.inflate(layout,parent,false );
        final CalendarAdapter.ViewHolder2 viewHolder=new CalendarAdapter.ViewHolder2();

        viewHolder.pf_community_description=(TextView)convertView.findViewById(R.id.pf_community_description);
        viewHolder.picture=(ImageView)convertView.findViewById(R.id.pf_main_pic);
        convertView.setTag(viewHolder);
        mainViewHolder=(CalendarAdapter.ViewHolder2)convertView.getTag();
        mainViewHolder.pf_community_description.setText(getItem(position));
        String description,picUrl,category;
        picUrl=CommunityActivity.Pictures.get(position);

        URL newurl = null;
        try {
            newurl = new URL(picUrl);
            Picasso.with(getContext()).load(picUrl).into(viewHolder.picture);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertView;
    }
    public class ViewHolder2 {
        ImageView picture;
        TextView pf_community_description;
    }




}
