package com.example.monothon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.WindowManager
import android.media.MediaPlayer

import android.media.RingtoneManager
import android.net.Uri
import android.media.Ringtone





class CallActivity : AppCompatActivity() {

    private val binding by lazy {

    }
    private lateinit var vibrator : Vibrator
    private val vibratePattern = longArrayOf(500, 100, 500, 100, 500)

    private val defaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(
        this,
        RingtoneManager.TYPE_RINGTONE
    )
    private val defaultRingtone = RingtoneManager.getRingtone(this, defaultRingtoneUri)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        setVibrate()
        playRingtone()

    }


    private fun setVibrate(){
        vibrator.vibrate(vibratePattern, 0)
    }
    private fun playRingtone() {

        defaultRingtone.play()
    }


    override fun onDestroy() {
        super.onDestroy()
        vibrator.cancel()

    }

}