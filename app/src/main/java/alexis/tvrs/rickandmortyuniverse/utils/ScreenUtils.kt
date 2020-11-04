package alexis.tvrs.rickandmortyuniverse.utils

import android.content.Context

object ScreenUtils {
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    private fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }
}