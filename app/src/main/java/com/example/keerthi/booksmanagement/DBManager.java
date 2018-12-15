package com.example.keerthi.booksmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context c;

    private SQLiteDatabase database;

    public DBManager(Context c) {

        this.c = c;
        dbHelper = new DatabaseHelper(c);
    }

    public void open(){
     // dbHelper = new DatabaseHelper(c);
        try {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
      //  return this;
    }

    public void close() throws SQLException {
        dbHelper.close();
    }

    public boolean insert(String name, String author,String date) {
        try {
            ContentValues contentValue = new ContentValues();
            contentValue.put(DatabaseHelper.BOOK, name);
            contentValue.put(DatabaseHelper.AUTHOR, author);
            contentValue.put(DatabaseHelper.DATE, date);
            long result = database.insert(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID, contentValue);
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cursor fetch() {
        String[] columns ={ DatabaseHelper._ID, DatabaseHelper.BOOK, DatabaseHelper.AUTHOR,DatabaseHelper.DATE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
      //  if (cursor != null) {
       //     cursor.moveToFirst();
      //  }
        return cursor;
    }


    public boolean delete(int _id) {
        try{
      int result=database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=?", new String[]{String.valueOf(_id)});
        if(result>0){
            return true;
        }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        }

}
