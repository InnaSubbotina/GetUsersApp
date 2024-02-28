package com.innasubbotina.usersapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.tabs.TabLayout
import com.innasubbotina.data.mapper.departments
import com.innasubbotina.data.repository.UserRepositoryImpl.Companion.ALL
import com.innasubbotina.usersapp.R
import com.innasubbotina.usersapp.databinding.FragmentMainBinding
import com.innasubbotina.usersapp.presentation.adapters.UserAdapter
import com.innasubbotina.usersapp.presentation.utils.CustomItemDecoration
import com.innasubbotina.usersapp.presentation.utils.DialogSort
import com.innasubbotina.usersapp.presentation.utils.RadioButtonListener
import com.innasubbotina.usersapp.presentation.viewmodel.MainViewModel
import com.innasubbotina.usersapp.presentation.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(), OnClickUserDetails {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var shimmerView: ShimmerFrameLayout
    private var tabName: String = ALL
    private val navController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shimmerView = ShimmerFrameLayout(context)
        shimmerView.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        viewModel = ViewModelProvider(
            this, MainViewModelFactory(requireContext()))[MainViewModel::class.java]
        getResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDecoration()
        viewModel.userLiveData.observe(
            viewLifecycleOwner, Observer { it ->
                binding.apply {
                    adapter.submitList(it)
                }
            })
        addTabName()
        setRefreshListener()
        addTabListener()
        onClickSortButton()
        searchViewListener()
    }

    private fun setDecoration() {
        binding.rcViewListUser.layoutManager = LinearLayoutManager(activity)
        adapter = UserAdapter(this)
        binding.rcViewListUser.adapter = adapter
        val drawable = AppCompatResources.getDrawable(requireActivity(),R.drawable.divider)
        val decor = CustomItemDecoration(drawable!!)
        binding.rcViewListUser.addItemDecoration(decor)
    }

    private fun getResult() {
        CoroutineScope(Dispatchers.IO).launch {
            val usersInfo = viewModel.loadAllUsers().getOrNull()
             activity?.runOnUiThread {
               adapter.submitList(usersInfo)
            }
        }
    }

    private fun searchViewListener() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { return true }
            override fun onQueryTextChange(newText: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    val list = viewModel.searchUser(newText!!)
                    activity?.runOnUiThread {
                        if (list.isEmpty()) {
                            navController.navigate(R.id.notFoundFragment)
                        } else {
                            adapter.submitList(list)
                        }
                    }
                }
                return true
            }
        })
    }

    private fun setRefreshListener() {
        binding.refreshLayout.setOnRefreshListener{
            getResult()
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun removeRefreshListener() {
        binding.refreshLayout.setOnRefreshListener{
                binding.refreshLayout.isRefreshing = false
        }
    }

    private fun setFilter(tab: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val depFilters = viewModel.filterByDepartment(tab).getOrNull()
            activity?.runOnUiThread {
                adapter.submitList(depFilters)
            }
        }
    }

    private fun addTabName() {
        departments.forEach{
            binding.tabLay.addTab(binding.tabLay.newTab().setText(it.key))
        }
    }

    private fun addTabListener() : String  {
            binding.tabLay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabName = if (tab!!.equals(ALL)) {
                    setFilter(tab.text.toString())
                    ALL
                } else {
                    setFilter(tab.text.toString())
                    removeRefreshListener()
                    tab.text.toString()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        return tabName
    }

    override fun onClickUserDetails(bungleUser: Bundle) {
        navController.navigate(R.id.userDetailsFragment,bungleUser)
    }

    private fun sortUsersByABC(tabName: String) {
       this.tabName = tabName
        CoroutineScope(Dispatchers.IO).launch {
            val list = viewModel.sortUsersByABC(tabName)
            activity?.runOnUiThread {
                adapter.submitList(list)
            }
        }
    }

    private fun sortUsersByDOB(tabName: String) {
        this.tabName = tabName
        CoroutineScope(Dispatchers.IO).launch {
            val list = viewModel.sortUsersByDOB(tabName)
            activity?.runOnUiThread {
                adapter.submitList(list)
            }
        }
    }

    private fun onClickSortButton() {
        binding.buttonDialogSort.setOnClickListener {
            DialogSort.showDialog(requireActivity(), object : RadioButtonListener {
                override fun onClickSortABC() {
                    sortUsersByABC(addTabListener())
                    removeRefreshListener()
                }
                override fun onClickSortDOB() {
                    sortUsersByDOB(addTabListener())
                }
            })
        }
    }

}