package alexis.tvrs.rickandmortyuniverse.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    @JvmStatic
    fun getConnectivityStatus(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}