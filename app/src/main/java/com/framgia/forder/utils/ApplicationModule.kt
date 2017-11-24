package com.framgia.forder.utils

import android.content.Context
import com.framgia.forder.data.source.local.TokenLocalDataSource
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl
import com.framgia.forder.data.source.remote.TokenRepository
import com.framgia.forder.data.source.remote.TokenRepositoryImpl
import com.framgia.forder.data.source.remote.api.TokenRemoteDataSource
import com.framgia.forder.data.source.remote.api.middleware.InterceptorImpl
import com.framgia.forder.utils.dagger.AppScope
import com.framgia.forder.utils.rx.BaseSchedulerProvider
import com.framgia.forder.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor

/**
 * Created by Tuanlvt on 24/11/2017.
 */
@Module
class ApplicationModule(private val mContext: Context) {

    @Provides
    @AppScope
    fun provideApplicationContext(): Context {
        return mContext
    }

    @Provides
    @AppScope
    fun provideSharedPrefsApi(): SharedPrefsApi {
        return SharedPrefsImpl(mContext)
    }

    @Provides
    @AppScope
    fun provideBaseSchedulerProvider(interceptor: Interceptor,
                                     tokenRepository: TokenRepository): BaseSchedulerProvider {
        (interceptor as InterceptorImpl).setTokenRepository(tokenRepository)
        return SchedulerProvider.instance
    }

    @Provides
    @AppScope
    fun provideTokenRepository(localDataSource: TokenLocalDataSource,
                               remoteDataSource: TokenRemoteDataSource): TokenRepository {
        return TokenRepositoryImpl(localDataSource, remoteDataSource)
    }
}
