package com.example.test2effectivemobile.presentation.homestore

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class GridLayoutWithoutScrolling(context: Context, spanCount: Int): GridLayoutManager(context, spanCount) {

    init {
        this.isSmoothScrollbarEnabled
    }
}