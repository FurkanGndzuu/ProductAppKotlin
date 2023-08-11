package com.frkn.productappkotlin.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.frkn.productappkotlin.R
import com.frkn.productappkotlin.adapters.screenSlideAdapter
import com.frkn.productappkotlin.ui.login.Signin.SigninFragment
import com.frkn.productappkotlin.ui.login.Signup.SignupFragment


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewPagerLogin: ViewPager2 = findViewById(R.id.viewPagerLogin)

        var viewPagerAdapter = screenSlideAdapter(this)
        viewPagerAdapter.addFragment(SigninFragment())
        viewPagerAdapter.addFragment(SignupFragment())

        viewPagerLogin.adapter = viewPagerAdapter
    }
}