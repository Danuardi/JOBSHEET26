package com.example.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        var id = intent?.getStringExtra("id")
        val SimpanBtn = findViewById<Button>(R.id.simpanBtn)
        val judul = findViewById<TextView>(R.id.judulEdit)
        val isi = findViewById<TextView>(R.id.isiEdit)

        judul.setText(intent?.getStringExtra("nama"))
        isi.setText(intent?.getStringExtra("isi"))

        SimpanBtn.setOnClickListener() {
            val db = DBHelper(this, null)
            if (id == null){
                db.addBlog(judul.text.toString(),isi.text.toString())
            } else {
                db.updateBlog(Blog(id, judul.text.toString(), isi.text.toString()))
            }
            db.close()
            finish()
        }
    }
}