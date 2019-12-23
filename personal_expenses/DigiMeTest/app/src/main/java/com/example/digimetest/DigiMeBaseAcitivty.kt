package com.example.digimetest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.digimetest.model.StepsDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.digi.sdk.DMEPullClient
import me.digi.sdk.entities.DMEPullConfiguration
import me.digi.sdk.interapp.DMEAppCommunicator
import me.digi.sdk.utilities.crypto.DMECryptoUtilities
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


abstract class DigiMeBaseAcitivty : AppCompatActivity(), OnItemClickListener, IDataReady {

    companion object {
        private const val DATA_TIMER = "dataTimer"
        private const val DATA_TIMER_INTERVAL_MS = 15 * 1000L
    }

    lateinit var pullClient: DMEPullClient
    var stepsData: ArrayList<StepsDTO> = ArrayList()
    var dataTimer: Timer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Fabric.with(this, Crashlytics())

        val privateKeyHex = DMECryptoUtilities(applicationContext).privateKeyHexFrom(
            "9at238yCe1HDartjPnjZqzk2ZJjg120t.p12",
            "digime"
        )
        val configuration = DMEPullConfiguration(
            "huHKYJeuMat4o6aP5tYuWm32dkDupVbr",
            "9at238yCe1HDartjPnjZqzk2ZJjg120t",
            privateKeyHex
        )

        pullClient = DMEPullClient(applicationContext, configuration)
    }


    private fun waitFileDownloadComplete() {
        //this timer will check the data between the files
        if (dataTimer == null) {
            onDataLoading()
            //we are adding this to be 20seconds but it can be lest than that based on the quantity and connection
            //15 and 20 seconds shows a good result for both time and performance to make sure all is fine
            dataTimer =
                fixedRateTimer(DATA_TIMER, false, DATA_TIMER_INTERVAL_MS, DATA_TIMER_INTERVAL_MS) {
                    cancel()

                    onDataReady()
                }
        }
    }


    override fun onItemClick(position: Int) {
        pullClient.authorize(this) { session, error ->
            pullClient.getSessionData({ file, error ->

                // Handle each downloaded file here.
                val fileNameIdentifier = file?.identifier.toString()

                val fileNameIdentifierAttr = fileNameIdentifier.split("_")

                if (fileNameIdentifierAttr[DigiMeAttr.attr_version] == "18" && fileNameIdentifierAttr[DigiMeAttr.attr_group] == "4" && fileNameIdentifierAttr[DigiMeAttr.attr_data_group] == "301") {
                    val data = file?.fileContentAsJSON()
                    val listType = object : TypeToken<List<StepsDTO>>() {}.type

                    data?.asString?.let { dataAsString ->
                        val activities =
                            Gson().fromJson<List<StepsDTO>>(dataAsString, listType)

                        //every file data will be added to stepsData
                        stepsData.addAll(activities)
                        waitFileDownloadComplete()
                    }
                }

            }) { error ->
                // Any errors interupting the flow of data will be directed here, or null once all files are retrieved.
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        DMEAppCommunicator.getSharedInstance().onActivityResult(requestCode, resultCode, data)
    }

    abstract override fun onDataLoading()
    abstract override fun onDataReady()
}