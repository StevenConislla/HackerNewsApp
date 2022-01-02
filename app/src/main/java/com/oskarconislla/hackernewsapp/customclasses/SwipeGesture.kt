package com.oskarconislla.hackernewsapp.customclasses

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import android.R
import android.content.Context
import androidx.core.content.ContextCompat

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator




open class SwipeGesture(context : Context) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
    val labelColor = ContextCompat.getColor(context,R.color.white)

    val deleteColor = ContextCompat.getColor(context,R.color.holo_red_dark)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(deleteColor)
            .addSwipeLeftLabel("Delete").setSwipeLeftLabelColor(labelColor)
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}