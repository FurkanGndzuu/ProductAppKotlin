package com.frkn.productappkotlin.utilty

import android.app.Application
import android.content.Context

class globalApp : Application() {

    companion object{
        private lateinit var  context : Context

        fun getContext():Context{
            return context;
        }

       fun getActivityContext(contextcs: Context){
            context = contextcs
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }
}