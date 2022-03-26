package com.yunis.parstagram

import com.parse.Parse

import android.app.Application
import com.parse.ParseObject


class ParstagramApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ParseObject.registerSubclass(Post::class.java)

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        )
    }
}