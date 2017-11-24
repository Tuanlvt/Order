package com.framgia.forder.data.source.local

import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsKey
import com.framgia.forder.data.source.remote.TokenDataSource
import com.framgia.forder.data.source.remote.api.response.TokenResponse
import com.framgia.forder.utils.common.StringUtils
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class TokenLocalDataSource @Inject constructor(
        private val mSharedPrefsApi: SharedPrefsApi) : TokenDataSource.LocalDataSource {

    override fun saveToken(token: TokenResponse.Token?) {
        val data = Gson().toJson(token)
        mSharedPrefsApi.put(SharedPrefsKey.KEY_TOKEN, data)
    }

    override fun getToken(): TokenResponse.Token? {
        val data = mSharedPrefsApi[SharedPrefsKey.KEY_TOKEN, String::class.java]
        return if (StringUtils.isBlank(data)) {
            null
        } else {
            Gson().fromJson(data, TokenResponse.Token::class.java)
        }
    }
}
