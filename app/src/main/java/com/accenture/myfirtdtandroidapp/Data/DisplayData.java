package com.accenture.myfirtdtandroidapp.Data;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.accenture.myfirtdtandroidapp.R;

public class DisplayData extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);
        String name = getIntent().getExtras().getString("contact");
        String data1 = getIntent().getExtras().getString("data1");
        String data2 = getIntent().getExtras().getString("data2");
        TextView contact = findViewById(R.id.contact);
        contact.setText(name);
        TextView horse = findViewById(R.id.gun);
        horse.setText(data2);
        TextView gun = findViewById(R.id.horse);
        gun.setText(data1);
    }
}
