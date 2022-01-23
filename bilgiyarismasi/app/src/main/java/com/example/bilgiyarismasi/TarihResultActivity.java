package com.example.bilgiyarismasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TarihResultActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView olanpuan, eniyi, tv;
    Button anasay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarih_result);

        dataHelper = new DataHelper(this);
        olanpuan = (TextView) findViewById(R.id.yapilanpuan);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        tv = (TextView) findViewById(R.id.tv_user);
        anasay = (Button) findViewById(R.id.home);

        int puan = dataHelper.receiveDataInt("PUAN TARİH" , 0);
        int eni = dataHelper.receiveDataInt("EN İYİ TARİH" , 0);

        olanpuan = (TextView) findViewById(R.id.yapilanpuan);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        tv.setText("BİR DAHA Kİ SEFERE İYİ ŞANSLAR, "+dataHelper.receiveDataString("İsim","Kullanici"));
        olanpuan.setText(""+puan);


        if(puan > eni){
            eni = puan;
            dataHelper.saveDataInt("En iyi",eni);
        }

        eniyi.setText(""+eni);

        anasay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TarihResultActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}