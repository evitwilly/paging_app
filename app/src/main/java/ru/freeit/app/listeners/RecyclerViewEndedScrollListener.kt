package ru.freeit.app.listeners

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewEndedScrollListener(private val hasEnded: () -> Unit): RecyclerView.OnScrollListener() {

    private var hasScrolled = false

    private var layoutManager: LinearLayoutManager? = null
    private var adapter: RecyclerView.Adapter<*>? = null

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (layoutManager == null) {
            layoutManager = recyclerView.layoutManager as? LinearLayoutManager
        }
        if (adapter == null) {
            adapter = recyclerView.adapter
        }

        if (newState == RecyclerView.SCROLL_STATE_IDLE && hasScrolled) {
            if (adapter != null && layoutManager != null && adapter?.itemCount == layoutManager?.findLastCompletelyVisibleItemPosition()?.plus(1)) {
                hasEnded.invoke()
            }
            hasScrolled = false
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        hasScrolled = true
    }

}