package com.example.root.recipesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class screen1 extends AppCompatActivity {

    //URL to get recipes
    public static final String JSON_URL = "https://api2.bigoven.com/recipes?api_key=axV15293h59oU9Z853fw48CmI1H1Js";
    private ListView listView;
    Button refresh,search;
    private ProgressDialog dialog;
    EditText editsearchttext;
    public String[] title=new String[10];
    public String[] category=new String[10];
    public String[] subcategory=new String[10];
    public  String[] image=new String[10];
    String[] rate=new String[10];
    public String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        dialog = new ProgressDialog(screen1.this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait...");

        listView = (ListView) findViewById(R.id.listview);
        refresh=(Button)findViewById(R.id.refresh);
        search=(Button)findViewById(R.id.search);
        sendrequest();
       refresh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listView.setAdapter(null);
               dialog.show();
               sendrequest();

           }
       });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
editsearchttext=(EditText)findViewById(R.id.searchtext);
               id1= editsearchttext.getText().toString();
                String apiKey="axV15293h59oU9Z853fw48CmI1H1Js";
                String uri = String.format("http://api2.bigoven.com/recipe/" + id1+ "?api_key="+apiKey);
                dialog.show();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                dialog.dismiss();
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response);

                                   title[0]=jsonObject.getString("Title");

                                   category[0]=jsonObject.getString("Category");

                                    subcategory[0]=jsonObject.getString("Subcategory");

                                    image[0]=jsonObject.getString("PhotoUrl");

                                    rate[0]=jsonObject.getString("StarRating");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                    listView.setAdapter(null);
                                    customlistAdapter cl = new customlistAdapter(screen1.this, title, category, subcategory,image,rate);
                                    listView.setAdapter(cl);

                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                                            Intent intent = new Intent(screen1.this, screen2.class);
                                            String title1 = title[position];
                                            String category1 = category[position];
                                            String subcategory1 = subcategory[position];
                                            String image1 =image[position];
                                            String rate1=rate[position];

                                            Bundle b = new Bundle();

                                            b.putString("id", id1);
                                            b.putString("title", title1);
                                            b.putString("category", category1);
                                            b.putString("subcat", subcategory1);
                                            b.putString("image", image1);
                                            b.putString("rate", rate1);

                                            intent.putExtras(b);
                                            startActivity(intent);
                                            finish();

                                        }

                                    });

                                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                                        public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                                                       int index, long arg3) {

                                            Toast.makeText(screen1.this,"Yammy", Toast.LENGTH_SHORT).show();

                                            return true;
                                        }
                                    });




                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(screen1.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {

                };

                RequestQueue requestQueue = Volley.newRequestQueue(screen1.this);
                requestQueue.add(stringRequest);


            }
        });



    }

    private void sendrequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listView.setAdapter(null);
                        dialog.dismiss();

                        showjson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(screen1.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showjson(String response) {

        ParseJSON pj = new ParseJSON(response);
        pj.ParseJSON();
        customlistAdapter cl = new customlistAdapter(screen1.this, ParseJSON.tiltes, ParseJSON.catogries, ParseJSON.subcatories,ParseJSON.images,ParseJSON.rates);
        listView.setAdapter(cl);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Intent intent = new Intent(screen1.this, screen2.class);
                String ID=ParseJSON.ids[position];
                String title = ParseJSON.tiltes[position];
                String category = ParseJSON.catogries[position];
                String subcategory = ParseJSON.subcatories[position];
                String image = ParseJSON.images[position];
                String rate=ParseJSON.rates[position];

                Bundle b = new Bundle();

                b.putString("id", ID);
                b.putString("title", title);
                b.putString("category", category);
                b.putString("subcat", subcategory);
                b.putString("image", image);
                b.putString("rate", rate);

                intent.putExtras(b);
                startActivity(intent);
                finish();
            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {

                Toast.makeText(screen1.this,"Yammy", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }



}




