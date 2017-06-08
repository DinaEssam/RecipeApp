package com.example.root.recipesapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class screen2 extends AppCompatActivity {


    //URL to get recipes
    //public static final String JSON_URL = "https://api2.bigoven.com/recipe/?api_key=axV15293h59oU9Z853fw48CmI1H1Js";
    private String id,title,category,subcategory,image,rate;
    TextView textviewTitle,textviewcategpry,textviewsubcat,instruction;
    ImageView imagev;
    RatingBar rating;
    ImageView back;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);


        textviewTitle=(TextView)findViewById(R.id.recipetitle2) ;
        textviewcategpry=(TextView)findViewById(R.id.category2);
        textviewsubcat=(TextView)findViewById(R.id.subcatogry2);
        instruction=(TextView)findViewById(R.id.instruction2);
        imagev=(ImageView)findViewById(R.id.recipesimage2);
        rating=(RatingBar)findViewById(R.id.rating2);

        Intent in = getIntent();
        Bundle b = in.getExtras();

        id = b.getString("id");
         String apiKey="axV15293h59oU9Z853fw48CmI1H1Js";
         String uri = String.format("http://api2.bigoven.com/recipe/" + id+ "?api_key="+apiKey);

        title= b.getString("title");
        textviewTitle.setText(title);
        category = b.getString("category");
        textviewcategpry.setText(category);
        subcategory = b.getString("subcat");
        textviewsubcat.setText(subcategory);
        image = b.getString("image");
        Picasso.with(screen2.this).load(image).into(imagev);
        rate = b.getString("rate");
        rating.setRating(Float.parseFloat(rate));

        back=(ImageView)findViewById(R.id.backarrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(screen2.this,screen1.class);
                startActivity(i);
                finish();
            }
        });

        lv = (ListView)findViewById(R.id.listIngredients);

       // sendrequest();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showjson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(screen2.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showjson(String response) {
        ParsJSONngredients pj = new ParsJSONngredients(response);
        pj.ParsJSONngredients();
        customlistIngredients cl = new customlistIngredients(screen2.this, ParsJSONngredients.names,ParsJSONngredients.quantity);
        lv.setAdapter(cl);
        instruction.setText(ParsJSONngredients.instructions);
    }
}
