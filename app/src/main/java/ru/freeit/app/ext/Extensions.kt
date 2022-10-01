package ru.freeit.app

import android.content.Context
import android.content.res.ColorStateList
import android.util.StateSet
import androidx.core.content.ContextCompat

fun Context.switchTrackColor() = ColorStateList(
    arrayOf(
        intArrayOf(android.R.attr.state_checked),
        StateSet.WILD_CARD
    ),
    intArrayOf(
        ContextCompat.getColor(this, R.color.teal_200),
        ContextCompat.getColor(this, R.color.purple_700)
    )
)