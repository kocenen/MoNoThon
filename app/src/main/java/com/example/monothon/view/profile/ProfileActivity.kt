package com.example.monothon.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monothon.R
import com.example.monothon.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}