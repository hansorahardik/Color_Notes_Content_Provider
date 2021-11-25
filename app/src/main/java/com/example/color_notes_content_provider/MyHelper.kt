package com.example.color_notes_content_provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context): SQLiteOpenHelper(context,"MYDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE NOTES (_id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, UPDATEDON TEXT DEFAULT CURRENT_DATE)")
        db?.execSQL("INSERT INTO NOTES(TITLE,DESCRIPTION) VALUES ('SBI','STATE BANK OF INDIA')")
        db?.execSQL("INSERT INTO NOTES(TITLE,DESCRIPTION) VALUES ('BOB','STATE BANK OF BARODA')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}