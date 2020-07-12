package com.ord.coronastats.ui.world

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ord.coronastats.R
import com.ord.coronastats.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_world.*

class WorldFragment : Fragment() {

    companion object {
        fun newInstance() = WorldFragment()
    }

    private lateinit var viewModel: WorldViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_world, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            InjectorUtils.provideWorldViewModelFactory()
        ).get(WorldViewModel::class.java)

        viewModel.fetchWorldStats()

        bindUI()
    }

    private fun bindUI() {

        viewModel.fetchWorldStats().observe(viewLifecycleOwner, Observer {
            tv_world_cases_nb.text = String.format("%,d", it.cases)
            tv_world_deaths_nb.text = String.format("%,d", it.deaths)
            tv_world_recovered_nb.text = String.format("%,d", it.recovered)
//            tv_updated.text = getString(
//                R.string.main_last_updated,
//                SimpleDateFormat("EEEE, d MMMM yyyy - hh:mm:ss aa", Locale.getDefault()).format(it.updated)
//            )
        })


        swipeRefreshLayout = swipe_to_refresh_world
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            refreshWorld()
        }
    }

    private fun refreshWorld() {
        requireActivity().recreate()
    }
}