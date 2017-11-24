package com.framgia.forder.screen.login

import android.app.Activity
import com.framgia.forder.utils.dagger.ActivityScope
import com.framgia.forder.utils.rx.BaseSchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Tuanlvt on 24/11/2017.
 */
@Module
class LoginModule(private val mActivity: Activity) {

    @ActivityScope
    @Provides
    fun providePresenter(schedulerProvider: BaseSchedulerProvider): LoginContract.Presenter {
        val presenter = LoginPresenter()
        presenter.setViewModel(mActivity as LoginContract.ViewModel)
        presenter.setSchedulerProvider(schedulerProvider)
        return presenter
    }
}
