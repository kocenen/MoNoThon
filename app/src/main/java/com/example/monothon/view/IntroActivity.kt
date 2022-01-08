package com.example.monothon.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.databinding.ActivityIntroBinding
import com.example.monothon.view.intro.picture.FacePictureActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        initBinding()
        initClickListener()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun initClickListener() {
        with(mBinding) {
            btn1.setOnClickListener {
                progressIntro.isVisible = true
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    progressIntro.isVisible = false
                }
                startActivity(Intent(this@IntroActivity, FacePictureActivity::class.java))
                finish()
            }
            btn2.setOnClickListener {
                progressIntro.isVisible = true
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    progressIntro.isVisible = false
                }
                startActivity(Intent(this@IntroActivity, FacePictureActivity::class.java))
                finish()
            }
        }
    }
}