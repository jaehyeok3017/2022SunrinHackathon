package com.a2022sunrinhackathon.Activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.a2022sunrinhackathon.databinding.ActivityRegisterBinding
import com.a2022sunrinhackathon.databinding.ActivitySnsBinding

class SnsActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySnsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}