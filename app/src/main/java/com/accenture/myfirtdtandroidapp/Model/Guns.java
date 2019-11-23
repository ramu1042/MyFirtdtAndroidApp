package com.accenture.myfirtdtandroidapp.Model;

import com.accenture.myfirtdtandroidapp.R;

import java.util.ArrayList;
import java.util.List;

public class Guns {
    public List<Item> getGuns(){
        List<Item> horseList = new ArrayList<>();
        horseList.add(new Item("Gun 1", R.drawable.gun1,"Model 1",2));
        horseList.add(new Item("Gun 2", R.drawable.gun2,"Model 2",3));
        horseList.add(new Item("Gun 3", R.drawable.gun3,"Model 3",4));
        horseList.add(new Item("Gun 4", R.drawable.gun4,"Model 4",5));
        horseList.add(new Item("Gun 5", R.drawable.gun5,"Model 5",3));
        return horseList;
    }
}
