package com.example.monothon.view.call

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.WindowManager
import android.media.RingtoneManager
import android.net.Uri
import android.media.Ringtone
import android.view.View
import com.example.monothon.databinding.ActivityCallBinding

import android.view.animation.AccelerateInterpolator

import android.view.animation.AlphaAnimation
import android.view.animation.Animation


class CallActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCallBinding.inflate(layoutInflater)
    }
    private lateinit var vibrator : Vibrator
    private val vibratePattern = longArrayOf(500, 100, 500, 100, 500)

    private lateinit var defaultRingtoneUri : Uri
    private lateinit var defaultRingtone :Ringtone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        defaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(
            this,
            RingtoneManager.TYPE_RINGTONE
        )
        defaultRingtone = RingtoneManager.getRingtone(this, defaultRingtoneUri)

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        initOnClick()
        playAnimation()
        setVibrate()
        playRingtone()


    }

    private fun initOnClick() {
        with(binding){
//            clCallDecline.setOnTouchListener { view, motionEvent ->
//
//            }
        }
    }

    private fun playAnimation() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val constraintLayout = binding.clCallBody.background as AnimationDrawable
        constraintLayout.apply {
            setEnterFadeDuration(2000)
            setExitFadeDuration(4000)
            start()
        }
    }


    private fun setVibrate(){
        vibrator.vibrate(vibratePattern, 0)
    }
    private fun playRingtone() {

        defaultRingtone.play()
    }


    private fun setAnimFadeOut(startOff: Long, duration: Long): Animation {
        val animFadeOut: Animation
        animFadeOut = AlphaAnimation(1f, 0f)
        animFadeOut.setInterpolator(AccelerateInterpolator())
        animFadeOut.setStartOffset(startOff)
        animFadeOut.setDuration(duration)
        return animFadeOut
    }

    private fun setAnimFadeIn(startOff: Long, duration: Long): Animation {
        val animFadeIn: Animation
        animFadeIn = AlphaAnimation(0f, 1f)
        animFadeIn.interpolator = AccelerateInterpolator()
        animFadeIn.setStartOffset(startOff)
        animFadeIn.setDuration(duration)
        return animFadeIn
    }

    override fun onStop() {
        super.onStop()
        defaultRingtone.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        vibrator.cancel()
    }

}