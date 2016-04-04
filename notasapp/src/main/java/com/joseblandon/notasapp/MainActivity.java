package com.joseblandon.notasapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, eProy, ePrac;
    Button bCalcular,bLimpiar;
    TextView tvporcM,eNotafinal;
    int proy=35,e=15,prac=50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eExpo=(EditText) findViewById(R.id.eExpo);
        ePrac=(EditText) findViewById(R.id.ePrac);
        eProy=(EditText) findViewById(R.id.eProy);
        eNotafinal=(TextView) findViewById(R.id.eFinal);
        tvporcM=(TextView) findViewById(R.id.tvPorcMain);
        bCalcular= (Button) findViewById(R.id.bCalcular);
        bLimpiar= (Button) findViewById(R.id.bLimpiar);

        if(savedInstanceState!=null)
        {
            e = savedInstanceState.getInt("exposicionSave");
            prac = savedInstanceState.getInt("practicaSave");
            proy = savedInstanceState.getInt("proyectoSave");
        }
        else
        {
            //Si no hay datos guardados inicializo
            e = 15;
            prac = 50;
            proy = 35;
        }

        tvporcM.setText(getResources().getString(R.string.expop) +e+ "\n"+ getResources().getString(R.string.pracp)  +prac+ "\n"+ getResources().getString(R.string.proyp) +proy);

        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double exp_l, prac_l, pro_l, nfinal_l;
                String exposicion, practica, proyecto;
                exposicion = eExpo.getText().toString();
                practica = ePrac.getText().toString();
                proyecto = eProy.getText().toString();
                if (((exposicion.equals("")) || (practica.equals("")) || (proyecto.equals("")))) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorCampoVacio), Toast.LENGTH_SHORT).show();
                } else {
                    exp_l = Double.parseDouble(eExpo.getText().toString());
                    prac_l = Double.parseDouble(ePrac.getText().toString());
                    pro_l = Double.parseDouble(eProy.getText().toString());

                    if ((exp_l <= 5) && (prac_l <= 5) && (pro_l <= 5)) {
                        nfinal_l = ((exp_l * e) + (prac_l * prac) + (pro_l * proy)) / 100;
                        DecimalFormat nfinald = new DecimalFormat("0.0");    //Para mostrar un solo digito decimal
                        eNotafinal.setText(String.valueOf(nfinald.format(nfinal_l)));
                    } else {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.errorNota), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eExpo.setText("");
                ePrac.setText("");
                eProy.setText("");
                eNotafinal.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("exposicionSave", e);
        savedInstanceState.putInt("proyectoSave",proy);
        savedInstanceState.putInt("practicaSave",prac);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.menu_configurar){
            Toast.makeText(this, getResources().getString(R.string.pressConfig), Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,SettingsActivity.class);
            i.putExtra("pProy",proy);
            i.putExtra("pExpo",e);
            i.putExtra("pPrac",prac);
            startActivityForResult(i,1234);
            return true;
        }
       return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1234 && resultCode==RESULT_OK){

            proy= Integer.parseInt(data.getExtras().getString("prPro"));
            e= Integer.parseInt(data.getExtras().getString("prExpo"));
            prac= Integer.parseInt(data.getExtras().getString("prPra"));
            tvporcM.setText(getResources().getString(R.string.expop) +e+ "\n"+ getResources().getString(R.string.pracp)  +prac+ "\n" +getResources().getString(R.string.proyp) +proy);
            Toast.makeText(this, getResources().getString(R.string.proyp) +proy+ "\n" + getResources().getString(R.string.expop) +e+ "\n"+getResources().getString(R.string.pracp) +prac, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
