package com.ouday.bitcoin.presentation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.akaita.android.circularseekbar.CircularSeekBar
import com.ouday.bitcoin.presentation.ui.chartdrawer.ChartDrawer
import com.ouday.bitcoin.presentation.viewmodel.TransactionViewModel
import com.ouday.core.network.Status
import com.ouday.core.presentation.BaseFragment
import com.ouday.core.presentation.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bitcoin_chart.*
import javax.inject.Inject
import kotlin.math.roundToInt
import com.google.android.material.snackbar.Snackbar
import com.ouday.bitcoin.R


class BitcoinChartFragment : BaseFragment() {

    private lateinit var snackbar: Snackbar
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModel: TransactionViewModel? = null

    private var weeks = 0
    private var rollingAverage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bitcoin_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(TransactionViewModel::class.java)
        setTransactionCallback()
        setSeekbarRollingAverageListener()
        setSeekbarWeeksListener()
    }

    private fun setTransactionCallback(){
        viewModel?.getTransactionLiveData()?.observe(this@BitcoinChartFragment, androidx.lifecycle.Observer {
            when (it.status) {
                Status.LOADING -> startLoadingState()
                Status.ERROR -> stopLoadingState()
                Status.SUCCESS -> {
                    stopLoadingState()
                    ChartDrawer().buildChart(linechart, emptyStateView, it?.data?.values)
                }
            }
        })
    }

    private fun setSeekbarWeeksListener(){
        seekbarWeeks.setOnCircularSeekBarChangeListener(object:
            CircularSeekBar.OnCircularSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: CircularSeekBar?,
                progress: Float,
                fromUser: Boolean
            ) {
                //Do Nothing
            }

            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {
                //Do Nothing
            }

            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {
                seekBar?.progress?.roundToInt()?.let {
                    weeks = seekBar?.progress?.roundToInt()
                }
                requestData()
            }

        })
    }

    private fun setSeekbarRollingAverageListener(){
        seekbarRollingAverage.setOnCircularSeekBarChangeListener(object:
            CircularSeekBar.OnCircularSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: CircularSeekBar?,
                progress: Float,
                fromUser: Boolean
            ) {
                //Do Nothing
            }

            override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {
                //Do Nothing
            }

            override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {
                seekBar?.progress?.roundToInt()?.let {
                    rollingAverage = seekBar?.progress?.roundToInt()
                }
                requestData()
            }
        })
    }


    private fun requestData(){
        viewModel?.requestAllRates(weeks, rollingAverage)
    }

    private fun startLoadingState(){
        seekbarRollingAverage.isEnabled = false
        seekbarWeeks.isEnabled = false
        snackbar = Snackbar
            .make(coordinator, getString(R.string.loading), Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun stopLoadingState(){
        seekbarRollingAverage.isEnabled = true
        seekbarWeeks.isEnabled = true
        snackbar.dismiss()
    }

}
