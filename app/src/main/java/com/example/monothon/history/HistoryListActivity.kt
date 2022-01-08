package com.example.monothon.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.databinding.ActivityHistoryListBinding

class HistoryListActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHistoryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_list)

        initBinding()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_history_list)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }
}