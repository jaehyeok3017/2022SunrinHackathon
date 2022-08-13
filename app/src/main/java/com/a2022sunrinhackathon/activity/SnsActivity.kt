package com.a2022sunrinhackathon.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.a2022sunrinhackathon.adapter.SnsRecyclerViewAdapter
import com.a2022sunrinhackathon.databinding.ActivitySnsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SnsActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySnsBinding

    private var firestore : FirebaseFirestore? = null
    private var uid : String? = null
    private var mBackWait : Long = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        uid = FirebaseAuth.getInstance().currentUser?.uid
        firestore = FirebaseFirestore.getInstance()

        binding.recyclerView.adapter = SnsRecyclerViewAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.plusBtn.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }

        binding.mapBtn.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기를 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        } else {
            finishAffinity()
        }
    }
}