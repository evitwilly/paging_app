package ru.freeit.app

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.freeit.app.adapter.model.FooterItem
import ru.freeit.app.adapter.model.Item
import ru.freeit.app.adapter.model.TextItem
import kotlin.random.Random

sealed class PagingListState {
    object Loading: PagingListState()
    object Error: PagingListState()
    class Success(val items: List<Item>): PagingListState()
}

class MainViewModel: ViewModel() {

    private val handler = Handler(Looper.getMainLooper())

    private var id = 0
    private var currentPage = 0

    private val currentState = mutableListOf<Item>()
    private val state = MutableLiveData<PagingListState>()
    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<PagingListState>) =
        state.observe(lifecycleOwner, observer)

    private var isWifiEnabled: Boolean = true

    fun changeWifiEnabled(enabled: Boolean) {
        isWifiEnabled = enabled
    }

    init {
        fetch()
    }

    fun fetch() {
        state.value = loading()

        handler.postDelayed({

            if (isWifiEnabled.not()) {
                if (currentState.isEmpty()) {
                    state.value = PagingListState.Error
                    return@postDelayed
                }

                currentState.removeLast()
                currentState.add(FooterItem(isError = true))
            } else {
                currentPage++

                currentState.removeLastOrNull()
                currentState.addAll(generateRandomList(currentPage))
            }

            state.value = PagingListState.Success(currentState)

        }, delay)
    }

    private fun loading(): PagingListState {
        if (currentState.isEmpty()) return PagingListState.Loading

        val newList = currentState.toMutableList()
        newList.removeLastOrNull()
        newList.add(FooterItem(isLoading = true))
        return PagingListState.Success(newList)
    }

    private fun generateRandomList(page: Int) =
        List(pageCount) { index ->
            if (index < pageCount - 1) {
                id++
                TextItem(id, "Count: $id, page: $page", randomColor())
            } else {
                FooterItem()
            }
        }

    private fun randomColor() = Color.rgb(
        Random.nextInt(0, 255),
        Random.nextInt(0, 255),
        Random.nextInt(0, 255)
    )

    private companion object {
        const val delay = 3000L
        const val pageCount = 15
    }

}