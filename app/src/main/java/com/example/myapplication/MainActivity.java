package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText enterText;
    private TextView shiftedText;
    private Button btn;
    private int shiftedAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterText = (EditText) findViewById(R.id.enterText);
        shiftedText = (TextView) findViewById(R.id.shiftedText);
        btn = (Button) findViewById(R.id.btn);

        if (savedInstanceState == null) {
            shiftedAmt = 0;
        }
        else {
            shiftedAmt = savedInstanceState.getInt("shiftedAmt");
        }

        Intent intent = new Intent(this, ChangeShiftActivity.class);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                shiftText();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };


        enterText.addTextChangedListener(textWatcher);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch second activity
                intent.putExtra("curShift", shiftedAmt);
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("CHECK", "CALL MADE TO ONACTIVITYRESULT");
        int newShift = data.getIntExtra("newShift", 0);
        Log.w("CHECK", "THE NEW SHIFT IS " + newShift);
        shiftedAmt = newShift;
        shiftText();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("shiftedAmt", shiftedAmt);
    }

    public void shiftText() {
        String inputStr = enterText.getText().toString();
        String res = "";
        for (int i = 0; i < inputStr.length(); i++) {
            int oldPos = inputStr.charAt(i) - 'a';
            int newPos = (oldPos + shiftedAmt) % 26;
            char newChar = (char) ('a' + newPos);
            res += (newChar);
        }
        shiftedText.setText(res);
    }
}