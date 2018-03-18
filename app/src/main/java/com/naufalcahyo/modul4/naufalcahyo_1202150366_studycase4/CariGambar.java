package com.naufalcahyo.modul4.naufalcahyo_1202150366_studycase4;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CariGambar extends Activity {
    private EditText etLinkGbr;
    private Button btnCariGbr;
    private ImageView bntkGbr;
    private String ImageURL;
    private Bitmap Imagebitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        etLinkGbr = (EditText) findViewById(R.id.linkGambar);
        btnCariGbr = (Button) findViewById(R.id.cariGbr);
        bntkGbr = (ImageView) findViewById(R.id.gambar);
        btnCariGbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageURL = etLinkGbr.getText().toString();
                new ImageLoader().execute(ImageURL);
            }
        });
    }

    public class ImageLoader extends AsyncTask<String, String, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                Imagebitmap = BitmapFactory.decodeStream((InputStream)
                        new URL(strings[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Imagebitmap;
        }

        @Override
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                bntkGbr.setImageBitmap(image);
            }
        }
    }
}
