package com.example.digimetest

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import com.example.digimetest.model.StepsDTO


class MainActivity : DigiMeBaseAcitivty() {
    var dataElements = arrayOf(
        R.drawable.screen1,
        R.drawable.screen2,
        R.drawable.screen3,
        R.drawable.screen4,
        R.drawable.screen5,
        R.drawable.screen6,
        R.drawable.screen7,
        R.drawable.screen8,
        R.drawable.screen9,
        R.drawable.screen10,
        R.drawable.screen_11,
        R.drawable.screen12,
        R.drawable.screen13,
        R.drawable.screen18,
        R.drawable.screen19,
        R.drawable.screen20,
        R.drawable.screen21
    )
    lateinit var pagerAdapter: SliderViewAdapter
    lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = SliderViewAdapter(
            this,
            dataElements, this
        )

        viewPager.adapter = pagerAdapter
    }

    override fun onDataLoading() {
        showDialog()
    }

    private fun showDialog() {
        progressDialog = Dialog(this)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.show()
    }

    override fun onDataReady() {

        progressDialog.dismiss()

        //group the data by month
        val result = (stepsData.clone() as ArrayList<StepsDTO>).groupBy {
            DateHelper.getMonthStringFromMillis(it.createddate)
        }

        //look for data for the current month
        if (!result.containsKey(DateHelper.getCurrentMonthString()) && !result.containsKey(
                DateHelper.getPreviousMonthString()
            )
        ) {
            Log.i(
                "DATA",
                "No data for month ${DateHelper.getCurrentMonthString()} and ${DateHelper.getPreviousMonthString()} show failure screen"
            )
            runOnUiThread {
                pagerAdapter.updateScreenStatus(SuccessStates.EMPTY)
            }
        } else {
            //we need to calculate average
            val firstMonthData = result[DateHelper.getCurrentMonthString()]
            val secondMonthData = result[DateHelper.getPreviousMonthString()]

            var firstMonthAverage: Long = 0
            var secondMonthAverage: Long = 0

            firstMonthData?.forEach {
                it.steps?.let { steps ->
                    firstMonthAverage += steps
                }
            }

            firstMonthAverage /= firstMonthData!!.size

            secondMonthData?.forEach {
                it.steps?.let { steps ->
                    secondMonthAverage += steps
                }
            }

            secondMonthAverage /= secondMonthData!!.size

            if (firstMonthAverage > secondMonthAverage) {
                runOnUiThread {
                    pagerAdapter.updateScreenStatus(SuccessStates.DATA)
                }
            } else {
                runOnUiThread {
                    pagerAdapter.updateScreenStatus(SuccessStates.NODATA)
                }
            }
        }
    }
}
