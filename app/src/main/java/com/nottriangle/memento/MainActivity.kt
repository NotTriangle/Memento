package com.nottriangle.memento

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.nottriangle.memento.facebook.FacebookApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setPermissions(listOf("public_profile", "user_photos"))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                loginResult.accessToken.userId


            }

            override fun onCancel() {
                // App code
                Log.e("XXX", "onCancel")
            }

            override fun onError(exception: FacebookException) {
                // App code
                Log.e("XXX", "exce[ti " + exception)
            }
        })

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            FacebookApi().fetchUserPhotos()
            FacebookApi().fetchAlbumCover("150671114957384")
            FacebookApi().fetchAlbumPhotos("150671114957384")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
