package com.frkn.productappkotlin.ui.login.Signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.frkn.productappkotlin.R
import com.frkn.productappkotlin.models.userSignup
import com.frkn.productappkotlin.utilty.HelperService
import com.frkn.productappkotlin.utilty.LoadingState
import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton

class SignupFragment : Fragment() {

    companion object {
        fun newInstance() = SignupFragment()
    }

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)




    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]

        observeLiveData()
        view?.findViewById<CircularProgressButton>(R.id.btn_signup)?.setOnClickListener{

            signUpClicked(it)

        }

    }

    private fun observeLiveData(){

        val btn_signup : CircularProgressButton? = view?.findViewById(R.id.btn_signup)

        viewModel.loadingSate.observe(viewLifecycleOwner) {

            when(it){
                LoadingState.Loading -> btn_signup?.startAnimation()
                LoadingState.Loaded -> btn_signup?.revertAnimation()
            }

        }


        viewModel.errorState.observe(viewLifecycleOwner){

            HelperService.showErrorMessageByToaster(it)

        }
    }

    fun signUpClicked(view : View){
        var userName : String = view.findViewById<EditText>(R.id.text_signup_username)?.text.toString()
        var email : String = view.findViewById<EditText>(R.id.text_signup_email)?.text.toString()
        var password : String = view.findViewById<EditText>(R.id.text_signup_password)?.text.toString()
        var city : String = view.findViewById<EditText>(R.id.text_signup_city)?.text.toString()

        viewModel.signup(userSignup(userName , email , password , city))
    }

}