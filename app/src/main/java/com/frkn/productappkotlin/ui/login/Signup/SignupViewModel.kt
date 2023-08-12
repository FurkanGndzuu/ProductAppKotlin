package com.frkn.productappkotlin.ui.login.Signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frkn.productappkotlin.apiServices.LoginService
import com.frkn.productappkotlin.models.error
import com.frkn.productappkotlin.models.userSignup
import com.frkn.productappkotlin.utilty.IViewModel
import com.frkn.productappkotlin.utilty.LoadingState
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel(), IViewModel {
    override var loadingSate: MutableLiveData<LoadingState> = MutableLiveData<LoadingState>()
    override var errorState: MutableLiveData<error> = MutableLiveData<error>()


    fun signup(userSignup: userSignup): LiveData<Boolean> {
        var status = MutableLiveData<Boolean>()
        loadingSate.value = LoadingState.Loading
        viewModelScope.launch {
            var response = LoginService.signUp(userSignup)
            status.value = response.isSuccessfull
            if (!response.isSuccessfull && response.error != null)
                errorState.value = response.error!!

            loadingSate.value = LoadingState.Loaded
        }

        return status

    }
}