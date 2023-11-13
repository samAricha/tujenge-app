package ke.droidcon.tujenge.data.remote.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object GoogleNewsRetrofitProvider {
    private const val BASE_URL = "https://newsapi.org/v2/"

    private fun provide(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }//to ignore unknown keys

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkhttpClient())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(HeaderInterceptor)
            .build()

    fun createGoogleNewsFetchingService(): GoogleNewsFetchingService {
        return provide().create(GoogleNewsFetchingService::class.java)
    }


}