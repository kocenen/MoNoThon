package com.example.monothon.view.history.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monothon.databinding.ActivityHistoryDetailUncrossedLineBinding

class HistoryDetailUncrossedLineActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHistoryDetailUncrossedLineBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}