package com.example.monothon.view.intro.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.databinding.ActivityFaceResultBinding

class FaceResultActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFaceResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_result)

        initBinding()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_face_result)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }
}