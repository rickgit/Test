package edu.ptu.androidtest.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
        @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "first_name")
    public String name;
}