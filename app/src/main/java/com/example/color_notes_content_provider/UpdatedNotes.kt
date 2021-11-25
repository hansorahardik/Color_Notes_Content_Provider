package com.example.color_notes_content_provider

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class UpdatedNotes : AppCompatActivity() {

    lateinit var edTitle1 : EditText
    lateinit var edDesc1 : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updated_notes2)

        var id = intent.getStringExtra("id")

        var helper = MyHelper(applicationContext)
        var db = helper.writableDatabase
        var rs = db.rawQuery("SELECT * FROM NOTES WHERE _id=?", arrayOf(id))

        edTitle1 = findViewById(R.id.edTitle1)
        edDesc1 = findViewById(R.id.edDesc1)

        if(rs.moveToNext()) {
            edTitle1.setText(rs.getString(1))
            edDesc1.setText(rs.getString(2))
        }

        var bu2 = findViewById<Button>(R.id.button2)

        bu2.setOnClickListener {
            var cv = ContentValues()
            cv.put("TITLE",edTitle1.text.toString())
            cv.put("DESCRIPTION",edDesc1.text.toString())
            db.update("NOTES",cv,"_id=?", arrayOf(rs.getString(0)))
            clear()

            AlertDialog.Builder(this)
                .setTitle("Confirm Box")
                .setMessage("Updated Successfully...")
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                })
                .setNegativeButton("Cancle", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                .show()
        }
    }
    private fun clear() {
        edTitle1.setText("")
        edDesc1.setText("")
        edTitle1.requestFocus()
    }
}