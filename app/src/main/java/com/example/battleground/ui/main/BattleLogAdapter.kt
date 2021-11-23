package com.example.battleground.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.battleground.R
import com.example.battleground.databinding.BattleLogRecyclerviewItemBinding

class BattleLogAdapter(context: Context) : RecyclerView.Adapter<BattleLogAdapter.Holder>() {
    private lateinit var binding: BattleLogRecyclerviewItemBinding
    private val inflater = LayoutInflater.from(context)
    private lateinit var mClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = DataBindingUtil.inflate(inflater, R.layout.battle_log_recyclerview_item, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.binding.heroName = mSelectedCardList[position].cardName
//        holder.binding.cardDesign = mSelectedCardList[position].cardDrawable
        holder.itemView.setOnClickListener {
            mClickListener.onItemClickListener(it, position)
        }
        //DataBindingの非同期処理を即座に実行させる
        //上から下にスクロールするときのちらつきをなくすため
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return 2
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.mClickListener = listener
    }

    //インナークラス
    class Holder(val binding: BattleLogRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)
}