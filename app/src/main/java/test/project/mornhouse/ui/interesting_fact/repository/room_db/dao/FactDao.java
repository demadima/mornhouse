package test.project.mornhouse.ui.interesting_fact.repository.room_db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;

@Dao
public interface FactDao {

    @Query("SELECT * FROM facts")
    List<Fact> getAll();

    @Insert
    void insert(Fact fact);

}
