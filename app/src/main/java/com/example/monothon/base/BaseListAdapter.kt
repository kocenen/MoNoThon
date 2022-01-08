package com.example.monothon.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<VB: ViewDataBinding, T>(@LayoutRes val layoutId: Int, diffCallback: DiffUtil.ItemCallback<T>): ListAdapter<T, BaseListAdapter<VB, T>.ViewHolder>(diffCallback)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<VB>(LayoutInflater.from(parent.context), layoutId, parent, false)
        binding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(val binding: VB): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            onBind(binding, getItem(position), position)
        }
    }

    protected abstract fun onBind(binding: VB, data: T, position: Int)
}