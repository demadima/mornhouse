package test.project.mornhouse.ui.interesting_fact.repository.room_db.db;

import static test.project.mornhouse.AFApplication.getAppContext;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import test.project.mornhouse.ui.interesting_fact.repository.room_db.dao.FactDao;
import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;

@Database(entities = {Fact.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;
    private static String DATABASE_NAME = "numbers_interesting_facts";

    public static RoomDB getInstance(){
        if (database == null) {
            database = Room.databaseBuilder(getAppContext(),
                    RoomDB.class, DATABASE_NAME).build();
            return database;
        }
        return database;
    }

    public abstract FactDao factDao();
}
