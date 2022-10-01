package ru.freeit.app.adapter.model

sealed interface Item {
    fun areItemsTheSame(other: Item): Boolean
    fun areContentsTheSame(other: Item): Boolean
}