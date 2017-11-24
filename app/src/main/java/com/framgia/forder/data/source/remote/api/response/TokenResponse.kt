package com.framgia.forder.data.source.remote.api.response

import com.framgia.forder.data.model.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class TokenResponse : BaseRespone() {
    @SerializedName("fauth")
    @Expose
    var token: Token? = null

    class Token : BaseModel() {
        @SerializedName("access_token")
        @Expose
        var accessToken: String? = null
        @SerializedName("refresh_token")
        @Expose
        var refreshToken: String? = null
    }
}
