package com.noby.core.utils

import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException

import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import android.net.NetworkCapabilities


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
}
fun isInternetAvailable(): Boolean {
    try {
        val address = InetAddress.getByName("www.google.com")
        return !address.equals("")
    } catch (e: UnknownHostException) {
        // Log error
    }
    return false
}
 fun internetConnectionAvailable(timeOut: Int): Boolean {
    var inetAddress: InetAddress? = null
    try {
        val future: Future<InetAddress?>? =
            Executors.newSingleThreadExecutor().submit(Callable<InetAddress?> {
                try {
                    InetAddress.getByName("google.com")
                } catch (e: UnknownHostException) {
                    null
                }
            })
        if (future != null) {
            inetAddress = future[timeOut.toLong(), TimeUnit.MILLISECONDS]
        }
        future?.cancel(true)
    } catch (_: InterruptedException) {
    } catch (_: ExecutionException) {
    } catch (_: TimeoutException) {
    }
    return inetAddress != null && !inetAddress .equals("")
}