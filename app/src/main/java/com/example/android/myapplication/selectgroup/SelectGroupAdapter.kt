package com.example.android.myapplication.selectgroup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.myapplication.database.BeeGroup
import com.example.android.myapplication.databinding.ListItemSelectGroupBinding

class SelectGroupAdapter(val clickListener: SelectGroupListener): ListAdapter<BeeGroup, SelectGroupAdapter.ViewHolder>(SelectGroupDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

class ViewHolder private constructor(val binding: ListItemSelectGroupBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BeeGroup, clickListener: SelectGroupListener){
        binding.clickListener = clickListener
        binding.group = item
        binding.executePendingBindings()
    }
    companion object{
        fun from(parent: ViewGroup): ViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemSelectGroupBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}
}

class SelectGroupDiffCallback: DiffUtil.ItemCallback<BeeGroup>(){
    override fun areContentsTheSame(oldItem: BeeGroup, newItem: BeeGroup): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: BeeGroup, newItem: BeeGroup): Boolean {
        return oldItem.groupId == newItem.groupId
    }
}

class SelectGroupListener(val clickListener: (BeeGroupId: Long) -> Unit){
    fun onClick(group: BeeGroup) = clickListener(group.groupId)
}