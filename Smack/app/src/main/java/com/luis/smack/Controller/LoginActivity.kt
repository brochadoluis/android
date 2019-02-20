package com.luis.smack.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.luis.smack.R
import com.luis.smack.Services.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginLoginBtnClicked (view: View) {
        val email: String = loginEmailText.text.toString()
        val password: String = loginPasswordText.text.toString()
        AuthService.login(this, email, password) { loginSuccess ->
            if(loginSuccess) {
                AuthService.findUserByEmail(this) { findSuccess ->
                    if(findSuccess) finish()
                }
            }
        }
    }

    fun loginRegisterBtnClicked (view: View) {
        val registerUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(registerUserIntent)
        finish()
    }
}
