package com.example.v8t3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Spinner sp;
    TextView teksti;
    ProgressBar pbar;
    SeekBar bar;
    TextView printti;
    BottleDispenser bd = BottleDispenser.getInstance();
    float money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printti = (TextView) findViewById(R.id.textView2);
        teksti = (TextView) findViewById(R.id.massiskaala);
        bar = (SeekBar) findViewById(R.id.simpleSeekBar);
        pbar = (ProgressBar) findViewById(R.id.progressBar);
        sp = findViewById(R.id.spinner);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                teksti.setText(" " + progress + "€");
                pbar.setProgress(10 * progress);
                money = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void addMoney (View v){
        String moneyStr = new Float(money).toString();
        bd.addMoney(money);
        printti.setText("Rahaa lisätty "+moneyStr + "€");
        bar.setProgress(0);
    }
    public void buyBottle(View v){
        int nro = bd.buyBottle();
        if (nro == 1) {
            printti.setText("Pullo pamahti ulos!");
        }
        else if (nro == 0) {
            printti.setText("Laita eka massia!");
        }
    }

    public void returnMoney(View v){
        float massit;
        massit = bd.returnMoney();
        String massitS = new Float(massit).toString();
        printti.setText("Sait takaisin " + massitS+ "€");
    }
}
