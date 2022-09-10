package test.project.mornhouse.ui.interesting_fact.repository.room_db.item;


import android.content.Context;
import android.view.LayoutInflater;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.List;

import test.project.mornhouse.ui.interesting_fact.view.adapter.IInterestingFactListener;

@Entity(tableName = "facts")
public class Fact {
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @Expose
    @ColumnInfo(name = "number")
    private long number;
    @Expose
    @ColumnInfo(name = "text")
    private String text;

    public Fact() {}

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
