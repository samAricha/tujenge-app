package ke.droidcon.tujenge.presentation.google_news_list_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ke.droidcon.tujenge.data.remote.dto.GoogleNewsApiResponse
import ke.droidcon.tujenge.data.remote.dto.NewsArticle
import ke.droidcon.tujenge.data.remote.retrofit.GoogleNewsRetrofitProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class GoogleNewsListScreenViewModel : ViewModel() {

    private val _newsState = MutableStateFlow<List<NewsArticle>>(emptyList())
    val newsState: StateFlow<List<NewsArticle>> = _newsState

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing

    private val searchQuery = MutableStateFlow("")

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
        getNewsList(keyword = searchQuery.value)
    }


    fun  getNewsList(keyword: String = "kenya"){
        _isSyncing.value = true
        viewModelScope.launch {

            val apiKey = "056fe72eabe24c5dacbbc8d2a82f0d15"

            val call: Call<GoogleNewsApiResponse> = GoogleNewsRetrofitProvider
                .createGoogleNewsFetchingService()
                .getNewsFromGoogleNews(keyword = keyword, apiKey =  apiKey)

            call.enqueue(object : Callback<GoogleNewsApiResponse> {
                override fun onResponse(call: Call<GoogleNewsApiResponse>, response: Response<GoogleNewsApiResponse>) {
                    if (response.isSuccessful) {
                        val newsResponse = response.body()
                        if (newsResponse != null) {
                            if (newsResponse.status == "ok"){
                                val articles = newsResponse.articles
                                println(">>>>>>getting news list<<<<<<<<<")

                                articles.let {
                                    if (it != null) {
                                        _newsState.value = it
                                    }
                                }
                                _isSyncing.value = false
                            }
                                Timber.tag("SUCCESS>>>>>>>>> :->").d("Successful response: $newsResponse")
                        }
                    } else {
                        Timber.tag("FAILURE>>>>>>>>>> :->").d("Unsuccessful response. Code: ${response.code()}, Message: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GoogleNewsApiResponse>, t: Throwable) {
                    Timber.tag("FAILURE>>>>>>>>> :->").d("Request failed: ${t.message}")
                }

            })
        }
    }

}