package com.framgia.forder.data.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class ErrorResponse {
    @SerializedName("message")
    @Expose
    var errorMessage: ErrorMessage? = null

    class ErrorMessage {
        @SerializedName("description")
        @Expose
        var message: List<String>? = null
        @SerializedName("status")
        @Expose
        var status: Boolean? = null
        @SerializedName("code")
        @Expose
        var code: Int? = null
    }

    fun getMessage(): String? {
        var listMessage: String? = ""
        errorMessage?.message?.let {
            it.indices.forEach { position ->
                listMessage += if (position < it.size - 1) {
                    " - " + it[position] + "\n"
                } else {
                    " - " + it[position]
                }
            }
        }
        return listMessage
    }
}
