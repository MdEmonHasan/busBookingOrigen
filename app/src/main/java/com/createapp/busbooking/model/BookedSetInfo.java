package com.createapp.busbooking.model;

import com.createapp.busbooking.RoomDB.SelectedSets;

import java.util.List;

public class BookedSetInfo {
    List<String> setName;

    public BookedSetInfo() {
    }

    public BookedSetInfo(List<String> setName) {
        this.setName = setName;
    }

    public List<String> getSetName() {
        return setName;
    }

    public void setSetName(List<String> setName) {
        this.setName = setName;
    }
}
