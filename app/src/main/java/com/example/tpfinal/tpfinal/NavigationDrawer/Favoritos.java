package com.example.tpfinal.tpfinal.NavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tpfinal.tpfinal.R;
import com.example.tpfinal.tpfinal.Tela1;
import com.example.tpfinal.tpfinal.Tela2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by giovannariqueti on 26/11/17.
 */

public class Favoritos extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;
    Bundle parametro = new Bundle();
    String [] uri = new String[400];

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos_layout);

        //String[] menuitem = {"github.com/GiovannaR"};

        itemList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.lblListItem, itemList);
        final ListView listview = (ListView) findViewById(R.id.lvw);
        listview.setAdapter(adapter);

        //deleteFile();
        update();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Favoritos.this, Tela2.class);
                parametro.putString("uri", uri[i]);
                intent.putExtras(parametro);
                startActivity(intent);
            }
        });


    }


    public void update() {

        File file = getApplicationContext().getFileStreamPath("favoritos.txt");
        String lineFromFile;
        int i = 0;
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("favoritos.txt")));
                lineFromFile = reader.readLine();
                while (lineFromFile != null) {
                    String [] array = lineFromFile.split("!");
                    itemList.add(array[0]);
                    adapter.notifyDataSetChanged();
                    uri[i] = array[1];
                    i++;
                    //parametro.putString("uri" + i, array[1]);
                    lineFromFile = reader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File does not exist!", Toast.LENGTH_SHORT).show();
        }
    }


    public void adicionarFile(String adicionada){
        try{
            FileOutputStream file = getApplicationContext().openFileOutput("favoritos.txt",MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);
            outputStreamWriter.write(adicionada + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error saving file!", Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteFile () {
        File file = new File(getFilesDir(),"favoritos.txt");
        if (file.exists()) {
            deleteFile("favoritos.txt");
        }
    }
}
