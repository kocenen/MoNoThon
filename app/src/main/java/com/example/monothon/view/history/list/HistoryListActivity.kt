package com.example.monothon.view.history.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.monothon.R
import com.example.monothon.databinding.ActivityHistoryListBinding
import com.example.monothon.repository.db.RoomDB
import com.example.monothon.view.history.detail.HistoryDetailCrossedLineActivity
import com.example.monothon.view.history.detail.HistoryDetailUncrossedLineActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryListActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHistoryListBinding

    private val adapter = HistoryListAdapter {
        Log.e("sldfjalsdfjsaf", "$it")
        when(it.isBreak) {
            true -> startActivity(
                Intent(this, HistoryDetailCrossedLineActivity::class.java)
                    .putExtra("imageUrl", it.image)
            )
            false -> startActivity(
                Intent(this, HistoryDetailUncrossedLineActivity::class.java)
                    .putExtra("imageUrl", it.image)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_list)

        initBinding()
        initListView()
        initData()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_history_list)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun initListView() {
        mBinding.recyclerView.adapter = adapter
    }

    private fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            launch {
                val data = RoomDB.getInstance(applicationContext)!!.sunHistoryDao().getSunHistory()
                withContext(Dispatchers.Main) {
                    Log.e("listData", "$data")
                    adapter.submitList(data)
                }
            }
        }
    }
}