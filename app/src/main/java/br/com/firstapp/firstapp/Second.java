package br.com.firstapp.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by matheush on 05/01/16.
 */
public class Second extends AppCompatActivity {

    TextView reNome;
    TextView reDtnasc;
    TextView reSexo;
    TextView reCpf;
    TextView reEmail;
    TextView reTel;

    Button btFechar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        AlteraTudo();

    }

    public void AlteraTudo() {
        reNome = (TextView) findViewById(R.id.reNome);
        reNome.setText(Main.Nome);

        reDtnasc = (TextView) findViewById(R.id.reDtnasc);
        reDtnasc.setText(Main.Dtnasc);

        reSexo = (TextView) findViewById(R.id.reSexo);
        reSexo.setText(Main.Sexo);

        reCpf = (TextView) findViewById(R.id.reCpf);
        Long aux = Main.Cpf;
        String Cpf = String.valueOf(aux);
        reCpf.setText(Cpf);

        reEmail = (TextView) findViewById(R.id.reEmail);
        reEmail.setText(Main.Email);

        reTel = (TextView) findViewById(R.id.reTel);
        Long aux2 = Main.Tel;
        String Tel = String.valueOf(aux2);
        reTel.setText(Tel);
    }
}
