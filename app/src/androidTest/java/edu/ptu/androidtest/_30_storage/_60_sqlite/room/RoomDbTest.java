package edu.ptu.androidtest._30_storage._60_sqlite.room;

import androidx.lifecycle.LiveData;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.util.List;

import edu.ptu.androidtest.jetpack.room.RoomDb;
import edu.ptu.androidtest.jetpack.room.Word;

public class RoomDbTest {
    @Test
    public void test() {
        RoomDb database = RoomDb.getDatabase(InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
        Word word = new Word();
        word.name = "rwerqwe";
        database.wordDao().insertAll(word);
        LiveData<List<Word>> usersFromSync = database.wordDao().getUsersFromSync();
        List<Word> f = database.wordDao().getUserList();
        System.out.println();
    }
}
