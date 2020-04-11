package com.nottriangle.memento.facebook

import android.util.Log
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod


class FacebookApi {

    suspend fun fetchUserPhotos() {
        val accessToken = AccessToken.getCurrentAccessToken()
        val userId = accessToken.userId
        val path = "/${accessToken.userId}/albums?fields=id,cover_photo"
        Log.e("XXX", "fetching " + userId + " " + path)
        GraphRequest(
            accessToken,
            "/${accessToken.userId}/albums",
            null,
            HttpMethod.GET,
            GraphRequest.Callback {
                Log.e("XXX", "result " + it.rawResponse)
            }
        ).executeAsync()
    }

    suspend fun fetchAlbumCover(albumId: String) {
        val response = GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/${albumId}?fields=cover_photo",
            null,
            HttpMethod.GET
        ).executeAndWait()
        Log.e("XXX", "al details " + response.rawResponse)
    }

    suspend fun fetchAlbumPhotos(albumId: String) {
        val response = GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/${albumId}/photos",
            null,
            HttpMethod.GET
        ).executeAndWait()
        Log.e("XXX", "al photos" + response.rawResponse)
    }
}