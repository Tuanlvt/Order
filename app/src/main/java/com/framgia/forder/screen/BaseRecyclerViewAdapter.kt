package com.framgia.forder.screen

import android.content.Context
import android.support.v7.widget.RecyclerView

/**
 * Created by Tuanlvt on 24/11/2017.

 * @param <V> is a type extend from [RecyclerView.ViewHolder]
</V> */

abstract class BaseRecyclerViewAdapter<V : RecyclerView.ViewHolder> protected constructor(
        protected val context: Context) : RecyclerView.Adapter<V>()

