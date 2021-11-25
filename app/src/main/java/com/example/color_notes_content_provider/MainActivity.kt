package com.example.color_notes_content_provider

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var lv: ListView
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Notes"

         var helper = MyHelper(applicationContext)
         db = helper.writableDatabase

        rs = db.rawQuery("SELECT * FROM NOTES",null)
        lv = findViewById<ListView>(R.id.listview1)
        var adapter = SimpleCursorAdapter(this,
            R.layout.mylayout,
            rs,
            arrayOf("_id","TITLE","DESCRIPTION","UPDATEDON"),
            intArrayOf(R.id.textId,R.id.text1,R.id.text3,R.id.text2),0)
        lv.adapter = adapter

        lv.setOnItemClickListener { adapterView, view, i, l ->
            var noteId = view.findViewById<TextView>(R.id.textId);

            var i = Intent(this,NoteDetails::class.java)
            i.putExtra("noteId",noteId.text.toString())
            startActivity(i);

        }

        registerForContextMenu(lv)
    }

    override fun onCreateContextMenu(menu: ContextMenu?,v: View?,menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Options")
        menu?.add(0, 101, 0, "UPDATE")
        menu?.add(0, 102, 1, "DELETE")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            101 ->
            {
                intent = Intent(applicationContext,UpdatedNotes::class.java)
                intent.putExtra("id",rs.getString(0))
                startActivity(intent)
            }
            102 ->{
                db.delete("NOTES","_id=?", arrayOf(rs.getString(0)))
                rs.requery()
                AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Deleted Successfully..")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .show()
            }
        }
        finish()
        startActivity(intent)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intent = Intent(applicationContext,AddNotes::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}