package com.ouday.bitcoin.presentation.ui.chartdrawer

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.ouday.bitcoin.R
import com.ouday.bitcoin.data.model.Value
import java.util.ArrayList

class ChartDrawer {

    fun buildChart(lineChart: LineChart, emptyStateView: View, values: List<Value>?) {
        lineChart.visibility =
            if (values == null || values.isEmpty()) View.GONE else View.VISIBLE
        emptyStateView.visibility =
            if (values == null || values.isEmpty()) View.VISIBLE else View.GONE

        val data = LineData(values?.let { buildYAxis(emptyStateView.context, it) })
        lineChart.axisLeft.isEnabled = false // no left axis
        lineChart.legend.isEnabled = false
        lineChart.axisRight.setDrawGridLines(true)
        lineChart.axisRight.setDrawAxisLine(false)
        lineChart.axisRight.textColor = emptyStateView.context.getColor(R.color.atomsColorsTextSecondaryDayNight)


        lineChart.axisRight.zeroLineWidth = 3f
        lineChart.axisRight.labelCount = 2
        lineChart.axisRight.setDrawZeroLine(false)
        lineChart.xAxis.setDrawAxisLine(false)

        lineChart.axisRight.gridColor = emptyStateView.context.getColor(R.color.dividerColor)
        lineChart.xAxis.gridColor = emptyStateView.context.getColor(R.color.sectionBackground)
        lineChart.xAxis.axisLineWidth = 3f

        lineChart.data = data
        values?.let { buildXAxis(emptyStateView.context, lineChart, it) }

        lineChart.animateX(300)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(false)


        lineChart.highlightValue(null)
        lineChart.invalidate()
        lineChart.description = null

    }

    private fun buildXAxis(context: Context, mChart: LineChart, values: List<Value>) {
        val axis = mChart.xAxis
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.granularity = 1f
        axis.setDrawGridLines(false)
        axis.setCenterAxisLabels(true)
        axis.axisLineWidth = 2f
        axis.textColor = context.getColor(R.color.atomsColorsTextSecondaryDayNight)
        axis.axisLineColor = ContextCompat.getColor(context, R.color.grey)
        axis.valueFormatter =
            IndexAxisValueFormatter(
                ChartDataMapper.getXAxisValues(
                    values
                )
            )
    }

    private fun buildYAxis(context: Context, Values: List<Value>): ArrayList<ILineDataSet> {
        val sety = LineDataSet(
            ChartDataMapper.getYAxisValues(
                Values
            ), null)

        sety.axisDependency = YAxis.AxisDependency.LEFT
        sety.valueFormatter =
            LineChartYValueFormatter()
        sety.color = ContextCompat.getColor(context, R.color.atomsColorsSecondaryAccent)
        sety.circleRadius = 0f
        sety.lineWidth = 2f
        sety.setDrawValues(false)
        sety.setDrawCircles(false)
        sety.mode = LineDataSet.Mode.LINEAR
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(sety)
        return dataSets

    }

}