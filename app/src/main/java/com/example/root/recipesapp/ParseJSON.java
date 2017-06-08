package com.example.root.recipesapp;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 08/06/17.
 */

public class ParseJSON {
    public static String[] ids;
    public static String[] tiltes;
    public static String[] catogries;
    public static String[] subcatories;
    public static String[] images;
    public static String[] rates;

    public static final String JSON_ARRAY = "Results";
    public static final String KEY_id = "RecipeID";
    public static final String KEY_title = "Title";
    public static final String KEY_category = "Category";
    public static final String KEY_subcatepory = "Subcategory";
    public static final String KEY_image = "PhotoUrl";
    public static final String KEY_rate = "StarRating";

    private JSONArray users = null;
    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void ParseJSON() {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            tiltes = new String[users.length()];
            catogries = new String[users.length()];
            subcatories = new String[users.length()];
            images = new String[users.length()];
            rates = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_id);
                tiltes[i] = jo.getString(KEY_title);
                catogries[i] = jo.getString(KEY_category);
                subcatories[i] = jo.getString(KEY_subcatepory);
                images[i] = jo.getString(KEY_image);
                rates[i] = jo.getString(KEY_rate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
