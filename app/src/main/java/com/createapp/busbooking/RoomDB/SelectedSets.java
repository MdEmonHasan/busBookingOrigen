package com.createapp.busbooking.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
@Entity
public class SelectedSets {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "userSelectedList")
    //List<String> setList = new ArrayList<>();
    String setList;


    public SelectedSets(String setList) {
        this.setList = setList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetList() {
        return setList;
    }

    public void setSetList(String setList) {
        this.setList = setList;
    }


}
