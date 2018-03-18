package com.naufalcahyo.modul4.naufalcahyo_1202150366_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mhs,gbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mhs = (Button) findViewById(R.id.btnMhs);
        gbr = (Button) findViewById(R.id.btnGbr);

        mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,
                        CariMahasiswa.class);
                startActivity(intent1);
            }
        });

        gbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,
                        CariGambar.class);
                startActivity(intent2);
            }
        });
    }
}
