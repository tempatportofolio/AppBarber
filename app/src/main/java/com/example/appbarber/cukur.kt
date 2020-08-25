package com.example.appbarber

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.widget.doOnTextChanged
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_cukur.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class cukur : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cukur)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Cukur"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)



        val mPickTimeBtn2 = findViewById<CardView>(R.id.pickTimeBtn)
        val textView2     = findViewById<TextView>(R.id.et_jam)

        mPickTimeBtn2.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView2.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }


        val mPickTimeBtn = findViewById<CardView>(R.id.pickDateBtn)
        val textView     = findViewById<TextView>(R.id.et_tgl)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val date = c.get(Calendar.DATE)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, date ->
                // Display Selected date in TextView
                textView.setText("" + date + "/" + month + "/" + year)
            }, year, month, date)
            dpd.show()

        }


        val layanans = arrayOf("-","Cukur Biasa - Rp.30,000","Paket 1 - Rp.50,000","Paket 2 - Rp.75,000")

        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            layanans // Array
        )

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Finally, data bind the spinner object with dapter
        spinner.adapter = adapter;

        // Set an on item selected listener for spinner object
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                text_view.text = "${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        val context=this

        next.setOnClickListener {
            val intent = Intent(this@cukur,checkout::class.java)

            val name:String = et_nama2.text.toString()
            val tgl:String = et_tgl.text.toString()
            val jam:String = et_jam.text.toString()
            val layanan:String = text_view.text.toString()
            intent.putExtra("name", name)
            intent.putExtra("tgl", tgl)
            intent.putExtra("jam", jam)
            intent.putExtra("layanan", layanan)
            startActivity(intent)
            finish()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

//    fun tambahdata(nama:String,email:String, password:String){
//
//        val loading = ProgressDialog(this)
//        loading.setMessage("Menambahkan data...")
//        loading.show()
//
//
//        AndroidNetworking.post("http://192.168.100.182/barber/register.php")
//            .addBodyParameter("nama_user", nama)
//            .addBodyParameter("email", email)
//            .addBodyParameter("password", password)
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONObject(object : JSONObjectRequestListener {
//
//                override fun onResponse(response: JSONObject?) {
//
//                    loading.dismiss()
//                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()
//
//                    if(response?.getString("message")?.contains("successfully")!!){
//                        this@cukur.finish()
//                    }
//
//                }
//
//                override fun onError(anError: ANError?) {
//                    loading.dismiss()
//                    Log.d("ONERROR",anError?.errorDetail?.toString())
//                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }
//
//
//            })
//
//    }
}
