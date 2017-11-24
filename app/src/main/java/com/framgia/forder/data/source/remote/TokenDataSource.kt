package com.framgia.forder.data.source.remote

import com.framgia.forder.data.source.remote.api.response.TokenResponse

/**
 * Created by Tuanlvt on 24/11/2017.
 */
interface TokenDataSource {
    interface LocalDataSource {
        fun saveToken(token: TokenResponse.Token?)

        fun getToken(): TokenResponse.Token?
    }

    interface RemoteDataSource {
        //Todo edit later
    }
}
