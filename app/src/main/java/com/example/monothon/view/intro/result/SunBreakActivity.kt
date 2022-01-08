package com.example.monothon.view.intro.result

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.databinding.ActivitySunBreakBinding
import com.example.monothon.view.sms.MakeCallActivity
import com.example.monothon.view.sms.MakeMarryActivity
import com.example.monothon.view.sms.MakeWoohanSMSActivity

class SunBreakActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySunBreakBinding

    private var selectReason = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sun_break)

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
                    when(selectReason) {
                        1 -> startActivity(Intent(this@SunBreakActivity, MakeWoohanSMSActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                        2 -> startActivity(Intent(this@SunBreakActivity, MakeMarryActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                        3 -> startActivity(Intent(this@SunBreakActivity, MakeCallActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                        4 -> startActivity(Intent(this@SunBreakActivity, MakeCallActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                    }
                }
            }
            nextBtnText.setOnClickListener {
                if(selectReason != -1) {
                    when(selectReason) {
                        1 -> startActivity(Intent(this@SunBreakActivity, MakeWoohanSMSActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                        2 -> startActivity(Intent(this@SunBreakActivity, MakeMarryActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                        3 -> startActivity(Intent(this@SunBreakActivity, MakeCallActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                        4 -> startActivity(Intent(this@SunBreakActivity, MakeCallActivity::class.java).putExtra("imageFile", intent.getStringExtra("imageFile")))
                    }
                }
            }
        }
    }
}