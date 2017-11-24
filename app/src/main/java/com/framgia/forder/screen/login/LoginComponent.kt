package com.framgia.forder.screen.login

import com.framgia.forder.utils.AppComponent
import com.framgia.forder.utils.dagger.ActivityScope
import dagger.Component

/**
 * Created by Tuanlvt on 24/11/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}
