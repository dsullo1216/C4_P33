package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeShiftActivity extends AppCompatActivity {

    private TextView titleView;
    private EditText enterValue;
    private Button changeBtn;
    private int curShift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift);

        titleView = (TextView) findViewById(R.id.titleView);
        enterValue = (EditText) findViewById(R.id.enterValue);
        changeBtn = (Button) findViewById(R.id.changeBtn);


        if (savedInstanceState == null) {
            curShift = getIntent().getIntExtra("curShift", 0);;
        }
        else {
            curShift = savedInstanceState.getInt("shiftedAmt");
        }

        enterValue.setText("" + curShift);;

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newShift;
                try {
                    newShift = Integer.parseInt(enterValue.getText().toString());
                    if (newShift < 1 || newShift > 25) {
                        throw new Exception();
                    }
                }
                catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Please enter a value between 1 and 25 and try again", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("newShift", newShift);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curShift", curShift);
    }
}
