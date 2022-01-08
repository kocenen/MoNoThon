package com.example.monothon.view.intro.result

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.databinding.ActivitySunBreakBinding
import java.io.File

class SunBreakActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySunBreakBinding

    private var selectReason = -1
    private lateinit var file : File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sun_break)
        file = intent.getSerializableExtra("imageFile") as File
        initBinding()
        initClickListener()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sun_break)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun initClickListener() {
        with(mBinding) {
            itemSunBreakReasonCovid.setOnClickListener {
                selectReason = 1
                itemSunBreakReasonCovid.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_select)
                itemSunBreakReasonCovid.alpha = 1f
                itemSunBreakReasonWedding.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonWedding.alpha = 0.5f
                itemSunBreakReasonCar.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCar.alpha = 0.5f
                itemSunBreakReasonCall.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCall.alpha = 0.5f
                nextBtn.alpha = 1f
            }

            itemSunBreakReasonWedding.setOnClickListener {
                selectReason = 2
                itemSunBreakReasonCovid.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCovid.alpha = 0.5f
                itemSunBreakReasonWedding.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_select)
                itemSunBreakReasonWedding.alpha = 1f
                itemSunBreakReasonCar.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCar.alpha = 0.5f
                itemSunBreakReasonCall.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCall.alpha = 0.5f
                nextBtn.alpha = 1f
            }

            itemSunBreakReasonCar.setOnClickListener {
                selectReason = 3
                itemSunBreakReasonCovid.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCovid.alpha = 0.5f
                itemSunBreakReasonWedding.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonWedding.alpha = 0.5f
                itemSunBreakReasonCar.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_select)
                itemSunBreakReasonCar.alpha = 1f
                itemSunBreakReasonCall.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCall.alpha = 0.5f
                nextBtn.alpha = 1f
            }

            itemSunBreakReasonCall.setOnClickListener {
                selectReason = 4
                itemSunBreakReasonCovid.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCovid.alpha = 0.5f
                itemSunBreakReasonWedding.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonWedding.alpha = 0.5f
                itemSunBreakReasonCar.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_unselect)
                itemSunBreakReasonCar.alpha = 0.5f
                itemSunBreakReasonCall.setBackgroundResource(R.drawable.rounded_border_rectangle_gray_15_select)
                itemSunBreakReasonCall.alpha = 1f
                nextBtn.alpha = 1f
            }

            nextBtn.setOnClickListener {
                if(selectReason != -1) {
                    Toast.makeText(this@SunBreakActivity, "선택 !!!!", Toast.LENGTH_SHORT).show()
                }
            }
            nextBtnText.setOnClickListener {
                if(selectReason != -1) {
                    Toast.makeText(this@SunBreakActivity, "선택 !!!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}