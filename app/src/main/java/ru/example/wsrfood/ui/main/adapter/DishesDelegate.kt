package ru.example.wsrfood.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import ru.example.wsrfood.data.db.entity.DishEntity
import ru.example.wsrfood.databinding.ItemDishBinding
import ru.example.wsrfood.ui.core.BaseAdapterDelegate

class DishesDelegate : BaseAdapterDelegate() {
    override fun isForItem(item: Any, items: MutableList<Any>, position: Int): Boolean =
        item is DishEntity

    override fun getViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder =
        DishesViewHolder(ItemDishBinding.inflate(inflater,parent,false))

    override fun onBindViewHolder(item: Any, holder: ViewHolder, payloads: MutableList<Any>) {
        val viewHolder = holder as DishesViewHolder
        viewHolder.bind(item as DishEntity)
    }

    inner class DishesViewHolder(
        private val binding: ItemDishBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: DishEntity) {
            binding.ivDish.load("http://food.madskill.ru/up/images/" + item.icon)
            binding.tvPrice.text = "N${item.price}"
            binding.tvTitle.text = item.nameDish
        }
    }
}