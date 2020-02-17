package com.harsh.dkatalislabs.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Author: Harsh Mittal
 * Date:   2020-02-14
 * Github: https://github.com/harshmittal2810
 */

class ConnectionHelper(private val context: Context) {

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}