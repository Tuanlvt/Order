package com.framgia.forder.data.source.remote.api

import com.framgia.forder.data.source.remote.BaseRemoteDataSource
import com.framgia.forder.data.source.remote.TokenDataSource
import com.framgia.forder.data.source.remote.api.service.FOrderApi
import javax.inject.Inject

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class TokenRemoteDataSource @Inject
constructor(nameApi: FOrderApi) : BaseRemoteDataSource(nameApi), TokenDataSource.RemoteDataSource
