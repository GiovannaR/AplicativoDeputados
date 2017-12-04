package com.example.tpfinal.tpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutionException;

/**
 * Created by giovannariqueti on 25/11/17.
 */

public class Tela2 extends AppCompatActivity {

    TextView nomeView;
    TextView siglaPartidoView;
    TextView siglaUfView;
    TextView siglaEstAtualView;
    String saved;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2_layout);
        try{
            nomeView = (TextView) findViewById(R.id.nomeID);
            siglaPartidoView = (TextView) findViewById(R.id.partidoID);
            siglaEstAtualView = (TextView) findViewById(R.id.estAtualID);
            siglaUfView = (TextView) findViewById(R.id.UFID);

            //Parte dedicada a preencher a pagina
            Intent intent = getIntent();
            Bundle params = intent.getExtras();
            DownloadDados getRequest = new DownloadDados();
            String myUrl = params.getString("uri");
            final String result = getRequest.execute(myUrl).get();
            saved = parseJson(result);
            nomeView.setText(saved);

            //Parte dedicada aos favoritos
            ImageButton btnFavorito = (ImageButton) findViewById(R.id.imageButton);

            btnFavorito.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    adicionarArquivo(saved);
                }
            });





        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }


    private String parseJson (String json) {
        JSONObject objArray;
        JSONObject ultimoStatus;
        try{

            JSONObject jsonObj = new JSONObject(json);

                objArray = jsonObj.getJSONObject("dados");
                ultimoStatus = objArray.getJSONObject("ultimoStatus");

                nomeView.setText(ultimoStatus.getString("nomeEleitoral"));
                siglaPartidoView.setText(ultimoStatus.getString("siglaPartido"));
                siglaEstAtualView.setText(ultimoStatus.getString("situacao"));
                siglaUfView.setText(ultimoStatus.getString("siglaUf"));
                // + "#" + ultimoStatus.getString("uri"))
            return (ultimoStatus.getString("nomeEleitoral") + "!" + ultimoStatus.getString("uri"));

        }catch (JSONException e){
            e.printStackTrace();
            nomeView.setText(e.getMessage());
            return null;
        }
    }

    //METODOS REFERENTES AO FAVORITOS
    public void adicionarArquivo (String toSave){
        try{
            FileOutputStream file = openFileOutput("favoritos.txt",MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);
            outputStreamWriter.write(toSave + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
            //nomeView.setText(e.getMessage());
            Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
        }

    }


}
