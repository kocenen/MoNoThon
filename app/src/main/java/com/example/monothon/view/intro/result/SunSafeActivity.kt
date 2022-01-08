package com.example.monothon.view.intro.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.monothon.R
import com.example.monothon.databinding.ActivitySunSafeBinding
import com.example.monothon.model.SunType
import com.example.monothon.repository.db.RoomDB
import com.example.monothon.repository.db.SunHistoryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File

class SunSafeActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySunSafeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sun_safe)

        initBinding()
        initClickListener()
        initImage(intent.getSerializableExtra("imageFile") as File)
        initData()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sun_safe)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun initClickListener() {
        with(mBinding) {
            reTakePictureBtn.setOnClickListener {
                finish()
            }
            reTakePictureBtn2.setOnClickListener {
                finish()
            }
        }
    }

    private fun initImage(file: File) {
        Glide.with(this)
            .load(file)
            .into(mBinding.imageFile)
    }

    private fun initData() {
        val db = RoomDB.getInstance(applicationContext)
        db!!.sunHistoryDao().addHistory(SunHistoryItem(SunType.NONE, false, "22.01.09", (intent.getSerializableExtra("imageFile") as File).path))
    }
}