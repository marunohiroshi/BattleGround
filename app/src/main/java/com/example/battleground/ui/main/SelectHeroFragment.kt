package com.example.battleground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.battleground.BaseFragment
import com.example.battleground.R
import com.example.battleground.databinding.SelectHeroRecyclerviewBinding

class SelectHeroFragment: BaseFragment() {
    private lateinit var binding: SelectHeroRecyclerviewBinding

    companion object {
        @JvmStatic
        fun newInstance(): SelectHeroFragment {
            return SelectHeroFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.select_hero_recyclerview, container, false)
        val gridLayoutManager = GridLayoutManager(context, 4)
        binding.containerRecyclerView.layoutManager = gridLayoutManager
        //RecyclerViewサイズの固定化、パフォーマンス向上
        binding.containerRecyclerView.setHasFixedSize(true)

        val adapter = context?.let { SelectHeroAdapter(it) }

        binding.containerRecyclerView.adapter = adapter
        adapter?.setOnClickListener(object : SelectHeroAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
//                transitionTo(GachaDetailFragment.newInstance(mDisplayCardDataList[position]))
            }
        })
        return binding.root
    }
}