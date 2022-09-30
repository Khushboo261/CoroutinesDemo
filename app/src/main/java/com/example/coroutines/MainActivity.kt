package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.math.log as log

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var messageTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageTextView = findViewById(R.id.tvMessage)
        val textView = findViewById<TextView>(R.id.tvCount)
        val countButton = findViewById<Button>(R.id.btnCount)
        val downloadButton = findViewById<Button>(R.id.btnDownload)

        countButton.setOnClickListener(){
            textView.text = count++.toString()
        }
        downloadButton.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
    }
    private suspend fun downloadUserData(){
        for (i in 1..200000) {
            Log.i("MyTag", "Downloading User $i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main){
            messageTextView.text = "Downloading User $i"
            }
                delay(100)
        }
    }
}

