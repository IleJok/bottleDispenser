package com.example.bottledispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView text;
    SeekBar seekbar;
    TextView progressTextView;
    Spinner dropdown;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        // Looping menu for bottle dispenser
        BottleDispenser bd = BottleDispenser.getInstance();
        bd.fillBottleDispenser();
        text = findViewById(R.id.textView2);
        seekbar = findViewById(R.id.seekBar);
        progressTextView = findViewById(R.id.textView3);
        seekbar.setMax(10);
        seekbar.setProgress(0);
        dropdown = findViewById(R.id.spinner);
        String[] bottles = bd.listBottles();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bottles);
        dropdown.setAdapter(adapter);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                progressTextView.setText(Integer.toString(progressChangedValue));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                progressTextView.setText(Integer.toString(progressChangedValue));

        }
        });
    }

    public void buyBottle(View v) {
        BottleDispenser bd = BottleDispenser.getInstance();
        String bottle = dropdown.getSelectedItem().toString();
        String[] items = bottle.split(",", 3);
        String name = items[0].trim();
        String size = items[1].trim();
        String what =  bd.buyBottle(name, Double.parseDouble(size));
        text.setText(what);
        setTheBottleList();
    }

    public void addMoneyTo(View v) {
        BottleDispenser bd = BottleDispenser.getInstance();
        int amount = Integer.parseInt(progressTextView.getText().toString());
        String saldo = bd.addMoney(amount);
        text.setText(saldo);
        seekbar.setProgress(0);

    }

    public void returnMoney(View v) {
        BottleDispenser bd = BottleDispenser.getInstance();
        String moneyOut = bd.returnMoney();
        text.setText(moneyOut);
    }

    public void setTheBottleList() {
        BottleDispenser bd = BottleDispenser.getInstance();
        String[] bottles = bd.listBottles();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bottles);
        dropdown.setAdapter(adapter);
    }

    public void giveRCPT(View v) {
        BottleDispenser bd = BottleDispenser.getInstance();
        String rcpt = bd.printRCPT();
        if (!rcpt.equals("")) {
            try {
                OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("rcpt.txt", Context.MODE_PRIVATE));
                ows.write("RECEIPT: ");
                ows.write(rcpt);
                ows.close();
                text.setText("Receipt printed to file!");
            } catch (IOException e) {
                Log.e("IOException", "Error in input");
            }

        }
    }
}
