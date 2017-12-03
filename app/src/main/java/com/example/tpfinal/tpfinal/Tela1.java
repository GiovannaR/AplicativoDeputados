package com.example.tpfinal.tpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * Created by giovannariqueti on 03/12/17.
 */

public class Tela1 extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;
    Bundle params;
    ListView listview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela1_layout);


        //String[] menuitem = {"github.com/GiovannaR"};

        itemList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.lblListItem, itemList);
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        /*Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            subject = params.getString("subject");
        }*/

        Intent intent = getIntent();

        Bundle params = intent.getExtras();
        String json = params.getString("json");

        parseJson(json);


    }

    private void parseJson (String json) {
        JSONObject objArray;
        try{

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("dados");
            final String [] uri = new String[json.length()];

            itemList.add("" + json.length());
            adapter.notifyDataSetChanged();

            for(int i = 0; i < json.length(); i++){
                objArray = array.getJSONObject(i);
                itemList.add(objArray.getString("nome"));
                uri[i] = objArray.getString("uri");
                adapter.notifyDataSetChanged();

                //params = new Bundle();

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //params.putString("uri" + i , uri[i]);
                        Intent intent = new Intent(getApplicationContext(), Tela2.class);
                        startActivity(intent);
                    }
                });
            }

            //("" + json.length());

            //JSONObject objArray = array.getJSONObject(0);
            //JSONObject obj = objArray.getJSONObject("Partido_");

            //siglaUfView = (TextView) findViewById(R.id.estAtual);
            //nomeView.setText(json);
            //siglaPartidoView.setText(d.getSiglaPartido());
            //siglaUfView.setText(d.getSiglaUF());
            //siglaLegislaturaView.setText(d.getIdLegislatura());
            //itemList.add(objArray.getString("nome"));
            //adapter.notifyDataSetChanged();



            //nomeView.setText(objArray.getString("siglaUf"));
            //siglaPartidoView.setText(objArray.getString("siglaUf"));
            //siglaUfView.setText(d.getSiglaUF());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }







}
