package com.example.monothon.view.sms

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monothon.R
import com.example.monothon.databinding.ActivityMakeCallBinding
import com.example.monothon.view.call.CallActivity

class MakeCallActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMakeCallBinding.inflate(layoutInflater)
    }
    private lateinit var mediaPlayer : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.yuseung_grandpa);
        setContentView(binding.root)
        setOnClick()
    }

    private fun setOnClick(){
        with(binding){
            clButton.setOnClickListener {
                mediaPlayer.start()
            }
            send.setOnClickListener {
                startActivity(android.content.Intent(this@MakeCallActivity, CallActivity::class.java))
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

}