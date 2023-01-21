package com.example.android.myapplication.beegroups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.myapplication.database.BeeGroup
import com.example.android.myapplication.databinding.ListItemBeeGroupBinding

class BeeGroupsAdapter(val clickListener: BeeGroupListener):
    ListAdapter<BeeGroup, BeeGroupsAdapter.ViewHolder>(BeeGroupsDiffCallback()){

   /* override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }*/

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


class ViewHolder private constructor(val binding: ListItemBeeGroupBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(item: BeeGroup, clickListener: BeeGroupListener){
        binding.clickListener = clickListener
        binding.group = item
        binding.executePendingBindings()
    }

    companion object{
        fun from(parent: ViewGroup): ViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBeeGroupBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
  }
}

class BeeGroupsDiffCallback : DiffUtil.ItemCallback<BeeGroup>(){

    override fun areContentsTheSame(oldItem: BeeGroup, newItem: BeeGroup): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: BeeGroup, newItem: BeeGroup): Boolean {
        return oldItem.groupId == newItem.groupId
    }
}

class BeeGroupListener(val clickListener: (BeeGroupId: Long) -> Unit){
    fun onClick(group: BeeGroup) = clickListener(group.groupId)
}
