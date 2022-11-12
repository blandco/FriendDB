package com.joelbland.frienddb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "friendsDb";
    private static final int DATABASE_VERSION = 1;
    private static  final String TABLE_FRIENDS = "friends";

    public DatabaseManager (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_FRIENDS + " (id integer primary key autoincrement,";
        sqlCreate += "first_name text, last_name text, email text)";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_FRIENDS);
        onCreate(db);
    }

    public void insert(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into  " + TABLE_FRIENDS;
        sqlInsert += " values(null, '" + friend.getFirstName();
        sqlInsert += "','" + friend.getLastName() + "',";
        sqlInsert += "'" + friend.getEmail() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIENDS + " where id = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public ArrayList<Friend> selectAll() {
        String sqlQuery = "select * from " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Friend> friends = new ArrayList<Friend>();
        while(cursor.moveToNext()) {
            Friend currentFriend = new Friend(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            friends.add(currentFriend);
        }
        db.close();
        return friends;
    }

    public void updateById(int id,String firstname, String lastname, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_FRIENDS;
        sqlUpdate += " set first_name = '" + firstname + "', last_name = '" + lastname;
        sqlUpdate += "', email = '" + email;
        sqlUpdate += "' where id = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    // SELECT BY ID
    public Friend selectById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlSelect = "select * from " + TABLE_FRIENDS + " where id = " + id;
        Cursor cursor = db.rawQuery(sqlSelect, null);

        if (cursor != null)
            cursor.moveToFirst();

        Friend friend = new Friend(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );

        db.close();
        return friend;
    }

}
