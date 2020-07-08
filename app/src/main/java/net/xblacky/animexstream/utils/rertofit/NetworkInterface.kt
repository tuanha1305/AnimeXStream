package net.xblacky.animexstream.utils.rertofit

import io.reactivex.Observable
import net.xblacky.animexstream.utils.constants.Const
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

class NetworkInterface {

    //TODO To add Header for undectability

    interface FetchRecentSubOrDub {
        @Headers(
            Const.USER_AGENT,
            Const.ORIGIN,
            Const.REFERER
        )
        @GET("https://ajax.gogocdn.net/ajax/page-recent-release.html")
        fun get(
            @Query("page") page: Int,
            @Query("type") type: Int
        ): Observable<ResponseBody>
    }

    interface FetchPopularFromAjax {

        @Headers(
            Const.USER_AGENT,
            Const.ORIGIN,
            Const.REFERER
        )
        @GET("https://ajax.gogocdn.net/ajax/page-recent-release-ongoing.html")
        fun get(
            @Query("page") page: Int
        ): Observable<ResponseBody>
    }

    interface FetchMovies {
        @Headers(
            Const.USER_AGENT,
            Const.REFERER
        )
        @GET("/anime-movies.html")
        fun get(
            @Query("page") page: Int
        ): Observable<ResponseBody>
    }

    interface FetchNewestSeason {
        @Headers(
            Const.USER_AGENT,
            Const.REFERER
        )

        @GET("/new-season.html")
        fun get(
            @Query("page") page: Int
        ): Observable<ResponseBody>
    }

    interface FetchEpisodeMediaUrl {
        @Headers(
            Const.USER_AGENT,
            Const.REFERER
        )
        @GET
        fun get(
            @Url url: String
        ): Observable<ResponseBody>

    }

    interface FetchAnimeInfo {
        @Headers(
            Const.USER_AGENT,
            Const.REFERER
        )
        @GET
        fun get(
            @Url url: String
        ): Observable<ResponseBody>
    }

    interface FetchM3u8Url {
        @Headers(
            Const.USER_AGENT,
            Const.REFERER
        )
        @GET
        fun get(
            @Url url: String
        ): Observable<ResponseBody>
    }

    interface FetchEpisodeList{
        @Headers(
            Const.USER_AGENT,
            Const.ORIGIN,
            Const.REFERER
        )
        @GET(Const.EPISODE_LOAD_URL)
        fun get(
            @Query("ep_start") startEpisode: Int = 0,
            @Query("ep_end") endEpisode: String,
            @Query("id") id: String,
            @Query("default_ep") defaultEp: Int = 0,
            @Query("alias") alias: String
        ): Observable<ResponseBody>
    }

    interface FetchSearchData{
        @Headers(
            Const.USER_AGENT,
            Const.REFERER
        )
        @GET(Const.SEARCH_URL)
        fun get(
            @Query("keyword") keyword: String,
            @Query("page") page: Int
        ): Observable<ResponseBody>
    }

}