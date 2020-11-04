package alexis.tvrs.rickandmortyuniverse.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

class ScreenUtils {
    companion object {
        fun getScreenWidth(context: Context): Int {
            return context.resources.displayMetrics.widthPixels
        }

        private fun getScreenHeight(context: Context): Int {
            return context.resources.displayMetrics.heightPixels
        }
    }
}