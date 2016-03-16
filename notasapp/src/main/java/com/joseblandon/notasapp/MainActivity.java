package com.joseblandon.notasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, eProy,eNotafinal, ePrac;
    Button bCalcular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eExpo=(EditText) findViewById(R.id.eExpo);
        ePrac=(EditText) findViewById(R.id.ePrac);
        eProy=(EditText) findViewById(R.id.eProy);
        eNotafinal=(EditText) findViewById(R.id.eFinal);
        bCalcular= (Button) findViewById(R.id.bCalcular);

        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double exp,prac,pro,nfinal;

                exp=Double.parseDouble(eExpo.getText().toString());
                prac=Double.parseDouble(ePrac.getText().toString());
                pro=Double.parseDouble(eProy.getText().toString());

                nfinal=exp*0.15+prac*0.5+pro*0.35;

                eNotafinal.setText(String.valueOf(nfinal));
            }
        });
    }
}
