package com.frkn.productappkotlin.ui.login.Signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frkn.productappkotlin.apiServices.LoginService
import com.frkn.productappkotlin.models.error
import com.frkn.productappkotlin.models.userLogin
import com.frkn.productappkotlin.utilty.IViewModel
import com.frkn.productappkotlin.utilty.LoadingState
import kotlinx.coroutines.launch

class SigninViewModel : ViewModel(), IViewModel {
    override var loadingSate: MutableLiveData<LoadingState> = MutableLiveData<LoadingState>()
    override var errorState: MutableLiveData<error> = MutableLiveData<error>()

    fun login(userLogin: userLogin): LiveData<Boolean> {
        loadingSate.value = LoadingState.Loading
        var status = MutableLiveData<Boolean>()


        viewModelScope.launch {

            var response = LoginService.signIn(userLogin)
            status.value = response.isSuccessfull

            if (!response.isSuccessfull && response.error != null) errorState.value =
                response.error!!

            loadingSate.value = LoadingState.Loaded


        }
        return status

    }

}