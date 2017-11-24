package com.framgia.forder.screen.login

import android.os.Bundle
import com.framgia.forder.R
import com.framgia.forder.screen.BaseActivity
import com.framgia.forder.utils.MainApplication

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class LoginActivity : BaseActivity(), LoginContract.ViewModel {

    companion object {
        val TAG: String = LoginActivity::class.java.name
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerLoginComponent.builder()
                .appComponent((application as MainApplication).appComponent)
                .loginModule(LoginModule(mActivity = this))
                .build()
                .inject(this)
        setContentView(R.layout.activity_login)
    }
}
