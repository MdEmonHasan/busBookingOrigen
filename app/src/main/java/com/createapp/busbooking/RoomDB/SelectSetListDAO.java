package com.createapp.busbooking.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface SelectSetListDAO {
    @Insert
    void createList(SelectedSets selectedSets);
    @Delete
    void deleteList(SelectedSets selectedSets);

    @Update
    void updateList(SelectedSets selectedSets);

    @Query("DELETE FROM SelectedSets")
    void deleteAll();

    @Query("SELECT * FROM SelectedSets")
    List<SelectedSets> selectedSetList();


}
