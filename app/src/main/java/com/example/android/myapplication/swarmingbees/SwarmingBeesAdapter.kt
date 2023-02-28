package com.example.android.myapplication.swarmingbees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.myapplication.database.Beehive
import com.example.android.myapplication.databinding.ListItemSwarmingBeesBinding

class SwarmingBeesAdapter(val clickListener: SwarmingBeesClickListener):
        ListAdapter<Beehive, SwarmingBeesAdapter.ViewHolder>(SwarmingBeesDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


        class ViewHolder private constructor(val binding: ListItemSwarmingBeesBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(item: Beehive, clickListener: SwarmingBeesClickListener){
                    binding.clickListener = clickListener
                    binding.beehive = item
                    binding.executePendingBindings()
                }
            companion object{
                fun from(parent: ViewGroup): ViewHolder{
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ListItemSwarmingBeesBinding.inflate(layoutInflater,parent,false)
                    return ViewHolder(binding)
                }
            }
        }
}

class SwarmingBeesDiffCallback: DiffUtil.ItemCallback<Beehive>(){
    override fun areContentsTheSame(oldItem: Beehive, newItem: Beehive): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Beehive, newItem: Beehive): Boolean {
        return oldItem.beehiveId == newItem.beehiveId
    }
}

class SwarmingBeesClickListener(val clickListener: (BeehiveId: Long) -> Unit){
    fun onClick(beehive: Beehive) = clickListener(beehive.beehiveId)
}