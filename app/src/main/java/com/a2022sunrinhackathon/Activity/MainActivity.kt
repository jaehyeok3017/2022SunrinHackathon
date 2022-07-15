package com.a2022sunrinhackathon.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a2022sunrinhackathon.Fragment.ReviewFragment
import com.a2022sunrinhackathon.Fragment.SuggestFragment
import com.a2022sunrinhackathon.Fragment.UserInfoFragment
import com.a2022sunrinhackathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)

        bottomNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.iconBottomNavigationItem1->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayoutMainNavigationLayout, ReviewFragment() )
                        .commit()
                    true
                }
                R.id.iconBottomNavigationItem2->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayoutMainNavigationLayout, SuggestFragment())
                        .commit()
                    true
                }
                R.id.iconBottomNavigationItem3->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayoutMainNavigationLayout, UserInfoFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }


    }
}