package com.example.poclocalizeactivity.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal fun RecyclerView.initVertical() {
    val rvLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
    this.apply {
        setHasFixedSize(true)
        layoutManager = rvLayoutManager
    }
}
