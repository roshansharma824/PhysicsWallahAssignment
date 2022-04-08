package com.example.physicswallahassignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.fragment_user_list.*


class UserListFragment : Fragment() {

    private lateinit var recyclerAdapter: UserListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
        onBackPress()
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = UserListAdapter(requireActivity())
        recyclerView.adapter =recyclerAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        val viewModel:MainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        with(viewModel) {
            this.getLiveDataObserver().observe(viewLifecycleOwner) {
                if (it != null) {
                    progressBar.visibility = View.GONE
                    recyclerAdapter.setUserList(it)
                    recyclerAdapter.notifyDataSetChanged()
                } else {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error in getting list", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            makeAPICall()
        }
    }


    private fun onBackPress(){
        backArrow.setOnClickListener {

            requireActivity().finish()
        }
    }
}