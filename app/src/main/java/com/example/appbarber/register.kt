package com.example.appbarber

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONObject

class register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val context = this

        text_log.setOnClickListener {
            intent = Intent(context,login::class.java)
            startActivity(intent)
            finish()
        }

        btn_regis.setOnClickListener {
            var nama = et_nama.text.toString()
            var email = et_regemail.text.toString()
            var password = et_regpassword.text.toString()

            tambahdata(nama, email, password)

            val intent= Intent(context,login::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun tambahdata(nama:String,email:String, password:String){

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()


        AndroidNetworking.post("http://192.168.100.184/barber/register.php")
            .addBodyParameter("nama_user", nama)
            .addBodyParameter("email", email)
            .addBodyParameter("password", password)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@register.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }
}
