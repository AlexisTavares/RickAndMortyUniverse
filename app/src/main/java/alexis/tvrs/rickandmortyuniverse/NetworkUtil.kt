package alexis.tvrs.rickandmortyuniverse

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    @JvmStatic
    fun getConnectivityStatus(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null) {
            activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE
        } else false
    }
}