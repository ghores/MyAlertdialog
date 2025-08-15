package com.example.myalertdialog;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new USelect(this)
                .setTitle("Select your color")
                .setOptions("Red", "Green", "Blue")
                .setDefaultValue("Blue")
                .setIsCancelable(true)
                .setIsImmediate(false)
                .setOkLabel(R.string.dialog_ok)
                .setCancelLabel(R.string.dialog_cancel)
                .setOnItemSelectedListener((selectedIndex, selectedValue) ->
                        Toast.makeText(MainActivity.this,
                                "You selected index " + selectedIndex + "\nValue " + selectedValue,
                                Toast.LENGTH_SHORT).show())
                .show();
    }
}