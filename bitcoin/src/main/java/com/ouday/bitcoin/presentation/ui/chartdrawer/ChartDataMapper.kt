package com.ouday.bitcoin.presentation.ui.chartdrawer


import com.github.mikephil.charting.data.Entry
import com.ouday.bitcoin.data.model.Value
import com.ouday.core.utils.toCalendar
import com.ouday.core.utils.toReadableDateFormat
import com.ouday.core.utils.toReadableTimeFormat
import java.lang.StringBuilder
import java.util.ArrayList

object ChartDataMapper {

    private val MAX_NUMBER_FRO_EVERY_PAGE = 5


    fun getCurrentPageData(allData: List<Value>, previousPage: Int, currentPage: Int): List<Value> {
        return allData.subList(
            previousPage * MAX_NUMBER_FRO_EVERY_PAGE,
            currentPage * MAX_NUMBER_FRO_EVERY_PAGE
        )
    }

    fun getXAxis(allData: List<Value>): List<String> {
        val data = ArrayList<String>()
        for (value in allData) {
            data.add(value.x.toCalendar().toReadableDateFormat())
        }

        return data
    }

    fun getYAxis(allData: List<Value>): List<String> {
        val data = ArrayList<String>()
        for ((_, y) in allData) {
            data.add(y.toString())
        }
        return data
    }

    fun getXAxisValues(allData: List<Value>?): List<String> {
        val data = ArrayList<String>()
        if (allData != null) {
            for (index in allData.indices) {
                data.add(allData[index].x.toCalendar().toReadableTimeFormat())
            }
        }
        return data
    }

    fun getYAxisValues(allData: List<Value>?): List<Entry> {
        val data = ArrayList<Entry>()
        if (allData != null) {
            for (index in allData.indices) {
                data.add(
                    Entry(
                        index.toFloat(),
                        java.lang.Float.parseFloat(allData[index].y.toString())
                    )
                )
            }
        }
        return data
    }


}
