package com.example.color_notes_content_provider

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class NoteDetails : AppCompatActivity() {

    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        this.supportActionBar?.setTitle("Notes")

        var noteId = intent.getStringExtra("noteId")

        var tvt = findViewById<TextView>(R.id.textViewTitle)
        var tvd = findViewById<TextView>(R.id.textViewDesc)

        var helper = MyHelper(applicationContext)
        db = helper.readableDatabase

        rs = db.rawQuery("SELECT * FROM NOTES WHERE _id = ${noteId}",null)

        if(rs.moveToFirst()){
            tvt.text = rs.getString(1)
            tvd.text = rs.getString(2)

        }

    }
}