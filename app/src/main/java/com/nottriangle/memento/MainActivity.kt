package com.nottriangle.memento

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.Credentials
import com.google.auth.oauth2.UserCredentials
import com.google.photos.library.v1.PhotosLibraryClient
import com.google.photos.library.v1.PhotosLibrarySettings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val credentials: Credentials = UserCredentials.newBuilder()
            .setClientId("465911247389")
            .setClientSecret("1venjjm75asi4kdh57bqdmiockpf2ofl")
            .setRefreshToken("apps.googleusercontent.com")
            .build()
        val settings = PhotosLibrarySettings.newBuilder()
            .setCredentialsProvider(
                FixedCredentialsProvider.create(credentials)
            )
            .build()
        val client = PhotosLibraryClient.initialize(settings)

        val a = client.listAlbums()
        Log.e("XXX", "a " + a)
    }
}
