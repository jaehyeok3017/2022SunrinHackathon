package com.a2022sunrinhackathon.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.a2022sunrinhackathon.databinding.ActivitySnsBinding
import com.google.firebase.firestore.FirebaseFirestore

class SnsActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySnsBinding

    private var firestore : FirebaseFirestore? = null
    private var uid : String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//
//        uid = FirebaseAuth.getInstance().currentUser?.uid
//        firestore = FirebaseFirestore.getInstance()
//
//
//        recycle.adapter = ResultViewRecyclerViewAdapter()
//        resultView.layoutManager = LinearLayoutManager(this)
    }
}