package com.frkn.productappkotlin.ui.launch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.frkn.productappkotlin.R
import com.frkn.productappkotlin.ui.login.LoginActivity
import com.frkn.productappkotlin.ui.user.UserActivity

class LaunchActivity : AppCompatActivity() {

    private lateinit var viewModel : LaunchActivityViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        viewModel = ViewModelProvider(this)[LaunchActivityViewModel::class.java]



    }

    override fun onStart() {
        super.onStart()
        viewModel.tokenCheck(this).observe(this) {

            var intent = when(it){
                true -> Intent(this@LaunchActivity , LoginActivity::class.java)
                false -> Intent(this@LaunchActivity , UserActivity::class.java)
            }

            startActivity(intent)
        }
    }
}