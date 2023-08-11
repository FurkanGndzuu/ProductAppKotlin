package com.frkn.productappkotlin.ui.login.Signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frkn.productappkotlin.R

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
    }

}