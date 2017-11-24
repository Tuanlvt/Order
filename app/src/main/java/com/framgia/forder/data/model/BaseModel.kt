package com.framgia.forder.data.model

import com.google.gson.Gson

/**
 * Created by Tuanlvt on 24/11/2017.
 */
abstract class BaseModel : Cloneable {

    @Throws(CloneNotSupportedException::class)
    public override fun clone(): BaseModel {
        super.clone()
        val gson = Gson()
        return gson.fromJson(gson.toJson(this), this.javaClass)
    }
}
