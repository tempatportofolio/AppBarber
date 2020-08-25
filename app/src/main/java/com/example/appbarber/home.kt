package com.example.appbarber

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*


class home : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val context = this

        cv_cukur.setOnClickListener {
            startActivity(Intent(this@home,cukur::class.java))
        }

        cv_model.setOnClickListener {
            startActivity(Intent(this@home,model::class.java))
        }

        cv_layanan.setOnClickListener {
            startActivity(Intent(this@home,layanan::class.java))
        }

        cv_antrian.setOnClickListener {
            startActivity(Intent(this@home,antrian::class.java))
        }



        button.setOnClickListener{
            val sharedPreferences=getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor=sharedPreferences.edit()

            editor.putString("STATUS","0")
            editor.apply()

            startActivity(Intent(this@home,login::class.java))
            finish()
        }



    }




}
