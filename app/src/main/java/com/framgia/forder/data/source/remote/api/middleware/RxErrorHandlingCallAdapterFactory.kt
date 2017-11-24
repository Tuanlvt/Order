package com.framgia.forder.data.source.remote.api.middleware

import android.text.TextUtils
import android.util.Log
import com.framgia.forder.data.source.remote.api.error.BaseException
import com.framgia.forder.data.source.remote.api.response.ErrorResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.reactivex.*
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class RxErrorHandlingCallAdapterFactory private constructor() : CallAdapter.Factory() {

    private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    override fun get(returnType: Type, annotations: Array<Annotation>,
                     retrofit: Retrofit): CallAdapter<*, *> {
        return RxCallAdapterWrapper(returnType,
                original.get(returnType, annotations, retrofit) as CallAdapter<Any, Any>)
    }

    /**
     * RxCallAdapterWrapper
     */
    internal inner class RxCallAdapterWrapper(private val returnType: Type,
                                              private val wrapped: CallAdapter<Any, Any>?) : CallAdapter<Any, Any> {

        override fun responseType(): Type? {
            return wrapped?.responseType()
        }

        override fun adapt(call: Call<Any>): Any? {
            val rawType = CallAdapter.Factory.getRawType(returnType)

            val isFlowable = rawType == Flowable::class.java
            val isSingle = rawType == Single::class.java
            val isMaybe = rawType == Maybe::class.java
            val isCompletable = rawType == Completable::class.java
            if (rawType != Observable::class.java && !isFlowable && !isSingle && !isMaybe) {
                return null
            }
            if (returnType !is ParameterizedType) {
                val name = if (isFlowable)
                    "Flowable"
                else if (isSingle) "Single" else if (isMaybe) "Maybe" else "Observable"
                throw IllegalStateException(name
                        + " return type must be parameterized"
                        + " as "
                        + name
                        + "<Foo> or "
                        + name
                        + "<? extends Foo>")
            }

            if (isFlowable) {
                return (wrapped?.adapt(call) as Flowable<*>).onErrorResumeNext { throwable: Throwable ->
                    Flowable.error(convertToBaseException(throwable))
                }
            }
            if (isSingle) {
                return (wrapped?.adapt(call) as Single<*>).onErrorResumeNext { throwable ->
                    Single.error(convertToBaseException(throwable))
                }
            }
            if (isMaybe) {
                return (wrapped?.adapt(call) as Maybe<*>).onErrorResumeNext { throwable: Throwable ->
                    Maybe.error(convertToBaseException(throwable))
                }
            }
            if (isCompletable) {
                return (wrapped?.adapt(call) as Completable).onErrorResumeNext { throwable ->
                    Completable.error(convertToBaseException(throwable))
                }
            }
            return (wrapped?.adapt(call) as Observable<*>).onErrorResumeNext { throwable: Throwable ->
                Observable.error(convertToBaseException(throwable))
            }
        }

        private fun convertToBaseException(throwable: Throwable): BaseException {
            if (throwable is BaseException) {
                return throwable
            }

            if (throwable is IOException) {
                return BaseException.toNetworkError(throwable)
            }

            if (throwable is HttpException) {
                val response = throwable.response()
                if (response.errorBody() != null) {
                    try {
                        val errorResponse = Gson().fromJson(response.errorBody()?.string(),
                                ErrorResponse::class.java)
                        if (errorResponse != null && !TextUtils.isEmpty(
                                errorResponse.getMessage())) {
                            return BaseException.toServerError(errorResponse)
                        } else {
                            return BaseException.toHttpError(response)
                        }
                    } catch (e: IOException) {
                        Log.e(TAG, e.message)
                    } catch (e: JsonSyntaxException) {
                        Log.e(TAG, e.message, e)
                    }
                } else {
                    return BaseException.toHttpError(response)
                }
            }

            return BaseException.toUnexpectedError(throwable)
        }
    }

    companion object {

        private val TAG = RxErrorHandlingCallAdapterFactory::class.java.name

        fun create(): CallAdapter.Factory {
            return RxErrorHandlingCallAdapterFactory()
        }
    }
}
