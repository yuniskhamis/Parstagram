package com.yunis.parstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.edt_username).text.toString()
        val password = findViewById<EditText>(R.id.edt_password).text.toString()

        loginUser(username, password)


    }

    private fun loginUser(username: String, password: String) {

    }
}