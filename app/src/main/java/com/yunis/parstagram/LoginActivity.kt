package com.yunis.parstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.edt_username).text.toString()
        val password = findViewById<EditText>(R.id.edt_password).text.toString()
        val loginButton = findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            loginUser(username, password)
        }


    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG,"Successfully logged in user")
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Error logging in",Toast.LENGTH_SHORT).show()
            }})
        )
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}