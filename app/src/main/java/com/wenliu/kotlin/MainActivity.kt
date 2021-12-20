package com.wenliu.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.wenliu.kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        @BindingAdapter("autoTextSize")
        fun setAutoTextSizeWithMax(textView: TextView, size: Int) {
            TextViewCompat.setAutoSizeTextTypeWithDefaults(
                textView,
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
            )
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                textView,
                6,
                size,
                2,
                TypedValue.COMPLEX_UNIT_DIP
            )
        }
    }


    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User

    private val demoFlow = flow {
        listOf(9, 5, 2, 7).forEach {
            delay(100)
            emit(it)
        }
    }

    private val demoFlow2 = flow {
        listOf("0", "2", "0", "4").forEach {
            delay(200)
            emit(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
