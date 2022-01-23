package com.example.bilgiyarismasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SporActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView sorularr,puann,isim_oyun,gecsayi;
    ImageButton dogruq,yanlisq,anasayfag;

    RelativeLayout gec;
    int gecc;
    Random r = new Random();
    int n;
    int points=0;
    int SKIP_NUMBER=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spor);

        dataHelper = new DataHelper(this);
        gecsayi = (TextView) findViewById(R.id.gecsayi);
        sorularr = (TextView) findViewById(R.id.sorular);
        isim_oyun = (TextView) findViewById(R.id.isim_oyun);
        puann = (TextView) findViewById(R.id.puan);
        dogruq = (ImageButton) findViewById(R.id.dogruq);
        yanlisq = (ImageButton) findViewById(R.id.yanlisq);
        anasayfag = (ImageButton) findViewById(R.id.anasayfayagit);
        gec = (RelativeLayout) findViewById(R.id.gec);

        anasayfag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SporActivity.this,SelectActivity.class));
                finish();
            }
        });

        gecsayi.setText(""+dataHelper.receiveDataInt("Geç", SKIP_NUMBER));
        isim_oyun.setText(dataHelper.receiveDataString("İsim", "Kullanıcı"));

        final String[] arrayQ = {getString(R.string.s1),getString(R.string.s2),getString(R.string.s3),getString(R.string.s4),getString(R.string.s5),getString(R.string.s6),getString(R.string.s7),getString(R.string.s8),getString(R.string.s9),getString(R.string.s10)};
        final Boolean[] arrayA = {true, true, false, false, true, true, true, false, true, true};
        final ArrayList<String> sorular = new ArrayList<String>(Arrays.asList(arrayQ));
        final ArrayList<Boolean> cevaplar = new ArrayList<Boolean>(Arrays.asList(arrayA));

        n = r.nextInt(sorular.size());
        sorularr.setText(sorular.get(n)); //soruların karışık olmasını sağlar

        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gecsayi.setText(""+dataHelper.receiveDataInt("Geç", SKIP_NUMBER));
                gecc = dataHelper.receiveDataInt("Geç",SKIP_NUMBER);
                if(dataHelper.receiveDataInt("Geç",SKIP_NUMBER)== 0 ){
                    Toast.makeText(SporActivity.this,"0 Geçme Hakkınız Var",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    gecc--;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    if(sorular.size() == 0){
                        result();
                    }
                    else{
                        n=r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                        dataHelper.saveDataInt("Geç",gecc);
                    }
                }
            }
        });


        dogruq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cevaplar.get(n))
                {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor: "+points);
                    if(sorular.size() == 0){
                        result();
                    }
                    else
                    {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                }
                else      {
                    result();
                }
            }
        });

        yanlisq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cevaplar.get(n))
                {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor: "+points);
                    if(sorular.size() == 0){
                        result();
                    }
                    else
                    {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                }
                else      {
                    result();
                }
            }
        });
    }

    private void result() {
        dataHelper.saveDataInt("PUAN SPOR", points);
        startActivity(new Intent(SporActivity.this,SporResultActivity.class));
        finish();
    }
}