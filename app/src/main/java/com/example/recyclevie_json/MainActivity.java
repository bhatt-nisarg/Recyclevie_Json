package com.example.recyclevie_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    MyRecAdapter myRecAdapter;
    private String TAG = MainActivity.class.getSimpleName();
     RecyclerView rv;

    ArrayList<HashMap<String,String>> InfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InfoList = new ArrayList<>();
        rv = findViewById(R.id.JsRecycleView);

        new FetInfo().execute();



    }
    private class FetInfo extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is Downloading",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ReqHandler reqHandler = new ReqHandler();

            //making url to getting request
            String url = "https://api.androidhive.info/contacts/";
            Log.d("asdf",url);
            String jsonstr = reqHandler.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonstr);
            if (jsonstr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonstr);

                    //getting Json Array Node
                    JSONArray contacts = jsonObject.getJSONArray("contacts");

                    //looping for through All contact
                    for (int i = 0;i<contacts.length();i++){
                        JSONObject jo = contacts.getJSONObject(i);
                        String id = jo.getString("id");
                        String name = jo.getString("name");
                        String email = jo.getString("email");
                        String address = jo.getString("address");
                        String gender = jo.getString("gender");


                        //phone node is JSON Object
                        JSONObject phone = jo.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");



                        //tmp hash map for single contact
                        HashMap<String,String> contact = new HashMap<>();

                        //adding each child node to hashmap key => value
                        contact.put("id",id);
                        contact.put("name",name);
                        contact.put("email",email);
                        contact.put("address",address);
                        contact.put("gender",gender);
                        contact.put("mobile",mobile);
                        contact.put("home",home);
                        contact.put("office",office);
                        Log.d("fghy", id +"==" +  name  + "=="+ email+"==" + address  + "=="+ gender + " == " + mobile + "==" + home + "==" + office );



                        //add contact to list
                        InfoList.add(contact);


                    }
                } catch (JSONException e) {
//                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"json parsing error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Couldn't get json from server",Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            rv.setLayoutManager(linearLayoutManager);
            myRecAdapter = new MyRecAdapter(MainActivity.this,InfoList);
            Log.d("sder", String.valueOf((InfoList.size())));
            rv.setAdapter(myRecAdapter);
            myRecAdapter.notifyDataSetChanged();

        }
    }
}