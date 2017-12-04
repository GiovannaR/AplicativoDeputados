package com.example.tpfinal.tpfinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by giovannariqueti on 25/11/17.
 */

public class Tela2 extends AppCompatActivity {

    TextView nomeView;
    TextView siglaPartidoView;
    TextView siglaUfView;
    TextView siglaEstAtualView;
    TextView emailView;
    TextView telefoneView;
    ImageView foto;
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
            emailView = (TextView) findViewById(R.id.emailID);
            telefoneView = (TextView) findViewById(R.id.telefoneID);
            foto = (ImageView)findViewById(R.id.imageView2);


            //Parte dedicada a preencher a pagina
            Intent intent = getIntent();
            Bundle params = intent.getExtras();
            DownloadDados getRequest = new DownloadDados();
            String myUrl = params.getString("uri");
            final String result;
            result = getRequest.execute(myUrl).get();
            saved = parseJson(result);

            //Parte dedicada aos favoritos
            ImageButton btnFavorito = (ImageButton) findViewById(R.id.imageButton);

            btnFavorito.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    adicionarArquivo(saved);
                }
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private String parseJson (String json) {
        JSONObject objArray;
        JSONObject ultimoStatus;
        JSONObject gabinete;
        try{

            JSONObject jsonObj = new JSONObject(json);

                objArray = jsonObj.getJSONObject("dados");
                ultimoStatus = objArray.getJSONObject("ultimoStatus");

                nomeView.setText(ultimoStatus.getString("nomeEleitoral"));
                siglaPartidoView.setText(ultimoStatus.getString("siglaPartido"));
                siglaEstAtualView.setText(ultimoStatus.getString("situacao"));
                siglaUfView.setText(ultimoStatus.getString("siglaUf"));

                Bitmap deputado = new Imagem().execute(ultimoStatus.getString("urlFoto")).get();

                foto.setImageBitmap(deputado);

                gabinete = ultimoStatus.getJSONObject("gabinete");

                emailView.setText(gabinete.getString("email"));
                telefoneView.setText(gabinete.getString("telefone"));

            return (ultimoStatus.getString("nomeEleitoral") + "!" + ultimoStatus.getString("uri"));

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
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
