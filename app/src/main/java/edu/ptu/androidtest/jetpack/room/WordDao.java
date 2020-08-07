package edu.ptu.androidtest.jetpack.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
        @Insert
        public void insertAll(Word... userjava);

        @Query("select * from word_table")
        public LiveData<List<Word>> getUsersFromSync();

        @Query("select * from word_table")
        public List<Word> getUserList();

        @Delete
        public  int delete(Word userjava);//参数可以是数组，集合，返回的是删除成功的条数

        @Update
        public  int update(Word... userjava);//参数可以是数组，集合，返回的是update成功的条数
}
