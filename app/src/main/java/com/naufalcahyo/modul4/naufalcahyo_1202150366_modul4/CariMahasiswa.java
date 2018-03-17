package com.naufalcahyo.modul4.naufalcahyo_1202150366_modul4;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CariMahasiswa extends AppCompatActivity {
    private String [] mahasiswa = {"Noval", "Aji", "Wildan", "DC", "Dicky", "Ilham",
            "Aari", "Fanha"};
    ListView lv;
    private Button btnMahasiswa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_mahasiswa);

        lv = (ListView) findViewById(R.id.listView);
        btnMahasiswa = (Button) findViewById(R.id.cariMhs);


        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>()));

        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyTask().execute();
            }
        });

    }

    class MyTask extends AsyncTask<Void, String, String>{


        ArrayAdapter<String> adapter;
        ProgressDialog proDia = new ProgressDialog(CariMahasiswa.this,
                R.style.MyAlertDialogStyle);
        int count=0;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) lv.getAdapter();

            //penetapan komponene progress dialog
            proDia.setTitle("Loading Data");
            proDia.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            proDia.setCancelable(false);
            proDia.setProgress(0);

            //menambahkan negative button/cancel
            proDia.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new MyTask().cancel(true);
                            dialogInterface.dismiss();
                        }
                    });
            proDia.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String Name: mahasiswa){
                publishProgress(Name);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Semua nama telah di tambahkan";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);

            //penetapan nilai posisi nama mahasiswa yang akan di muat dalam progress dialog
            Integer posMhs = (int) ((count/(float)mahasiswa.length)*100);
            proDia.setProgress(posMhs);

            //menambahkan "%" dibelakang posisis array mahasiswa
            proDia.setMessage(String.valueOf(posMhs + "%"));
            //post increment agar tetap memuat hingga mencapai batas
            count++;
        }

        @Override
        protected void onPostExecute(String result) {
            //menghilangkan progress bar dan progress dialog
            proDia.dismiss();

            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

            lv.setVisibility(View.VISIBLE);
        }
    }
}
