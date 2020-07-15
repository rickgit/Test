package edu.ptu.androidtest._30_storage._60_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class SqliteTest {
    public static class SqliteManager extends SQLiteOpenHelper {

        public SqliteManager(@Nullable Context context ) {
            super(context,"data.db",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table user (id integer primary key autoincrement,username varchar(20),password varchar(20))");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("alter table user add account varchar(20)");

        }
    }
    @Test
    public void testSqlite(){
        SqliteManager manager = new SqliteManager(InstrumentationRegistry.getInstrumentation().getTargetContext());
        long l = System.nanoTime();
        SQLiteDatabase writableDatabase = manager.getWritableDatabase();
        writableDatabase.beginTransaction();
        writableDatabase.execSQL("insert into user (`username`,`password`) values('rick','psw')");
        writableDatabase.endTransaction();
        System.out.println(((System.nanoTime()-l)));//4337100 ns

          l = System.nanoTime();
        SQLiteDatabase readableDatabase = manager.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from user", null);
        while (cursor.moveToNext()){
            System.out.println(cursor.getColumnIndex("username"));
        }
        System.out.println(((System.nanoTime()-l)));//555200 ns

    }

    @Test
    public void testSqliteBatch(){
        SqliteManager manager = new SqliteManager(InstrumentationRegistry.getInstrumentation().getTargetContext());


        manager.getWritableDatabase().execSQL("DROP TABLE IF EXISTS user" );

        long l = System.nanoTime();
        SQLiteDatabase writableDatabase = manager.getWritableDatabase();
        writableDatabase.beginTransaction();
        for (int i = 0; i < 1000; i++) {
            writableDatabase.execSQL("insert into user (`username`,`password`) values('"+"user"+i+"','"+"pwd"+i+"')");
        }
        writableDatabase.endTransaction();
        System.out.println(((System.nanoTime()-l)));//298_477_400 ns


        l = System.nanoTime();
        SQLiteDatabase readableDatabase = manager.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from user", null);
        while (cursor.moveToNext()){
            System.out.println(cursor.getColumnIndex("username"));
        }
        System.out.println(((System.nanoTime()-l)));//1_852_300 ns

    }
}
