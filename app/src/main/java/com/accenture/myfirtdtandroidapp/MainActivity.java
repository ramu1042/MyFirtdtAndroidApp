package com.accenture.myfirtdtandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.accenture.myfirtdtandroidapp.Adapter.AdapterList;
import com.accenture.myfirtdtandroidapp.Data.DisplayData;
import com.accenture.myfirtdtandroidapp.Data.DisplayList;
import com.accenture.myfirtdtandroidapp.Data.Types;
import com.accenture.myfirtdtandroidapp.Model.Item;
import com.accenture.myfirtdtandroidapp.Model.Horses;
import com.accenture.myfirtdtandroidapp.Model.Guns;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> displaySelectedList = new ArrayList<>();
    ListView listView;
    AdapterList adapter;
    String contactName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.disListView);
        //  if (!displaySelectedList.isEmpty())
        adapter = new AdapterList(this, R.layout.list_item, displaySelectedList);
        listView.setAdapter(adapter);
    }
    public void horsesList(View view){
        disPlayList(Types.HORSE);
    }
    public void gunsList(View view){
        disPlayList(Types.GUN);
    }

    private void disPlayList(int type) {
        Intent intent = new Intent(this, DisplayList.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Types.TYPE,type);
        intent.putExtras(bundle);
        startActivityForResult(intent,type);
    }
    public void getContact(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, Types.PICK_CONTACT_SUBACTIVITY);
    }
    public void viewSelectedData(View view){
        Intent intent = new Intent(this, DisplayData.class);
        Bundle bundle = new Bundle();
        bundle.putString("contact",contactName);
        bundle.putString("data1",displaySelectedList.get(0).getName());
        bundle.putString("data2",displaySelectedList.get(1).getName());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case (Types.HORSE) :
                if(data!=null){
                    int hPostion = data.getIntExtra("position",-1);
                    displaySelectedList.add(new Horses().getHorses().get(hPostion));
                    adapter.notifyDataSetChanged();
                }
                break;

            case (Types.GUN) :
                if(data!=null) {
                    int gPostion = data.getIntExtra("position", -1);
                    displaySelectedList.add(new Guns().getGuns().get(gPostion));
                    adapter.notifyDataSetChanged();
                }
                break;


            case (Types.PICK_CONTACT_SUBACTIVITY) :
                if (resultCode == RESULT_OK) {
                    try {
                        Uri uri = data.getData();
                        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                        cursor.moveToFirst();
                        int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        String phoneNo = cursor.getString(phoneIndex);
                        contactName = cursor.getString(nameIndex);
                        displaySelectedList.add(new Item( contactName, 0, phoneNo,1f));
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, ""+contactName, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    Log.e("Failed", "Not able to pick contact");
                }
                break;

            default: break;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}

