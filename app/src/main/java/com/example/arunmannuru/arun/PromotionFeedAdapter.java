package com.example.arunmannuru.arun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

import static android.content.Context.MODE_PRIVATE;
import static android.support.v4.content.ContextCompat.startActivity;
import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

/**
 * Created by ArunMannuru on 4/19/17.
 */

public class PromotionFeedAdapter extends ArrayAdapter<String> {
    public int layout;

   // MyEvents myEvents;
    public PromotionFeedAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        layout=resource;
    }
    Context mContext;



    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        ViewHolder2 mainViewHolder=null;
        mContext = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(getContext());
        convertView= inflater.inflate(layout,parent,false );
        final ViewHolder2 viewHolder=new ViewHolder2();

        viewHolder.title=(TextView)convertView.findViewById(R.id.pf_title);
        viewHolder.title2=(TextView)convertView.findViewById(R.id.pf_title2);
        viewHolder.category=(TextView)convertView.findViewById(R.id.pf_category);
        viewHolder.picture=(ImageView)convertView.findViewById(R.id.pf_main_pic);
        viewHolder.title.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
              //  String eid=MyEvents.EIDArray.get(position);
                //Toast.makeText(getContext(),"EID = "+eid,Toast.LENGTH_SHORT).show();
           //     NewEvent.EditEventCall=true;
            //    Intent EditEventIntent = new Intent(getContext(),NewEvent.class);
            //    EditEventIntent.putExtra("EID", eid);
                //getContext().startActivity(EditEventIntent);
            //    ((MyEvents) mContext).startActivityForResult(EditEventIntent, 2);



                SharedPreferences prefs3 =getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                final String str = prefs3.getString("Set-Cookie", "");
                BackgroundTask backgroundTask=new BackgroundTask(getContext());
                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("id",PromotionsFeedActivity.PartnerId.get(position));
                    Toast.makeText(getContext(),"se "+str,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(),"id "+PromotionsFeedActivity.PartnerId.get(position),Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String method="get_business_profile";
                backgroundTask.execute(jsonObject.toString(),str,"http://farbinder-dev.elasticbeanstalk.com/api/v1/get_business_profile","get_business");
            }
        });

        convertView.setTag(viewHolder);
        mainViewHolder=(ViewHolder2)convertView.getTag();
        mainViewHolder.title.setText(getItem(position));
        String title2,picUrl,category;
        title2=PromotionsFeedActivity.Title2.get(position);
        mainViewHolder.title2.setText(title2);
        picUrl=PromotionsFeedActivity.Pictures.get(position);
        category=PromotionsFeedActivity.Categorie.get(position);
        mainViewHolder.category.setText(category);

        URL newurl = null;
        try {
            newurl = new URL(picUrl);
            //Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            //viewHolder.picture.setImageBitmap(mIcon_val);
            Picasso.with(getContext()).load(picUrl).into(viewHolder.picture);
            //mainViewHolder.picture.setImageURI(picUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } //catch (IOException e) {
//            e.printStackTrace();
//        }
        //    mainViewHolder.title2.setText(MyEvents.ETypeArray.get(position));

        return convertView;
    }




    public class ViewHolder2 {
        TextView title;
        TextView title2;
        ImageView picture;
        TextView category;
    }

}

