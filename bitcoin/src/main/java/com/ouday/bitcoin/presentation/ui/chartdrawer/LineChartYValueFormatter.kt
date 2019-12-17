package com.ouday.bitcoin.presentation.ui.chartdrawer

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class LineChartYValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return if (value > 1000) {
            (value / 1000).toInt().toString() + "K"
        } else {
            value.toInt().toString()
        }
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return if (value > 1000) {
            (value / 1000).toInt().toString() + "K"
        } else {
            value.toInt().toString()
        }
    }
}
