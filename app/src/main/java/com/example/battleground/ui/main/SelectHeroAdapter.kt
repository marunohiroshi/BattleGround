package com.example.battleground.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.battleground.HeroData
import com.example.battleground.R
import com.example.battleground.databinding.SelectHeroRecyclerviewItemBinding

class SelectHeroAdapter(context: Context, ) : RecyclerView.Adapter<SelectHeroAdapter.Holder>() {
    private lateinit var binding: SelectHeroRecyclerviewItemBinding
    private val inflater = LayoutInflater.from(context)
    private lateinit var mClickListener: OnItemClickListener
    private var mHeroData: Array<HeroData> = HeroData.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = DataBindingUtil.inflate(inflater, R.layout.select_hero_recyclerview_item, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.heroName = mHeroData[position].heroName
        holder.binding.heroImage = mHeroData[position].heroImage
        holder.itemView.setOnClickListener {
            mClickListener.onItemClickListener(it, position)
        }
        //DataBindingの非同期処理を即座に実行させる
        //上から下にスクロールするときのちらつきをなくすため
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mHeroData.size
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.mClickListener = listener
    }

    //インナークラス
    class Holder(val binding: SelectHeroRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)
}