package com.example.vamosrachar;



import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    EditText oper1, oper2;
    TextView result;
    FloatingActionButton float_share, float_tocar;
    TextToSpeech ttsPlayer;

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1122) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // the user has the necessary data - create the TTS
                ttsPlayer = new TextToSpeech(this, this);
            } else {
                // no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent
                        .setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oper1= (EditText) findViewById(R.id.editTextNumber);
        oper2= (EditText) findViewById(R.id.editTextNumber2);
        result= (TextView) findViewById(R.id.result);
        float_tocar = (FloatingActionButton) findViewById(R.id.tocarButton);
        float_share = (FloatingActionButton) findViewById(R.id.shareButton);


        Intent checkTTSIntent = new Intent ();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, 1122);



            oper2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double numOper1 = Double.parseDouble(oper1.getText().toString());
                String textOper2 = oper2.getText().toString();
                double numOper2=1;
                if (!textOper2.isEmpty()){
                    numOper2 = Double.parseDouble(textOper2);
                }
                double res=numOper1/numOper2;


                result.setText(""+res);
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

        float_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String text = "Oi gente, o total de " +oper1.getText().toString() + " dividido pra "+
                        oper2.getText().toString()+ " pessoas deu " + result.getText().toString()+ "!";
                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,text);
                startActivity(Intent.createChooser(intent, "escolhe o método de compartilhamento!"));
            };
        });


        float_tocar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String text = "Valor individual de " +result.getText().toString()+ " por pessoa!";
                if (ttsPlayer!=null) {
                    ttsPlayer.speak(text, TextToSpeech.QUEUE_FLUSH, null, "ID1");
                }

            };
        });
    }

    @Override
    public void onInit(int initStatus) {
        // checando a inicialização
        if (initStatus == TextToSpeech.SUCCESS) {
            Toast.makeText(this, "TTS ativado...",
                    Toast.LENGTH_LONG).show();
        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sem TTS habilitado...",
                    Toast.LENGTH_LONG).show();
        }
    }
}