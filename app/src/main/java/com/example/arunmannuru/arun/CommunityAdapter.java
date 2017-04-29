package com.example.arunmannuru.arun;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

/**
 * Created by ArunMannuru on 4/26/17.
 */

public class CommunityAdapter extends ArrayAdapter<String> {


//    Context mContext;
//
//    public int layout;
//
//    // MyEvents myEvents;
//    public CommunityAdapter(Context context, int resource, List<String> objects) {
//        super(context, resource, objects);
//        layout=resource;
//    }
//
//
//        @Override
//    public View getView(final int position, View convertView, final ViewGroup parent){
//        CommunityAdapter.ViewHolder2 mainViewHolder=null;
//        mContext = parent.getContext();
//        LayoutInflater inflater=LayoutInflater.from(getContext());
//        convertView= inflater.inflate(layout,parent,false );
//        final CommunityAdapter.ViewHolder2 viewHolder=new CommunityAdapter.ViewHolder2();
//
//        viewHolder.picture=(ImageView)convertView.findViewById(R.id.pf_main_pic);
//        convertView.setTag(viewHolder);
//        viewHolder.description=(TextView)convertView.findViewById(R.id.pf_community_description);
//            convertView.setTag(viewHolder);
//        mainViewHolder=(CommunityAdapter.ViewHolder2)convertView.getTag();
//        String description,picUrl,category;
//        description=CommunityActivity.Description.get(position);
//        picUrl=CommunityActivity.Pictures.get(position);
//
//            URL newurl = null;
//            try {
//                newurl = new URL(picUrl);
//                Picasso.with(getContext()).load(picUrl).into(viewHolder.picture);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            return convertView;
//    }
//    public class ViewHolder2 {
//        ImageView picture;
//        TextView  description;
//    }



    public int layout;

    // MyEvents myEvents;
    public CommunityAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        layout=resource;
    }
    Context mContext;



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        CommunityAdapter.ViewHolder2 mainViewHolder=null;
        mContext = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView= inflater.inflate(layout,parent,false );
        final CommunityAdapter.ViewHolder2 viewHolder=new CommunityAdapter.ViewHolder2();

        viewHolder.pf_community_description=(TextView)convertView.findViewById(R.id.pf_community_description);
        viewHolder.picture=(ImageView)convertView.findViewById(R.id.pf_main_pic);
        convertView.setTag(viewHolder);
        mainViewHolder=(CommunityAdapter.ViewHolder2)convertView.getTag();
        mainViewHolder.pf_community_description.setText(getItem(position));
        String description,picUrl,category;
//        description=CommunityActivity.Title2.get(position);
//        mainViewHolder.title2.setText(title2);
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
