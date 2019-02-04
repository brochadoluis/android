package com.luis.smack.Controller

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.Toast
import com.android.volley.toolbox.Volley
import com.luis.smack.R
import com.luis.smack.Services.AuthService
import com.luis.smack.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profileDefault"
    var avatarColor ="[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        createSpinner.visibility = View.INVISIBLE
    }

    fun generateUserAvatar(view: View) {
        val random = Random()
        val color = random.nextInt(2)
        val avatar = random.nextInt(28)

        userAvatar = if(color == 0) {
            "light$avatar"
        } else {
            "dark$avatar"
        }
        val resourceID = resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImageView.setImageResource(resourceID)
    }

    fun generateColorClicked(view: View) {
        val random = Random()
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        createAvatarImageView.setBackgroundColor(Color.rgb(r,g,b))
        val saveR = r.toDouble() / 255
        val saveG = g.toDouble() / 255
        val saveB = b.toDouble() / 255

        avatarColor = "[$saveR, $saveG, $saveB, 1]"
    }

    fun createUserClicked(view: View) {
        enableSpinner(true)
        val username = createUsernameText.text.toString()
        val email = createEmailText.text.toString()
        val password = createPasswordText.text.toString()

        if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.register(this, email, password) {
                    registerSuccess -> if(registerSuccess) {
                AuthService.login(this, email, password) {
                        loginSuccess -> if(loginSuccess) {
                    AuthService.createUser(this, username, email, userAvatar, avatarColor){ createSuccess ->
                        if(createSuccess) {
                            val userDataChange: Intent = Intent(BROADCAST_USER_DATA_CHANGE)
                            LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChange)
                            enableSpinner(false)
                            finish()
                        } else {
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }
                }
            } else {
                errorToast("Make sure username, email and password are filled in.")
            }
            }
        }
    }

    fun enableSpinner(enable: Boolean) {
        if(enable){
            createSpinner.visibility = View.VISIBLE
        } else {
            createSpinner.visibility = View.INVISIBLE
        }

        createUserBtn.isEnabled = !enable
        createAvatarImageView.isEnabled = !enable
        backgroundColorBtn.isEnabled = !enable
    }

    fun errorToast(errorMessage: String = "Something went wrong, please try again."){
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }
}
