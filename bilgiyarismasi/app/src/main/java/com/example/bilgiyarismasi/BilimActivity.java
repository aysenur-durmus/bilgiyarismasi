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
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class BilimActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_bilim);

        dataHelper = new DataHelper(this);
        gecsayi = (TextView) findViewById(R.id.gecsayi);
        sorularr = (TextView) findViewById(R.id.sorular);
        isim_oyun = (TextView) findViewById(R.id.isim_oyun);
        puann = (TextView) findViewById(R.id.puan);
        dogruq = (ImageButton) findViewById(R.id.dogruq);
        yanlisq = (ImageButton) findViewById(R.id.yanlisq);
        anasayfag = (ImageButton) findViewById(R.id.anasayfayagit);
        gec = (RelativeLayout) findViewById(R.id.gec);

        // butona basınca anasayfaya gider
        anasayfag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BilimActivity.this,SelectActivity.class));
                finish();
            }
        });

        // geçme hakkını ekrana yazıyor
        gecsayi.setText(""+dataHelper.receiveDataInt("Geç", SKIP_NUMBER));
        // kullanıcı adını ekrana yazıyor
        isim_oyun.setText(dataHelper.receiveDataString("İsim", "Kullanıcı"));

        //soruları diziye atıyor
        final String[] arrayQ = {getString(R.string.b1),getString(R.string.b2),getString(R.string.b3),getString(R.string.b4),getString(R.string.b5),getString(R.string.b6),getString(R.string.b7),getString(R.string.b8),getString(R.string.b9),getString(R.string.b10)};
        final Boolean[] arrayA = {false, true, true, true, false, true, false, false, true, true};
        final ArrayList<String>sorular = new ArrayList<String>(Arrays.asList(arrayQ));
        final ArrayList<Boolean> cevaplar = new ArrayList<Boolean>(Arrays.asList(arrayA));

        //soruların karışık olmasını sağlar
        n = r.nextInt(sorular.size());
        sorularr.setText(sorular.get(n));

        // geç e basınca geçme hakkını azaltır ve bitince de geçme hakının olmadığı mesajını verir
        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gecsayi.setText(""+dataHelper.receiveDataInt("Geç", SKIP_NUMBER));
                gecc = dataHelper.receiveDataInt("Geç",SKIP_NUMBER);
                if(dataHelper.receiveDataInt("Geç",SKIP_NUMBER)== 0 ){
                    Toast.makeText(BilimActivity.this,"0 Geçme Hakkınız Var",Toast.LENGTH_SHORT).show();
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

                       // sorunun doğru olduğunu belirten butona basınca skor sayısı artar
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
                                    // soruların karışık gelmesini sağlar
                                    n = r.nextInt(sorular.size());
                                    sorularr.setText(sorular.get(n));
                                }
                            }
                            else      {
                    result();
                }
            }
        });


        // sorunun yanlış olduğunu belirten butona basınca skor sayısı sabit kalır
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

    // soruya yanlış cevap verildiyse direk sonuç sayfası açılır
    private void result() {
        dataHelper.saveDataInt("PUAN BİLİM", points);
        startActivity(new Intent(BilimActivity.this,BilimResultActivity.class));
        finish();
    }
}