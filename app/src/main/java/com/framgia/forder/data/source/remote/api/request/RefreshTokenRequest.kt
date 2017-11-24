package com.framgia.forder.data.source.remote.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class RefreshTokenRequest : BaseRequest() {
  @SerializedName("refresh_token")
  @Expose
  var refresh_token: String? = null
}
