package com.example.android.myapplication.beestatistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.R
import com.example.android.myapplication.database.BeeDatabase
import com.example.android.myapplication.databinding.FragmentStatisticsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

class BeeStatisticsFragment: Fragment() {
    private lateinit var ourPieChart: PieChart
    private lateinit var ourBarChart: BarChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentStatisticsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_statistics,container,false)

        val application = requireNotNull(this.activity).application
        val arguments = BeeStatisticsFragmentArgs.fromBundle(requireArguments())
        val dataSource = BeeDatabase.getInstance(application).beeDatabaseDao
        val viewModelFactory = BeeStatisticsViewModelFactory(arguments.groupkey,dataSource)
        val beeStatisticsViewModel = ViewModelProvider(this,viewModelFactory).get(BeeStatisticsViewModel::class.java)

        binding.beeStatisticsViewModel = beeStatisticsViewModel
        binding.setLifecycleOwner(this)

        ourPieChart = binding.ourPieChart
        ourBarChart = binding.ourBarChart

        val population = Array<Int>(5) {0}
        val populationname = Array<String>(5) {"n"}
        val queenBeeAge = Array<Int>(6){0}

        for (i in 1..5) {
            population[i-1] = beeStatisticsViewModel.getCountBadPop(i)
        }
        for (i in 0..5){
            queenBeeAge[i] = beeStatisticsViewModel.getQueenBeeYear(i)
        }
        populationname[0] = "Very Bad"
        populationname[1] = "Bad"
        populationname[2] = "Medium"
        populationname[3] = "Good"
        populationname[4] = "Very Good"

        populatePieChart(population,populationname)
        queenBeeAgeBarChart(queenBeeAge)
        binding.requiredQeenbee.text = beeStatisticsViewModel.getAllBadQueenbee().toString() + " / "
        binding.sumQueenbee.text = beeStatisticsViewModel.getAllQueenbee().toString()

        return binding.root
    }
    fun populatePieChart(values: Array<Int>, labels: Array<String>) {
        //an array to store the pie slices entry
        val ourPieEntry = ArrayList<PieEntry>()
        var i = 0

        for (entry in values) {
            //converting to float
            var value = values[i].toFloat()
            var label = labels[i]
            //adding each value to the pieentry array
            ourPieEntry.add(PieEntry(value, label))
            i++
        }

        //assigning color to each slices
        val pieShades: ArrayList<Int> = ArrayList()
        pieShades.add(Color.parseColor("#0E2DEC"))
        pieShades.add(Color.parseColor("#03A9F4"))
        pieShades.add(Color.parseColor("#288D2B"))
        pieShades.add(Color.parseColor("#FFC107"))
        pieShades.add(Color.parseColor("#FF5722"))

        //add values to the pie dataset and passing them to the constructor
        val ourSet = PieDataSet(ourPieEntry, "")
        val data = PieData(ourSet)

        //setting the slices divider width
        ourSet.sliceSpace = 1f

        //populating the colors and data
        ourSet.colors = pieShades
        ourPieChart.data = data
        //setting color and size of text
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(10f)

        //add an animation when rendering the pie chart
        ourPieChart.animateY(1400, Easing.EaseInOutQuad)
        //disabling center hole
        ourPieChart.isDrawHoleEnabled = false
        //do not show description text
        ourPieChart.description.isEnabled = false
        //legend enabled and its various appearance settings
        ourPieChart.legend.isEnabled = true
        ourPieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        ourPieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        ourPieChart.legend.isWordWrapEnabled = true

        //dont show the text values on slices
        ourPieChart.setDrawEntryLabels(false)
        //refreshing the chart
        ourPieChart.invalidate()

    }

    private fun queenBeeAgeBarChart(values: Array<Int>) {
        //adding values
        val ourBarEntries: ArrayList<BarEntry> = ArrayList()
        var i = 0

        for (entry in values) {
            var value = values[i].toFloat()
            ourBarEntries.add(BarEntry(i.toFloat(), value))
            i++
        }


        val barDataSet = BarDataSet(ourBarEntries, "")
        //set a template coloring
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        val data = BarData(barDataSet)
        ourBarChart.data = data
        //setting the x-axis
        val xAxis: XAxis = ourBarChart.xAxis
        //calling methods to hide x-axis gridlines
        ourBarChart.axisLeft.setDrawGridLines(false)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove legend
        ourBarChart.legend.isEnabled = false

        //remove description label
        ourBarChart.description.isEnabled = false

        //add animation
        ourBarChart.animateY(1400)
        //refresh the chart
        ourBarChart.invalidate()
    }

}