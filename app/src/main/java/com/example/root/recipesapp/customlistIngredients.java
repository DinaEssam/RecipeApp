package com.example.root.recipesapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by root on 08/06/17.
 */

public class customlistIngredients extends ArrayAdapter<String> {

    private String[] names;
    private String[] q;

    private Activity context;

    public customlistIngredients(Activity context, String[] names, String[] q) {
        super(context, R.layout.single_row, names);
        this.context = context;
        this.names = names;
        this.q = q;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.single_row, null, true);

        TextView textViewname = (TextView) listViewItem.findViewById(R.id.nameI);
        TextView textViewq= (TextView) listViewItem.findViewById(R.id.QI);

        textViewname.setText(names[position]);
        textViewq.setText(q[position]);


        return listViewItem;
    }
}
