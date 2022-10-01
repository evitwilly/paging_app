package ru.freeit.app.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.freeit.app.adapter.model.FooterItem
import ru.freeit.app.adapter.model.Item
import ru.freeit.app.adapter.model.TextItem
import ru.freeit.app.adapter.viewholder.FooterViewHolder
import ru.freeit.app.adapter.viewholder.TextItemViewHolder

class TextItemAdapter: ListAdapter<Item, RecyclerView.ViewHolder>(object: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.areItemsTheSame(newItem)
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem.areContentsTheSame(newItem)
}) {

    override fun getItemViewType(position: Int) =
        when (getItem(position)) {
            is FooterItem -> footer
            else -> item
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            item -> TextItemViewHolder.from(parent)
            else -> FooterViewHolder.from(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val item = getItem(position)) {
            is TextItem -> (holder as TextItemViewHolder).bind(item)
            is FooterItem -> (holder as FooterViewHolder).bind(item)
        }

    private companion object {
        const val footer = 1
        const val item = 2
    }

}