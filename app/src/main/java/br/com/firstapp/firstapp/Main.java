package br.com.firstapp.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private EditText etNome;
    private EditText etDtnasc;
    private EditText etCpf;
    private EditText etEmail;
    private EditText etTel;


    private RadioGroup rSexGroup;
    private RadioButton rSexButton;

    Button btEnviar;

    public static String Nome = null;
    public static String Dtnasc = null;
    public static long Cpf = 0;
    public static String Email = null;
    public static long Tel = 0;
    public static String Sexo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btEnviar = (Button) findViewById(R.id.btEnviar);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PegaTudo();
            }
        });

        final EditText etDtnasc = (EditText) findViewById(R.id.etDtnasc);
        etDtnasc.addTextChangedListener(Mask.insert("##/##/####", etDtnasc));

        final EditText etCpf = (EditText) findViewById(R.id.etCpf);
        etCpf.addTextChangedListener(Mask.insert("###.###.###-##", etCpf));

        final EditText etTel = (EditText) findViewById(R.id.etTel);
        etTel.addTextChangedListener(Mask.insert("(##)####-#####", etTel));
    }

    public void PegaNome() {
        etNome = (EditText) findViewById(R.id.etNome);

        Nome = etNome.getText().toString();
    }

    public void PegaDtnasc() {
        etDtnasc = (EditText) findViewById(R.id.etDtnasc);

        Dtnasc = etDtnasc.getText().toString();
    }

    public void PegaCpf() {
        etCpf = (EditText) findViewById(R.id.etCpf);

        String aux = etCpf.getText().toString();
        aux = retiraCaracteresEspeciais(aux);
        Cpf = Long.parseLong(aux);
    }

    public void PegaEmail() {
        etEmail = (EditText) findViewById(R.id.etEmail);

        Email = etEmail.getText().toString();
    }

    public void PegaTel() {
        etTel = (EditText) findViewById(R.id.etTel);

        String aux = etTel.getText().toString();
        aux = retiraCaracteresEspeciais(aux);
        Tel = Long.parseLong(aux);
    }

    public void PegaSexo() {
        rSexGroup = (RadioGroup) findViewById(R.id.rgSexo);

        // get selected radio button from radioGroup
        int selectedId = rSexGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        rSexButton = (RadioButton) findViewById(selectedId);

        Sexo = rSexButton.getText().toString();
    }

    public void PegaTudo() {
        int test_vazio = 0;

        test_vazio = VerificaVazio();

        if (test_vazio>0) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
        } else {
            PegaNome();
            PegaCpf();
            PegaDtnasc();
            PegaEmail();
            PegaSexo();
            PegaTel();
            Intent it = new Intent(Main.this, Second.class);
            startActivity(it);
        }
    }

    public abstract static class Mask {
        public static String unmask(String s) {
            return s.replaceAll("[.]", "").replaceAll("[-]", "")
                    .replaceAll("[/]", "").replaceAll("[(]", "")
                    .replaceAll("[)]", "");
        }

        public static TextWatcher insert(final String mask, final EditText ediTxt) {
            return new TextWatcher() {
                boolean isUpdating;
                String old = "";
                public void onTextChanged(CharSequence s, int start, int before,int count) {
                    String str = Mask.unmask(s.toString());
                    String mascara = "";
                    if (isUpdating) {
                        old = str;
                        isUpdating = false;
                        return;
                    }
                    int i = 0;
                    for (char m : mask.toCharArray()) {
                        if (m != '#' && str.length() > old.length()) {
                            mascara += m;
                            continue;
                        }
                        try {
                            mascara += str.charAt(i);
                        } catch (Exception e) {
                            break;
                        }
                        i++;
                    }
                    isUpdating = true;
                    ediTxt.setText(mascara);
                    ediTxt.setSelection(mascara.length());
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                public void afterTextChanged(Editable s) {}
            };
        }
    }

    public int VerificaVazio() {
        int cont_vazio = 0;
        etDtnasc = (EditText) findViewById(R.id.etDtnasc);
        etTel = (EditText) findViewById(R.id.etTel);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etNome = (EditText) findViewById(R.id.etNome);

        if (etDtnasc.length()==0)
            cont_vazio++;

        if (etTel.length()==0)
            cont_vazio++;

        if (etEmail.length()==0)
            cont_vazio++;

        if (etCpf.length()==0)
            cont_vazio++;

        if (etNome.length()==0)
            cont_vazio++;

        return cont_vazio;
    }

    public static String retiraCaracteresEspeciais(String stringFonte) {
        String passa = stringFonte;
        passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
        passa = passa.replaceAll("[âãàáä]", "a");
        passa = passa.replaceAll("[ÊÈÉË]", "E");
        passa = passa.replaceAll("[êèéë]", "e");
        passa = passa.replaceAll("ÎÍÌÏ", "I");
        passa = passa.replaceAll("îíìï", "i");
        passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
        passa = passa.replaceAll("[ôõòóö]", "o");
        passa = passa.replaceAll("[ÛÙÚÜ]", "U");
        passa = passa.replaceAll("[ûúùü]", "u");
        passa = passa.replaceAll("Ç", "C");
        passa = passa.replaceAll("ç", "c");
        passa = passa.replaceAll("[ýÿ]", "y");
        passa = passa.replaceAll("Ý", "Y");
        passa = passa.replaceAll("ñ", "n");
        passa = passa.replaceAll("Ñ", "N");
        passa = passa.replaceAll("[-+=*&amp;%$#@!_]", "");
        passa = passa.replaceAll("['\"]", "");
        passa = passa.replaceAll("[<>()\\{\\}]", "");
        passa = passa.replaceAll("['\\\\.,()|/]", "");
        passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
        return passa;
    }
}
