package ru.freeit.app

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.app.adapter.TextItemAdapter
import ru.freeit.app.listeners.RecyclerViewEndedScrollListener

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val scrollListener = RecyclerViewEndedScrollListener { viewModel.fetch() }

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wifiSwitch = findViewById<SwitchCompat>(R.id.wifi_switch)
        wifiSwitch.trackTintList = switchTrackColor()
        wifiSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.changeWifiEnabled(isChecked)
        }
        viewModel.changeWifiEnabled(wifiSwitch.isChecked)

        val adapter = TextItemAdapter()

        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = adapter
        list.addOnScrollListener(scrollListener)
        recyclerView = list

        val progress = findViewById<FrameLayout>(R.id.progress_view)
        val error = findViewById<FrameLayout>(R.id.error_view)
        error.setOnClickListener { viewModel.fetch() }

        viewModel.observe(this) { state ->
            list.isVisible = true
            error.isVisible = false
            progress.isVisible = false

            when (state) {
                is PagingListState.Loading -> {
                    list.isVisible = false
                    progress.isVisible = true
                }
                is PagingListState.Error -> {
                    list.isVisible = false
                    error.isVisible = true
                }
                is PagingListState.Success -> {
                    adapter.submitList(state.items)
                }
            }


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView?.removeOnScrollListener(scrollListener)
        recyclerView = null
    }

}