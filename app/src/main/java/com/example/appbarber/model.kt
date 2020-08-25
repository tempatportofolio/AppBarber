package com.example.appbarber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbarber.adapters.RambutAdapters
import com.example.appbarber.models.rambut

class model : AppCompatActivity() {

    private var recyclerView:RecyclerView ? = null
    private var gridLayoutManager:GridLayoutManager ? = null
    private var arrayList:ArrayList<rambut> ? = null
    private var rambutAdapters:RambutAdapters ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Model"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.my_recycler_view)
        gridLayoutManager = GridLayoutManager(applicationContext, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)

        arrayList = ArrayList()
        arrayList = setModel()
        rambutAdapters = RambutAdapters(applicationContext, arrayList!!)
        recyclerView?.adapter = rambutAdapters
    }
    private fun setModel(): ArrayList<rambut> {

        var arrayLists: ArrayList<rambut> = ArrayList()

        arrayLists.add(rambut(R.drawable.buzzcut, "BuzzCut"))
        arrayLists.add(rambut(R.drawable.combover, "CombOver"))
        arrayLists.add(rambut(R.drawable.fadepompadout, "Fade Pompadout"))
        arrayLists.add(rambut(R.drawable.highfade, "High Fade"))


        return arrayLists
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
