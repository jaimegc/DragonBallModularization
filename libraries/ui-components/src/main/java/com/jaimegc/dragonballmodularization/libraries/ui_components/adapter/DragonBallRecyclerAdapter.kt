package com.jaimegc.dragonballmodularization.libraries.ui_components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaimegc.dragonballmodularization.libraries.base.data.DragonBallInfo
import com.jaimegc.dragonballmodularization.libraries.ui_components.customview.DragonBallCellView
import com.jaimegc.dragonballmodularization.libraries.ui_components.databinding.ItemDragonballCellBinding

class DragonBallRecyclerAdapter(
    private var dragonBallList: MutableList<DragonBallInfo> = mutableListOf(),
    private val dragonBallCellActionsDelegate: DragonBallCellView.DragonBallCellActionsDelegate
) : RecyclerView.Adapter<DragonBallRecyclerAdapter.DragonBallViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonBallViewHolder {
        val binding = ItemDragonballCellBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DragonBallViewHolder(binding)
    }

    fun addDragonBallList(newDragonBallList: List<DragonBallInfo>) {
        dragonBallList.clear()
        dragonBallList.addAll(newDragonBallList)

        notifyDataSetChanged()
    }

    override fun getItemCount() = dragonBallList.size

    override fun onBindViewHolder(holder: DragonBallViewHolder, position: Int) =
        holder.bind(dragonBallList[position])

    inner class DragonBallViewHolder(private val binding: ItemDragonballCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dragonBall: DragonBallInfo) {
            binding.dragonBallCellView.initDetails(
                dragonBallInfo = dragonBall,
                actionDelegate = dragonBallCellActionsDelegate
            )
        }
    }
}