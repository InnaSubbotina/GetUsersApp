package com.innasubbotina.usersapp.presentation.utils

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(private val dividerDrawable: Drawable)
    : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        rect: Rect,
        view: View,
        parent: RecyclerView,
        s: RecyclerView.State
    ) {
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        val position = parent.getChildAdapterPosition(view)
            .let { if (it == RecyclerView.NO_POSITION) return else it }
        rect.bottom =
            if (position % 2 == 0 && parent.adapter?.getItemViewType(childAdapterPosition) == 2) 0
            else dividerDrawable.intrinsicHeight
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children
            .forEach { view ->
                val childAdapterPosition = parent.getChildAdapterPosition(view)
                val position = parent.getChildAdapterPosition(view)
                    .let { if (it == RecyclerView.NO_POSITION) return else it }
                if (position % 2 != 0 && parent.adapter?.getItemViewType(childAdapterPosition) == 2) {
                    dividerDrawable.drawSeparator(view, parent, canvas)
                } else {
                    Unit
                }
            }
    }

    private fun Drawable.drawSeparator(view: View, parent: RecyclerView, canvas: Canvas) =
        apply {
            val left = parent.paddingLeft
            val top = view.bottom
            val right = left + dividerDrawable.intrinsicWidth
            val bottom = top + dividerDrawable.intrinsicHeight
            bounds = Rect(left, top, right, bottom)
            draw(canvas)
        }

}

