package com.example.tpfinal.tpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by giovannariqueti on 25/11/17.
 */

public class Tela2 extends AppCompatActivity {

    TextView nomeView;
    TextView siglaPartidoView;
    TextView siglaUfView;
    TextView siglaLegislaturaView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2_layout);

        nomeView = (TextView) findViewById(R.id.nomeID);
       // siglaPartidoView = (TextView) findViewById(R.id.partidoID);
       // siglaUfView = (TextView) findViewById(R.id.estAtual);
       // siglaLegislaturaView = (TextView) findViewById(R.id.cargoID);



        Deputado d = new Deputado();

        Intent intent = getIntent();

        Bundle params = intent.getExtras();
        String json = params.getString("json");

        parseJson(json, d);




    }

    private void parseJson (String json, Deputado d) {
        try{

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("dados");

            JSONObject objArray = array.getJSONObject(0);
            //JSONObject obj = objArray.getJSONObject("Partido_");

            siglaUfView = (TextView) findViewById(R.id.estAtual);
            //nomeView.setText(json);
            //siglaPartidoView.setText(d.getSiglaPartido());
            //siglaUfView.setText(d.getSiglaUF());
            //siglaLegislaturaView.setText(d.getIdLegislatura());
            //d.setSiglaUF(objArray.getString("siglaUf"));

            nomeView.setText(objArray.getString("siglaUf"));
            //siglaPartidoView.setText(objArray.getString("siglaUf"));
            //siglaUfView.setText(d.getSiglaUF());
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
