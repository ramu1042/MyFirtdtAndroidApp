package com.accenture.myfirtdtandroidapp.Data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.accenture.myfirtdtandroidapp.Adapter.AdapterList;
import com.accenture.myfirtdtandroidapp.Model.Guns;
import com.accenture.myfirtdtandroidapp.Model.Horses;
import com.accenture.myfirtdtandroidapp.R;

import com.accenture.myfirtdtandroidapp.Data.Types;

public class DisplayList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list);
        ListView listView = findViewById(R.id.listView);
        final int type = getIntent().getExtras().getInt(Types.TYPE);
        setListData(type,listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent();
                intent.putExtra("position",i);
                setResult(type,intent);
                finish();
            }
        });

    }

    private void setListData(int type,ListView listView) {
        if (type==Types.HORSE){
            listView.setAdapter(new AdapterList(this,R.layout.list_item, new Horses().getHorses()));

        }else if(type==Types.GUN){
            listView.setAdapter(new AdapterList(this,R.layout.list_item, new Guns().getGuns()));
        }
    }
}
