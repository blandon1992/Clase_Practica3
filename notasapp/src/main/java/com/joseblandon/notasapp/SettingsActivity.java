package com.joseblandon.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText eExp,ePra,ePro;
    Button bGuardar,bSLimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        eExp=(EditText) findViewById(R.id.eSExpo);
        ePra=(EditText) findViewById(R.id.eSPrac);
        ePro=(EditText) findViewById(R.id.eSProy);
        bGuardar= (Button) findViewById(R.id.bSCalcular);
        bSLimpiar= (Button) findViewById(R.id.bSLimpiar);
        Bundle extras = getIntent().getExtras();

        eExp.setText(String.valueOf(extras.getInt("pExpo")));
        ePro.setText(String.valueOf(extras.getInt("pProy")));
        ePra.setText(String.valueOf(extras.getInt("pPrac")));

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Gpro1, Gprac1, Gexp1;
                Double Gpro2, Gprac2, Gexp2;

                Gexp1 = eExp.getText().toString();
                Gpro1 = ePro.getText().toString();
                Gprac1 = ePra.getText().toString();

                if (((Gexp1.equals("")) || (Gpro1.equals("")) || (Gprac1.equals("")))) {
                    Toast.makeText(SettingsActivity.this, getResources().getString(R.string.errorCampoVacio), Toast.LENGTH_SHORT).show();
                } else {
                    Gpro2 = Double.parseDouble(eExp.getText().toString());
                    Gprac2 = Double.parseDouble(ePro.getText().toString());
                    Gexp2 = Double.parseDouble(ePra.getText().toString());
                    if (((Gpro2 + Gprac2 + Gexp2) != 100)) {
                        Toast.makeText(SettingsActivity.this, getResources().getString(R.string.errorPorcentaje), Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent();
                        i.putExtra("prPro", ePro.getText().toString());
                        i.putExtra("prExpo", eExp.getText().toString());
                        i.putExtra("prPra", ePra.getText().toString());
                        setResult(RESULT_OK, i);
                        finish();
                    }
                }

            }
        });

        bSLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eExp.setText("");
                ePra.setText("");
                ePro.setText("");
            }
        });
    }
}
