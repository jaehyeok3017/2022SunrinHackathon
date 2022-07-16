package com.a2022sunrinhackathon.Activity

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a2022sunrinhackathon.Data.placeDTO
import com.a2022sunrinhackathon.databinding.ActivitySnsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SnsActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySnsBinding

    private var firestore : FirebaseFirestore? = null
    private var uid : String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnsBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        uid = FirebaseAuth.getInstance().currentUser?.uid
//        firestore = FirebaseFirestore.getInstance()
//
//
//        recycle.adapter = ResultViewRecyclerViewAdapter()
//        resultView.layoutManager = LinearLayoutManager(this)
    }
}