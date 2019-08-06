package com.example.poclocalizeactivity

import android.app.Application
import com.ice.restring.RestringConfig
import com.ice.restring.Restring



class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Restring.init(
            this,
            RestringConfig.Builder()
            .build()
        )
    }
}