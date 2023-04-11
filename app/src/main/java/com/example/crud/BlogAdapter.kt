package com.example.crud

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class BlogAdapter(private val data: ArrayList<Blog>?): RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    class BlogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val judul = itemView.findViewById<TextView>(R.id.namaJudul)
        private val isi = itemView.findViewById<TextView>(R.id.namaIsi)
        private val editBtn = itemView.findViewById<Button>(R.id.btnEdit)
        private val hapusBtn = itemView.findViewById<Button>(R.id.btnHapus)
        fun bind(get: Blog) {
            judul.text = get.judul
            isi.text = get.isi

            editBtn.setOnClickListener() {
                val intent = Intent(itemView.context, EditActivity::class.java)
                intent.putExtra("id", get.id)
                intent.putExtra("judul", get.judul)
                intent.putExtra("isi", get.isi)
                itemView.context.startActivity(intent)
            }
                hapusBtn.setOnClickListener() {
                    val dialogBuilder = AlertDialog.Builder(itemView.context)
                    dialogBuilder.setTitle("Hapus Data")
                    dialogBuilder.setMessage("Hapus Blog"+get.judul)
                    dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _->
                        val db = DBHelper(itemView.context, null)
                        val status = db.deleteBlog(get.id)
                        if (status > -1) Toast.makeText(itemView.context,"data dihapus",Toast.LENGTH_LONG).show()
                    })
                    dialogBuilder.setNegativeButton("cancel", DialogInterface.OnClickListener { _, _ ->

                    })
                    dialogBuilder.create()
                    dialogBuilder.show()
                }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_blog, parent,false))
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        holder.bind(data?.get(position) ?: Blog("","",""))

    }

    override fun getItemCount(): Int {
        return  data?.size ?: 0
    }
}