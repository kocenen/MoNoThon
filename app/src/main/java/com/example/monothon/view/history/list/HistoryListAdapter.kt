package com.example.monothon.view.history.list

import androidx.recyclerview.widget.DiffUtil
import com.example.monothon.R
import com.example.monothon.base.BaseListAdapter
import com.example.monothon.databinding.ItemHistoryBinding
import com.example.monothon.model.SunType
import com.example.monothon.repository.db.SunHistoryItem

class HistoryListAdapter(val onItemClick: (SunHistoryItem) -> Unit) : BaseListAdapter<ItemHistoryBinding, SunHistoryItem>(
    R.layout.item_history,
    object : DiffUtil.ItemCallback<SunHistoryItem>() {
        override fun areItemsTheSame(oldItem: SunHistoryItem, newItem: SunHistoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SunHistoryItem, newItem: SunHistoryItem): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onBind(binding: ItemHistoryBinding, data: SunHistoryItem, position: Int) {
        with(binding) {
            history = data

            when(data.isBreak) {
                true -> {
                    reasonImage.setBackgroundResource(R.drawable.ic_bad)
                    when(data.sunBreakType) {
                        SunType.COVID -> {
                            sunTitle.text = "[핑계거리] 코로나 접촉 동선 문자"
                            sunDescription.text = "이정도로는 어림도 없어요. (단호)"
                        }
                        SunType.WEDDING -> {
                            sunTitle.text = "[핑계거리] 결혼식 참석 문자"
                            sunDescription.text = "결혼식은 참석해야 하잖아요~"
                        }
                        SunType.CAR -> {
                            sunTitle.text = "[핑계거리] 자동차 접촉 사고 전화"
                            sunDescription.text = "우리 붕붕이가 다쳤다는데?!"
                        }
                        SunType.CALL -> {
                            sunTitle.text = "[핑계거리] 커스터마이징 가짜 전화 "
                            sunDescription.text = "원하시는 전화가 올 수 있게 만들어 보세요"
                        }
                    }
                }
                false -> {
                    reasonImage.setBackgroundResource(R.drawable.ic_good)
                    sunTitle.text = "아쉽게도 선을 넘지 않았다~"
                    sunDescription.text = "이정도로는 어림도 없어요. (단호)"
                }
            }

            root.setOnClickListener {
                onItemClick(data)
            }
        }
    }
}