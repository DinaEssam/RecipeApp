package com.example.root.recipesapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 08/06/17.
 */

public class ParsJSONngredients {
    public static String[] names;
    public static String[] quantity;
    public static String instructions;

    public static final String JSON_ARRAY = "Ingredients";
    public static final String KEY_name = "Name";
    public static final String KEY_Q = "Quantity";
    public static final String KEY_instructions = "Instructions";


    private JSONArray users = null;
    private String json;


    public ParsJSONngredients(String json) {
        this.json = json;
    }

    protected void ParsJSONngredients() {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            instructions=jsonObject.getString(KEY_instructions);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            names = new String[users.length()];
            quantity= new String[users.length()];


            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                names[i] = jo.getString(KEY_name);
                quantity[i] = jo.getString(KEY_Q);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
