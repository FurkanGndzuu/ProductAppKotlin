package com.frkn.productappkotlin.utilty

import androidx.lifecycle.MutableLiveData
import com.frkn.productappkotlin.models.error

interface IViewModel {
    var loadingSate : MutableLiveData<LoadingState>
    var errorState : MutableLiveData<error>
}