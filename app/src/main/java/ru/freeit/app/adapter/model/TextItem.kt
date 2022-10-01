package ru.freeit.app.adapter.model

import android.widget.TextView

class TextItem(
    private val id: Int,
    private val text: String,
    private val color: Int
): Item {

    override fun areItemsTheSame(other: Item) = other is TextItem && id == other.id
    override fun areContentsTheSame(other: Item) = other is TextItem && text == other.text && color == other.color

    fun text(view: TextView) {
        view.text = text
        view.setBackgroundColor(color)
    }
}