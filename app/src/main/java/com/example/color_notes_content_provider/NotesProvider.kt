package com.example.color_notes_content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class NotesProvider : ContentProvider() {
    companion object
    {
        val PROVIDER_NAME = "com.example.color_notes_content_provider/NotesProvider"
        val URL = "content://$PROVIDER_NAME/NOTES"
        val CONTENT_URI  = Uri.parse(URL)

        val _ID = "_id"
        val TITLE = "TITLE"
        val DESCRIPTION = "DESCRIPTION"
        val UPDATEDON = "UPDATEDON"
    }

    lateinit var db :SQLiteDatabase

    override fun onCreate(): Boolean {
        var helper = MyHelper(context!!)
         db = helper.writableDatabase
        return if(db == null) false else true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        return db.query("NOTES",p1,p2,p3,null,null,p4)
    }

    override fun getType(p0: Uri): String? {
        return "vnd.android.courser.dir/vnd.example.NOTES"
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
      db.insert("NOTES",null,p1)
        return p0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
       var count = db.delete("NOTES",p1,p2)
        return count
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        var count = db.update("NOTES",p1,p2,p3)
        return  count
    }
}