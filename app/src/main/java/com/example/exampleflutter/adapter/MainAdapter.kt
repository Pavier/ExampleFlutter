package com.example.exampleflutter.adapter

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.example.exampleflutter.R
import com.example.exampleflutter.data.ItemBean

/**
 * @author pw
 * @date 2023/10/26 11:30
 *
 */
class MainAdapter(data : MutableList<ItemBean>) : BaseQuickAdapter<ItemBean,QuickViewHolder>(data) {
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: ItemBean?) {
        holder.setText(R.id.title,item?.title)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.item_layout,parent)
    }
}