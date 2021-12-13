package com.createapp.busbooking.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;


@Database(entities = {SelectedSets.class}, version = 1)
public abstract class SelectedSetsDatabase extends RoomDatabase {

    public abstract SelectSetListDAO selectSetListDAO();

    static SelectedSetsDatabase selectedSetsDatabase = null;
    public static SelectedSetsDatabase getDatabase(Context context){
        if (selectedSetsDatabase == null){
            selectedSetsDatabase = Room.databaseBuilder(context,SelectedSetsDatabase.class,"setData").allowMainThreadQueries().build();
        }
        return selectedSetsDatabase;

    }


}
