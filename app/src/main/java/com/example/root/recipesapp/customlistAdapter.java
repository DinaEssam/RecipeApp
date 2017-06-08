package com.example.root.recipesapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by root on 08/06/17.
 */

public class customlistAdapter extends ArrayAdapter<String> {

    private String[] titles;
    private String[] category;
    private String[] subcaregory;
    private String[] images;
    private String[] rate;

    private Activity context;

    public customlistAdapter(Activity context, String[] titles1, String[] category1, String[] subcategory1,String[] images1,String[] rate1) {
        super(context, R.layout.customlist_single, titles1);
        this.context = context;
        this.titles = titles1;
        this.category = category1;
        this.subcaregory = subcategory1;
        this.images = images1;
        this.rate=rate1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.customlist_single, null, true);

        TextView textViewtitle = (TextView) listViewItem.findViewById(R.id.recipetitle);
        TextView textViewcategory = (TextView) listViewItem.findViewById(R.id.category);
        TextView textViewsubcategory = (TextView) listViewItem.findViewById(R.id.subcatogry);
        ImageView imageviewrecipeimage=(ImageView)listViewItem.findViewById(R.id.recipesimage) ;
        RatingBar rate2=(RatingBar)listViewItem.findViewById(R.id.rating);

        textViewtitle.setText(titles[position]);
        textViewcategory.setText(category[position]);
        textViewsubcategory.setText(subcaregory[position]);
        rate2.setRating(Float.parseFloat(rate[position]));

        Picasso.with(context).load(images[position]).into(imageviewrecipeimage);


        return listViewItem;
    }
}
