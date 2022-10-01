package ru.freeit.app.adapter.model

import android.view.View
import androidx.core.view.isVisible

class FooterItem(
    private val isLoading: Boolean = false,
    private val isError: Boolean = false
): Item {

    override fun areItemsTheSame(other: Item) = true
    override fun areContentsTheSame(other: Item) = other is FooterItem
        && isLoading && other.isLoading
        && isError && other.isError

    fun loading(view: View) {
        view.isVisible = isLoading
    }

    fun error(view: View) {
        view.isVisible = isError
    }
}