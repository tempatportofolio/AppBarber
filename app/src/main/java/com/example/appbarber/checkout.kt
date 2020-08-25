package com.example.appbarber

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.ui.setupActionBarWithNavController
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class checkout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Checkout"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        cancel.setOnClickListener {
            val intent = Intent(this@checkout,cukur::class.java)
            startActivity(intent)
            finish()
        }

        val intentObjects: Intent =intent

        val name=intentObjects.getStringExtra("name")
        txtView.text ="$name"
        val tgl=intentObjects.getStringExtra("tgl")
        txtView2.text ="$tgl"
        val jam=intentObjects.getStringExtra("jam")
        txtView3.text ="$jam"
        val layanan=intentObjects.getStringExtra("layanan")
        txtView4.text ="$layanan"

        finish.setOnClickListener {
            var nama = txtView.text.toString()
            var tgl = txtView2.text.toString()
            var jam = txtView3.text.toString()
            var lyn = txtView4.text.toString()

            tambahdata(nama, tgl, jam,lyn)

            val intent= Intent(this@checkout,home::class.java)
            startActivity(intent)
            finish()
        }


    }

        fun tambahdata(nama:String,tgl:String, jam:String, lyn:String){

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()


        AndroidNetworking.post("http://192.168.100.184/barber/booking.php")
            .addBodyParameter("nama", nama)
            .addBodyParameter("tgl", tgl)
            .addBodyParameter("jam", jam)
            .addBodyParameter("layanan", lyn)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@checkout.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
