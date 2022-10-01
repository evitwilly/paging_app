package ru.freeit.app.adapter.viewholder

import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import ru.freeit.app.adapter.model.FooterItem
import ru.freeit.app.R

class FooterViewHolderBindings(
    val root: View,
    val progress: View,
    val error: View
)

class FooterViewHolder(private val bindings: FooterViewHolderBindings): RecyclerView.ViewHolder(bindings.root) {

    fun bind(item: FooterItem) {
        item.loading(bindings.progress)
        item.error(bindings.error)
    }

    companion object {

        fun from(parent: ViewGroup): FooterViewHolder {
            val frameLayout = FrameLayout(parent.context)
            frameLayout.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                400
            )
            frameLayout.setPadding(56, 56, 56, 56)

            val progress = CircularProgressIndicator(parent.context)
            progress.layoutParams = FrameLayout.LayoutParams(80, 80).apply {
                gravity = Gravity.CENTER
            }
            progress.isIndeterminate = true
            frameLayout.addView(progress)

            val error = TextView(parent.context)
            error.setTextColor(Color.rgb(255, 0, 0))
            error.gravity = Gravity.CENTER
            error.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26f)
            error.text = parent.context.getString(R.string.not_internet_try_again)
            error.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
            frameLayout.addView(error)

            return FooterViewHolder(FooterViewHolderBindings(frameLayout, progress, error))
        }

    }

}