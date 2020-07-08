package net.xblacky.animexstream.ui.main.search

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.xblacky.animexstream.utils.rertofit.NetworkInterface
import okhttp3.ResponseBody
import retrofit2.Retrofit

class SearchRepository(private val retrofit: Retrofit) {

    fun fetchSearchList(keyWord: String, pageNumber: Int): Observable<ResponseBody> {
        val searchService = retrofit.create(NetworkInterface.FetchSearchData::class.java)
        return searchService.get(keyWord, pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}