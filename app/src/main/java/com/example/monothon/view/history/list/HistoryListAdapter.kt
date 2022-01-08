//package com.example.monothon.view.history.list
//
//import android.widget.ListAdapter
//import androidx.recyclerview.widget.DiffUtil
//import com.example.monothon.R
//
//class HistoryListAdapter(val onItemClick: (String) -> Unit) : ListAdapter<ItemBusBinding, BusStationInfoListInfo>(
//    R.layout.item_history,
//    object : DiffUtil.ItemCallback<BusStationInfoListInfo>() {
//        override fun areItemsTheSame(oldItem: BusStationInfoListInfo, newItem: BusStationInfoListInfo): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: BusStationInfoListInfo, newItem: BusStationInfoListInfo): Boolean {
//            return oldItem == newItem
//        }
//    }) {
//
//    override fun onBind(binding: ItemBusBinding, data: BusStationInfoListInfo, position: Int) {
//        with(binding) {
//            busStation = data
//
//            baseContainer.onSingleClick {
//                onItemClick(data.id)
//            }
//
//            if(data.mainDirection.isNullOrEmpty()) {
//                mainDirection.isVisible = false
//                view.isVisible = false
//            }
//
//            if(data.recommendBusInfo.arriveTime.isNullOrEmpty()) {
//                busArriveNull.visibility = View.VISIBLE
//                busArriveTime.visibility = View.INVISIBLE
//            } else {
//                val minute = UnitHelper.getMinuteCalculation(data.recommendBusInfo.arriveTime)
//                busArriveTime.text = when {
//                    minute.absoluteValue.toInt() <= 1 -> "곧 도착"
//                    minute.absoluteValue.toInt() == 0 -> "도착 또는 출발"
//                    else -> "${minute.absoluteValue}분 뒤 도착"
//                }
//                busArriveNull.visibility = View.INVISIBLE
//                busArriveTime.visibility = View.VISIBLE
//            }
//
//            if(data.busItemList.isNotEmpty()) {
//                sectionLine.isVisible = true
//                rvBusInfo.isVisible = true
//
//                val busInfoList = data.busItemList.groupBy {
//                    it.type
//                }
//
//                val busInfoAdapter = BusInfoAdapter()
//                rvBusInfo.adapter = busInfoAdapter
//                rvBusInfo.layoutManager = FlexboxLayoutManager(root.context).apply {
//                    flexWrap = FlexWrap.WRAP
//                    flexDirection = FlexDirection.ROW
//                }
//                busInfoAdapter.submitList(busInfoList.values.toList())
//            } else {
//                sectionLine.isVisible = false
//                rvBusInfo.isVisible = false
//            }
//
//            if (position == 0) {
//                busArriveTime.post {
//                    firstItemHeightY(root.height)
//                }
//            }
//
//            if (viewModel == null) {
//                viewModel = BusInfoViewModel()
//                viewModel?.initRecommend(
//                    RecommendBusInfo(
//                        data.recommendBusInfo.arriveTime,
//                        data.recommendBusInfo.color,
//                        data.recommendBusInfo.number,
//                        data.recommendBusInfo.type
//                    )
//                )
//            }
//        }
//    }
//}}