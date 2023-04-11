package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var blogView: RecyclerView
    lateinit var blogAdapter: BlogAdapter
    var db = DBHelper(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<Button>(R.id.btnTambah)
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)

        blogView = findViewById(R.id.rvJudul)
        blogView.layoutManager = LinearLayoutManager(this)

        blogAdapter = BlogAdapter(db.getBlog())
        blogView.adapter = blogAdapter

        btnTambah.setOnClickListener() {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
        btnRefresh.setOnClickListener() {
            blogAdapter = BlogAdapter(db.getBlog())
            blogView.adapter = blogAdapter
        }
    }

}