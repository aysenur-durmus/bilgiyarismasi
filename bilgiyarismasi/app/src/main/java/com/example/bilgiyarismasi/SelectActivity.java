package com.example.bilgiyarismasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelectActivity extends AppCompatActivity {
    RelativeLayout bilim,cografya,tarih,spor;
    TextView isim, bilimskorr, cografyaskorr,tarihskorr,sporskorr;
    ImageView isimduz;
    DataHelper dataHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        dataHelper = new DataHelper(this);

        final ImageButton bilim = findViewById(R.id.bilimbtn);
        final ImageButton cografya = findViewById(R.id.cografyabtn);
        final ImageButton spor = findViewById(R.id.sporbtn);
        final ImageButton tarih = findViewById(R.id.tarihbtn);
        isimduz =  (ImageView) findViewById(R.id.kadi_duzenle);
        isim = (TextView) findViewById(R.id.kullaniciadi);



        bilimskorr = (TextView) findViewById(R.id.bilimskor);
        cografyaskorr = (TextView) findViewById(R.id.cografyaskor);
        tarihskorr = (TextView) findViewById(R.id.tarihskor);
        sporskorr = (TextView) findViewById(R.id.sporskor);



        bilimskorr.setText("SKOR: "+dataHelper.receiveDataInt("Skor", 0));
        cografyaskorr.setText("SKOR: "+dataHelper.receiveDataInt("Skor", 0));
        tarihskorr.setText("SKOR: "+dataHelper.receiveDataInt("Skor", 0));
        sporskorr.setText("SKOR: "+dataHelper.receiveDataInt("Skor", 0));


        isimduz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });


        bilim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, BilimActivity.class);
                startActivity(intent);
            }
        });

        cografya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, CografyaActivity.class);
                startActivity(intent);
            }
        });

        spor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, SporActivity.class);
                startActivity(intent);
            }
        });

        tarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, TarihActivity.class);
                startActivity(intent);
            }
        });

    }

    public void alertDialog(){
        View view = getLayoutInflater().from(SelectActivity.this).inflate(R.layout.uyaripenceresi,null);
        final EditText name = (EditText)  view.findViewById(R.id.name);

        AlertDialog.Builder builder= new AlertDialog.Builder(SelectActivity.this);
        builder.setMessage("Kullanıcı Adınızı Giriniz").setView(view).setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                 String s = name.getText().toString();
                 dataHelper.saveDataString("İsim",s);
                 isim.setText(dataHelper.receiveDataString("İsim","Kullanıcı"));
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}