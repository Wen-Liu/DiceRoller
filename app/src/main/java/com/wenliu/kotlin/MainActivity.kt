package com.wenliu.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.wenliu.kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        user = User("12", "123")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = user
        binding.btnResetUserName.setOnClickListener { resetUserName() }
    }

    private fun resetUserName() {
        binding.tvUserName.text = "1111111"
        Log.i(TAG, "user.name= " + user.name)
    }
}
