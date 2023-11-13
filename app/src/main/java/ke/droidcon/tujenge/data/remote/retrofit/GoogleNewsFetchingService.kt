package ke.droidcon.tujenge.data.remote.retrofit

import ke.droidcon.tujenge.data.remote.dto.GoogleNewsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleNewsFetchingService {
//    @Headers(
//        "X-RapidAPI-Key: ed7650fe0amshaa7d2a245478b68p1c2f08jsn1cb907bce87c",
//        "X-RapidAPI-Host: google-news-api1.p.rapidapi.com"
//    )

    @GET("everything")
    fun getNewsFromGoogleNews(
        @Query("q") keyword: String,
        @Query("apiKey") apiKey: String = "056fe72eabe24c5dacbbc8d2a82f0d15"
    ): Call<GoogleNewsApiResponse>

}
