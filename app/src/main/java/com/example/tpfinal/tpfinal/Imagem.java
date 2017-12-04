package com.example.tpfinal.tpfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by giovannariqueti on 04/12/17.
 */

public class Imagem extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... url) {
            try{
                String params = url[0];
                URL endereco;
                InputStream inputStream;
                Bitmap imagem;
                endereco = new URL(params);
                inputStream = endereco.openStream();
                imagem = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return imagem;
            }catch (IOException e) {
                e.printStackTrace();
                return null;
            }
    }

}

