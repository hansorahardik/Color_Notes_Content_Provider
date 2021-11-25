package com.example.color_notes_content_provider

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class AddNotes : AppCompatActivity() {

    lateinit var edTitle : EditText
    lateinit var edDesc : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        var helper = MyHelper(applicationContext)
        var db = helper.writableDatabase

        edTitle = findViewById(R.id.edTitle)
        edDesc = findViewById(R.id.edDesc)

        var bu1 = findViewById<Button>(R.id.button)
        bu1.setOnClickListener {
            var cv = ContentValues()
            cv.put("TITLE",edTitle.text.toString())
            cv.put("DESCRIPTION",edDesc.text.toString())
            db.insert("NOTES",null,cv)
            clear()

            AlertDialog.Builder(this)
                .setMessage("Added Succesfully..!!!")
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                })
                .show()
        }
    }
    private fun clear() {
        edTitle.setText("")
        edDesc.setText("")
        edTitle.requestFocus()
    }
}