package com.framgia.forder.data.source.remote.api.middleware

import com.framgia.forder.data.source.remote.TokenRepository
import com.framgia.forder.utils.common.StringUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class InterceptorImpl : Interceptor {

    private val TAG = javaClass.name
    private val KEY_TOKEN = "Authorization"

    private lateinit var mTokenRepository: TokenRepository

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = initializeHeader(chain)
        var request = builder.build()
        var response = chain.proceed(request)
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            builder.removeHeader(KEY_TOKEN)
            builder.addHeader(KEY_TOKEN, getAccessToken())
            request = builder.build()
            response = chain.proceed(request)
        }
        return response
    }

    fun setTokenRepository(tokenRepository: TokenRepository) {
        mTokenRepository = tokenRepository
    }

    private fun initializeHeader(chain: Interceptor.Chain): Request.Builder {
        val originRequest = chain.request()
        return originRequest.newBuilder()
                .header("Accept", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store")
                .addHeader(KEY_TOKEN, getAccessToken())
                .method(originRequest.method(), originRequest.body())
    }

    private fun getAccessToken(): String? {
        var accessToken = mTokenRepository.getToken()?.accessToken
        if (StringUtils.isBlank(accessToken)) {
            accessToken = ""
        }
        return accessToken
    }
}
