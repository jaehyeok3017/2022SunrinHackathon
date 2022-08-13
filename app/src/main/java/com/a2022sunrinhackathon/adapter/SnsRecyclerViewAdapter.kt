package com.a2022sunrinhackathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a2022sunrinhackathon.data.firebase.postDTO
import com.a2022sunrinhackathon.databinding.ItemSnsBinding
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SnsRecyclerViewAdapter(): RecyclerView.Adapter<SnsRecyclerViewAdapter.ViewHolder>() {
    private val postDTOs: ArrayList<postDTO> = arrayListOf()
    private var firestore : FirebaseFirestore? = FirebaseFirestore.getInstance()
    private var uid : String? = null

    init {
        firestore?.collection("posts")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                postDTOs.clear()
                if (querySnapshot == null) return@addSnapshotListener

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(postDTO::class.java)
                    postDTOs.add(item!!)
                }
                notifyDataSetChanged()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSnsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemSnsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: postDTO){
            binding.address.text = data.address
            binding.comment.text = data.comment
            binding.rating.rating = data.rating!!

            Glide.with(itemView)
                .load(data.imageUrl).into(binding.image)
        }
    }

    override fun getItemCount(): Int {
        return postDTOs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postDTOs[position])
    }

}