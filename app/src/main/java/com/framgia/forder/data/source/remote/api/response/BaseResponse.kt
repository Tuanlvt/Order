package com.framgia.forder.data.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Tuanlvt on 24/11/2017.
 */

class BaseResponse<T> {
    @Expose
    @SerializedName("messages")
    var messages: String? = null
    @Expose
    @SerializedName("items")
    var items: T? = null
    @Expose
    @SerializedName("item")
    var item: T? = null
}
