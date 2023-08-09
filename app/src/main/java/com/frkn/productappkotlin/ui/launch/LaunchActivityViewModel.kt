package com.frkn.productappkotlin.ui.launch

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frkn.productappkotlin.apiServices.TokenService
import com.frkn.productappkotlin.models.error
import com.frkn.productappkotlin.utilty.IViewModel
import com.frkn.productappkotlin.utilty.LoadingState
import kotlinx.coroutines.launch

class LaunchActivityViewModel : ViewModel(), IViewModel {
    override var loadingSate: MutableLiveData<LoadingState> = MutableLiveData<LoadingState>()
    override var errorState: MutableLiveData<error> = MutableLiveData<error>()

    fun tokenCheck(): LiveData<Boolean> {
        loadingSate.value = LoadingState.Loading
        var result = MutableLiveData<Boolean>()

        viewModelScope.launch {

            var response = TokenService.checkToken()

            result.value = response.isSuccessfull

            if (!response.isSuccessfull && response.error != null)
                errorState.value = response.error!!

        }

        return result
    }

}