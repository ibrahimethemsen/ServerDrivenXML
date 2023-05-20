package com.ibrahimethemsen.serverdrivenxml.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibrahimethemsen.serverdrivenxml.adapter.HomeAdapter
import com.ibrahimethemsen.serverdrivenxml.databinding.FragmentHomeBinding
import com.ibrahimethemsen.serverdrivenxml.model.ItemActivity
import com.ibrahimethemsen.serverdrivenxml.model.ViewTypeModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val homeAdapter = HomeAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.homeScreenUi.observe(viewLifecycleOwner) {
            setAdapterUi(it)
        }
        viewModel.activityList.observe(viewLifecycleOwner) {
            setAdapterUser(it)
        }
    }

    private fun setAdapterUi(viewTypeList: List<ViewTypeModel>) {
        homeAdapter.setViewTypeList(viewTypeList)
        binding.homeProductRv.adapter = homeAdapter
    }

    private fun setAdapterUser(userListActivity: List<ItemActivity>) {
        homeAdapter.setUserListActivity(userListActivity)
    }
}