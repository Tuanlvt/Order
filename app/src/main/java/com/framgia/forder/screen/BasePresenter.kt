package com.framgia.forder.screen

/**
 * Created by Tuanlvt on 24/11/2017.
 */
/**
 * BasePresenter

 * @param <T> class extend from BaseViewModel
</T> */

interface BasePresenter<T : BaseViewModel> {

    fun onStart()

    fun onStop()

    fun setViewModel(viewModel: T)
}
