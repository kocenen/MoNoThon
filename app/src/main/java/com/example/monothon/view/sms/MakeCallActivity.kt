package com.example.monothon.view.sms

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monothon.R
import com.example.monothon.databinding.ActivityMakeCallBinding
import com.example.monothon.model.SunType
import com.example.monothon.repository.db.RoomDB
import com.example.monothon.repository.db.SunHistoryItem
import com.example.monothon.view.call.CallActivity
import java.io.File

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
                val db = RoomDB.getInstance(applicationContext)
                db!!.sunHistoryDao().addHistory(SunHistoryItem(SunType.CALL, true, "22.01.09", (intent.getSerializableExtra("imageFile") as File).path))
                startActivity(android.content.Intent(this@MakeCallActivity, CallActivity::class.java))
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

}