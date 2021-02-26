package com.wenliu.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.wenliu.kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User

    val demoFlow = flow {
        listOf(9, 5, 2, 7).forEach {
            delay(100)
            emit(it)
        }
    }

    val demoFlow2 = flow {
        listOf("0", "2", "0", "4").forEach {
            delay(200)
            emit(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        runBlocking {
            demoFlow
                .flatMapMerge {
                    printLog("get flow $it")
                    demoFlow2
                }.collect {
                    println(it)
                }
        }
    }

    private fun printLog(log: String) {
        Log.i(TAG, log)
    }
}
