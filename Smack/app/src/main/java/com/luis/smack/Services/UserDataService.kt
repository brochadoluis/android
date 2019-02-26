package com.luis.smack.Services

import android.graphics.Color
import com.luis.smack.Controller.App
import java.util.*

object UserDataService {

    var id: String = ""
    var avatarColor: String = ""
    var avatarName: String = ""
    var email: String = ""
    var name: String = ""

    fun returnAvatarColor(components: String): Int {
        val strippedColor = components
            .replace("[", "")
            .replace("]", "")
            .replace(",","")

        var r: Int = 0
        var g: Int = 0
        var b: Int = 0

        val scanner: Scanner = Scanner(strippedColor)
        if (scanner.hasNext()) {
            r = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
            b = (scanner.nextDouble() * 255).toInt()
        }

        return Color.rgb(r,g,b)
    }

    fun logout() {
        var id: String = ""
        var avatarColor: String = ""
        var avatarName: String = ""
        var email: String = ""
        var name: String = ""
        App.prefs.authToken = ""
        App.prefs.userEmail = ""
        App.prefs.isLoggedIn = false
    }
}