package com.fatapps.trams

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fatapps.trams.databinding.TimeTableActivityBinding
import kotlinx.android.synthetic.main.time_table_activity.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TimeTableActivity : AppCompatActivity() {
    lateinit var model: TimeTableViewModel
    lateinit var binding: TimeTableActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stopId = intent.getStringExtra(stop_id_extra)
        val stopNr = intent.getStringExtra(stop_nr_extra)

        model = getViewModel { parametersOf(stopId, stopNr) }

        binding = DataBindingUtil.setContentView(this, R.layout.time_table_activity)
        binding.vm = model

    }

    companion object {
        const val stop_id_extra = "STOP_ID_EXTRA"
        const val stop_nr_extra = "STOP_NR_EXTRA"

        fun open(context: Context, stopId: String, stopNr:String) {
            val intent = Intent(context, TimeTableActivity::class.java)
            intent.putExtra(stop_id_extra, stopId)
            intent.putExtra(stop_nr_extra, stopNr)
            context.startActivity(intent)
        }
    }
}
