package com.ord.coronastats.ui.world

import android.os.Bundle
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
import java.text.SimpleDateFormat
import java.util.*

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

        tb_world.title = "Total Cases"
        tb_world.subtitle = "Refreshing..."

        viewModel.fetchWorldStats().observe(viewLifecycleOwner, Observer {
            tv_world_cases_nb.text = String.format("%,d", it.cases)
            tv_world_today_cases.text = getString(R.string.today, String.format("%,d", it.todayCases))
            tv_world_active.text = getString(R.string.active, String.format("%,d", it.active))
            tv_world_critical.text = getString(R.string.critical, String.format("%,d", it.critical))

            tv_world_recovered_nb.text = String.format("%,d", it.recovered)
            tv_world_today_recovered.text = getString(R.string.today, String.format("%,d", it.todayRecovered))

            tv_world_deaths_nb.text = String.format("%,d", it.deaths)
            tv_world_today_deaths.text = getString(R.string.today, String.format("%,d", it.todayDeaths))


            tb_world.subtitle = getString(
                R.string.main_last_updated,
                SimpleDateFormat("EEEE, d MMMM yyyy - hh:mm:ss aa", Locale.getDefault()).format(it.updated)
            )
        })

        ll_world_cases.setOnClickListener {
            ll_world_more_cases.apply {
                visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }

        ll_world_recovered.setOnClickListener {
            ll_world_more_recovered.apply {
                visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }

        ll_world_dead.setOnClickListener {
            ll_world_more_dead.apply {
                visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }


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