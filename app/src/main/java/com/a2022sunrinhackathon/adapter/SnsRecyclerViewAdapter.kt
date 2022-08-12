package com.a2022sunrinhackathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a2022sunrinhackathon.R
import com.a2022sunrinhackathon.data.firebase.postDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SnsRecyclerViewAdapter(): RecyclerView.Adapter<SnsRecyclerViewAdapter.ViewHolder>() {
    private var postDTOs: ArrayList<postDTO> = arrayListOf()
    private var firestore : FirebaseFirestore? = null
    private var uid : String? = null

    init {
        firestore?.collection(uid!!)?.orderBy("timestamp", Query.Direction.DESCENDING)
            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                postDTOs.clear()
                if (querySnapshot == null) return@addSnapshotListener

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(postDTO::class.java)
                    postDTOs.add(item!!)
                }
                notifyDataSetChanged()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnsRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sns, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postDTOs.size
    }

    override fun onBindViewHolder(holder: SnsRecyclerViewAdapter.ViewHolder, position: Int) {

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }
}