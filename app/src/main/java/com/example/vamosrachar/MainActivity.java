package com.example.vamosrachar;



import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText oper1, oper2;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oper1= (EditText) findViewById(R.id.editTextNumber);
        oper2= (EditText) findViewById(R.id.editTextNumber2);
        result= (TextView) findViewById(R.id.result);
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
    }


}