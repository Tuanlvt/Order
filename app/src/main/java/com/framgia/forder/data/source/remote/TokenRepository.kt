package com.framgia.forder.data.source.remote

import com.framgia.forder.data.source.local.TokenLocalDataSource
import com.framgia.forder.data.source.remote.api.TokenRemoteDataSource
import com.framgia.forder.data.source.remote.api.response.TokenResponse
import javax.inject.Inject

/**
 * Created by Tuanlvt on 24/11/2017.
 */
interface TokenRepository : TokenDataSource.LocalDataSource, TokenDataSource.RemoteDataSource

open class TokenRepositoryImpl @Inject constructor(
        private val tokenLocalDataSource: TokenLocalDataSource,
        private val tokenRemoteDataSource: TokenRemoteDataSource) : TokenRepository {
    override fun saveToken(token: TokenResponse.Token?) {
        tokenLocalDataSource.saveToken(token)
    }

    override fun getToken(): TokenResponse.Token? {
        return tokenLocalDataSource.getToken()
    }
}
