package com.framgia.forder.screen.login

import com.framgia.forder.utils.rx.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class LoginPresenter : LoginContract.Presenter {
    private lateinit var mSchedulerProvider: BaseSchedulerProvider
    private var mViewModel: LoginContract.ViewModel? = null
    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    companion object {
        private val TAG = LoginPresenter::class.java.name
    }

    override fun onStart() {

    }

    override fun onStop() {
        mCompositeDisposable.clear()
    }

    override fun setViewModel(viewModel: LoginContract.ViewModel) {
        mViewModel = viewModel
    }

    fun setSchedulerProvider(schedulerProvider: BaseSchedulerProvider) {
        mSchedulerProvider = schedulerProvider
    }
}
