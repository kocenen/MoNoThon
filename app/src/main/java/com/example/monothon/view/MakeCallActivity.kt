package com.example.monothon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monothon.databinding.ActivityMakeCallBinding

class MakeCallActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMakeCallBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}