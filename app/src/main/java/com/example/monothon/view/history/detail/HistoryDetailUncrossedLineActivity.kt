package com.example.monothon.view.history.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.monothon.databinding.ActivityHistoryDetailUncrossedLineBinding
import java.io.File

class HistoryDetailUncrossedLineActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHistoryDetailUncrossedLineBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide
            .with(this)
            .load(File(intent.getStringExtra("imageUrl")))
            .into(binding.crossedLinePicture)
    }
}