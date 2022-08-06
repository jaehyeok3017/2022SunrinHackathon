package com.a2022sunrinhackathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a2022sunrinhackathon.R
import com.a2022sunrinhackathon.data.firebase.placeDTO

class ListAdapter(val itemList: ArrayList<placeDTO>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sns, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.email.text = itemList[position].userEmail.toString()
        holder.imageurl.text = itemList[position].imageUrl
        holder.comment.text = itemList[position].exaplain
        holder.address.text = itemList[position].address
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val email: TextView = itemView.findViewById(R.id.email)
        val comment : TextView = itemView.findViewById(R.id.comment)
        val imageurl : TextView = itemView.findViewById(R.id.image)
        val address : TextView = itemView.findViewById(R.id.review)
    }
}