package com.example.android.myapplication.honeyframebalancing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.myapplication.database.Beehive
import com.example.android.myapplication.databinding.ListItemHoneyFrameBalancingBinding

class HoneyFrameBalancingAdapter(val clickListener: HoneyFrameBalancingClickListener):
        ListAdapter<Beehive, HoneyFrameBalancingAdapter.ViewHolder>(HoneyFrameBalancingDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

            class ViewHolder private constructor(val binding: ListItemHoneyFrameBalancingBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(item: Beehive, clickListener: HoneyFrameBalancingClickListener){
                    binding.clickListener = clickListener
                    binding.beehive = item
                    binding.executePendingBindings()
                }
                companion object{
                    fun from(parent: ViewGroup): ViewHolder{
                        val layoutInflater = LayoutInflater.from(parent.context)
                        val binding = ListItemHoneyFrameBalancingBinding.inflate(layoutInflater,parent,false)
                        return ViewHolder(binding)
                    }
                }
            }
        }



class HoneyFrameBalancingDiffCallback: DiffUtil.ItemCallback<Beehive>(){
    override fun areContentsTheSame(oldItem: Beehive, newItem: Beehive): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Beehive, newItem: Beehive): Boolean {
        return oldItem.beehiveId == newItem.beehiveId
    }
}

class HoneyFrameBalancingClickListener(val clickListener: (Beehive: Long) -> Unit){
    fun onClick(beehive: Beehive) = clickListener(beehive.beehiveId)
}