package com.frkn.productappkotlin.ui.login.Signin

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frkn.productappkotlin.R
import com.frkn.productappkotlin.models.userLogin
import com.frkn.productappkotlin.ui.user.UserActivity
import com.frkn.productappkotlin.utilty.HelperService
import com.frkn.productappkotlin.utilty.LoadingState
import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SigninFragment : Fragment() {

    companion object {
        fun newInstance() = SigninFragment()
    }

    private lateinit var viewModel: SigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SigninViewModel::class.java]

        ObserveLiveData()
        view?.findViewById<CircularProgressButton>(R.id.btn_signin)?.setOnClickListener{

            btnSigninClicked(it )

        }

    }

    private fun ObserveLiveData(){
        val btn_signin : CircularProgressButton? = view?.findViewById(R.id.btn_signin)

        viewModel.loadingSate.observe(viewLifecycleOwner) {

            when(it){
                LoadingState.Loading -> btn_signin?.startAnimation()
                LoadingState.Loaded -> btn_signin?.revertAnimation()
            }

        }


        viewModel.errorState.observe(viewLifecycleOwner){

            HelperService.showErrorMessageByToaster(it)

        }
    }

    private fun btnSigninClicked(view: View){
        val email : String = getView()?.findViewById<TextInputLayout>(R.id.text_signin_email)?.editText?.text.toString()
        val password : String = getView()?.findViewById<TextInputLayout>(R.id.text_signin_password)?.editText?.text.toString()

        viewModel.login(userLogin(email , password)).observe(viewLifecycleOwner){
            if(it){
                var intent = Intent(requireActivity(), UserActivity::class.java)

                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

}