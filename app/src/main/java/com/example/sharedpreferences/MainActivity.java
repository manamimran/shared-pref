package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.movetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.text.setText(binding.edit.getText().toString());          //set text of edit text in textview on top
            }
        });
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText();
            }
        });

        againLoadText();           // to load already saved data in previous visit
    }

    private void saveText() {
        SharedPreferences preferences = getSharedPreferences("shared prefers",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Text", binding.text.getText().toString());            //keys or value(we used in intents)

        editor.putBoolean("switch", binding.switch1.isChecked());
        editor.apply();
        Toast.makeText(this, "Data is saved", Toast.LENGTH_SHORT).show();
    }

    private void againLoadText() {
    String text;
    Boolean switchOnOff;

        SharedPreferences preferences = getSharedPreferences("shared prefers",MODE_PRIVATE);

        text=preferences.getString("text","");
        switchOnOff=preferences.getBoolean("switch",false);

        binding.text.setText(text);
        binding.switch1.setChecked(switchOnOff);

    }
}