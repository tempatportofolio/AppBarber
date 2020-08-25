package com.example.appbarber.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appbarber.R
import com.example.appbarber.models.rambut
import kotlinx.android.synthetic.main.grid_layout_list_item.view.*

class RambutAdapters(var context: Context, var arrayList: ArrayList<rambut>) : RecyclerView.Adapter<RambutAdapters.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val itemHolder = LayoutInflater.from(parent.context) .inflate(R.layout.grid_layout_list_item,parent, false)
        return ItemHolder(itemHolder)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       var rambutChar:rambut = arrayList.get(position)

        holder.icons.setImageResource(rambutChar.iconsChar!!)
        holder.modelr.text = rambutChar.rambutChar

        holder.modelr.setOnClickListener {
            Toast.makeText(context, rambutChar.rambutChar, Toast.LENGTH_LONG).show()
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var icons = itemView.findViewById<ImageView>(R.id.icons_image)
        var modelr = itemView.findViewById<TextView>(R.id.model_text)
    }
}