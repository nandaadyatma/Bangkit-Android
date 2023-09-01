package com.example.hellodicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //inisialisasi komponen
        var edtTglLahir = findViewById<EditText>(R.id.edt_tanggal_lahir)
        var btnKlik = findViewById<Button>(R.id.btn_klik)
        var tvHasil = findViewById<TextView>(R.id.tv_hasil)

        btnKlik.setOnClickListener{
            val input = edtTglLahir.text.toString()
            if(input.isNotEmpty()){
                try {
                    val year = input.toInt()
                    val hasil = when (year){
                        in 1946..1964 -> "Baby boomer"
                        in 1965..1980 -> "X"
                        in 1981..1995 -> "Millenial"
                        in 1996..2010 -> "Z"
                        else -> "apa ya?"
                    }
                    tvHasil.text = "Kamu gen ${hasil}"
                } catch (e: NumberFormatException) {
                    edtTglLahir.error = "Masukkan tahun yang valid bro" //pesan error ketika input tidak valid
                }
            }
            else {
                edtTglLahir.error = "Isikan field lah bro" //pesan error ketika field kosong
            }
            }
    }
}