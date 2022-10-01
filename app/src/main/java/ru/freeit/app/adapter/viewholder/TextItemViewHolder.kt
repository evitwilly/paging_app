package ru.freeit.app.adapter.viewholder

import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.app.adapter.model.TextItem

class TextItemViewHolder(private val view: TextView): RecyclerView.ViewHolder(view) {

    fun bind(item: TextItem) {
        item.text(view)
    }

    companion object {
        fun from(parent: ViewGroup): TextItemViewHolder {
            val textView = TextView(parent.context)
            textView.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            textView.setPadding(56, 56, 56, 56)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26f)
            return TextItemViewHolder(textView)
        }
    }
}