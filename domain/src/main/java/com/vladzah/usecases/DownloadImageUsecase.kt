package com.vladzah.usecases

import android.content.Context
import android.app.DownloadManager
import android.os.Environment
import androidx.core.net.toUri
import com.vladzah.domain.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface DownloadImageUsecase {
    suspend fun execute(url: String, id: Int) : Long
}

class DownloadImageUsecaseImpl @Inject constructor(
    private val context: Context
) : DownloadImageUsecase {

    override suspend fun execute(url: String, id: Int): Long {
        return withContext(Dispatchers.IO) {
            val request = DownloadManager.Request(url.toUri())
                .setTitle("${id}.jpg")
                .setDescription("Downloading image with id $id")
                .setMimeType("image/jpeg")
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .addRequestHeader("Authorization", BuildConfig.API_KEY)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${id}.jpg")

            return@withContext context.getSystemService(DownloadManager::class.java).enqueue(request)
        }
    }
}