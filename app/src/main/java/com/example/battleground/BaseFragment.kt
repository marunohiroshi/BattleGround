package com.example.battleground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){
    companion object {
        fun newInstance(baseFragment: BaseFragment): Companion {
            return BaseFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Nullable
    abstract override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    fun isBackStackClearNeeded(): Boolean {
        return false
    }

    fun transitionTo(newFragment: BaseFragment) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
//        fragmentTransaction?.replace(R.id.container, newFragment, newFragment.javaClass.simpleName)
        fragmentTransaction?.add(R.id.container, newFragment, newFragment.javaClass.simpleName)
        val id = fragmentTransaction?.commit()
    }
}