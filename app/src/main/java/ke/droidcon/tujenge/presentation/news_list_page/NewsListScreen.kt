package ke.droidcon.tujenge.presentation.news_list_page

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ke.droidcon.tujenge.presentation.google_news_list_page.GoogleNewsListScreenViewModel

@Composable
fun NewsListScreen(){

    val newsListScreenViewModel: NewsListScreenViewModel = hiltViewModel();

    val newsList by newsListScreenViewModel.newsState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        newsListScreenViewModel.getNewsList()
    })

    // Use the observed state in your Compose UI
    LazyColumn {
        items(newsList) { newsItem ->
            NewsCard(newsItem = newsItem)
        }
    }

}