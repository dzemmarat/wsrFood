package ru.example.wsrfood.ui.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

abstract class BaseAdapterDelegate :
    AbsListItemAdapterDelegate<Any, Any, BaseAdapterDelegate.ViewHolder>() {

    protected var listSize = 0
    protected var currentPosition = 0
    protected var allItems =  ArrayList<Any>()

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return getViewHolder(inflater, parent)
    }

    open class ViewHolder(
        convertView: View
    ) : RecyclerView.ViewHolder(convertView), LayoutContainer {
        override val containerView: View? get() = itemView
    }

    override fun onBindViewHolder(item: Any, holder: ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as CurrentViewHolder
        viewHolder.bind(item)
    }

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        listSize = items.size
        allItems.add(item)
        currentPosition = position
        return isForItem(item, items, position)
    }

    fun deleteItem(position: Int) {
        allItems.removeAt(position)
    }

    abstract fun isForItem(item: Any, items: MutableList<Any>, position: Int): Boolean

    abstract fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder

    abstract inner class CurrentViewHolder(convertView: View) : ViewHolder(convertView) {
        abstract fun bind(item: Any)
    }
}