package com.android.crocodile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by v-ikomarov on 11/18/2014.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "MY_CARDS7";
    public static final String CARD_TABLE = "CARD_TABLE";
    public static final String ID = "ID";
    public static final String WORD1 = "WORD1";
    public static final String WORD2 = "WORD2";
    public static final String WORD3 = "WORD3";
    public static final String WORD4 = "WORD4";
    public static final String WORD5 = "WORD5";
    public static final String WORD6 = "WORD6";
    public static final String WORD7 = "WORD7";
    public static final String WORD8 = "WORD8";
    public static final int DB_VERSION = 1;



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CARD_TABLE + " (" +
                ID + " integer primary key autoincrement, " +
                WORD1 + " TEXT COLLATE UNICODE, " +
                WORD2 + " TEXT COLLATE UNICODE, " +
                WORD3 + " TEXT COLLATE UNICODE, " +
                WORD4 + " TEXT COLLATE UNICODE, " +
                WORD5 + " TEXT COLLATE UNICODE, " +
                WORD6 + " TEXT COLLATE UNICODE, " +
                WORD7 + " TEXT COLLATE UNICODE, " +
                WORD8 + " TEXT COLLATE UNICODE) " +
                "CHARACTER SET utf8;");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
