package com.example.poclocalizeactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poclocalizeactivity.R
import com.example.poclocalizeactivity.model.LocalizeModel
import kotlinx.android.synthetic.main.list_item_country.view.*

class LocalizeAdapter(var localize: MutableList<LocalizeModel> = mutableListOf(), private val listener: Listener?) : RecyclerView.Adapter<LocalizeAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClick(code : String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_country, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return localize.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(localize[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: LocalizeModel) {
            itemView.txtName.text = item.name
            itemView.imgCountry.setImageResource(item.img)
            itemView.btnSelectLanguage.setOnClickListener { listener?.onItemClick(item.code) }
        }
    }
}