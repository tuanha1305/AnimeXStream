package net.xblacky.animexstream.utils

import android.content.Context
import net.xblacky.animexstream.utils.constants.Const

object Utils {
    fun getTypeName(typeValue: Int): String {
        return when (typeValue) {
            Const.TYPE_RECENT_DUB -> "Recent Dub"
            Const.TYPE_RECENT_SUB -> "Recent Sub"
            Const.TYPE_MOVIE -> "Movies"
            Const.TYPE_POPULAR_ANIME -> "Popular Anime"
            Const.TYPE_GENRE -> "Categories"
            Const.TYPE_NEW_SEASON -> "New Season"
            else -> "Default"
        }
    }

    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Float
    ): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

    fun getPositionByType(typeValue: Int): Int {
        return when (typeValue) {
            Const.TYPE_RECENT_SUB -> Const.RECENT_SUB_POSITION
            Const.TYPE_NEW_SEASON -> Const.NEWEST_SEASON_POSITION
            Const.TYPE_RECENT_DUB -> Const.RECENT_SUB_POSITION
            Const.TYPE_MOVIE -> Const.MOVIE_POSITION
            Const.TYPE_POPULAR_ANIME -> Const.POPULAR_POSITION
            else -> 0
        }
    }
}