package ke.droidcon.tujenge.presentation.google_news_list_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ke.droidcon.tujenge.presentation.article_page.MyContent
import ke.droidcon.tujenge.presentation.news_list_page.NewsCard
import ke.droidcon.tujenge.presentation.widgets.CustomCircularProgressIndicator
import ke.droidcon.tujenge.presentation.widgets.SearchComposable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleNewsListScreen(navController: NavController){

    val googleNewsListScreenViewModel: GoogleNewsListScreenViewModel = hiltViewModel()

    val isSyncing by googleNewsListScreenViewModel.isSyncing.collectAsState()


    LaunchedEffect(key1 = Unit, block = {
        googleNewsListScreenViewModel.getNewsList()
    })


    val newsList by googleNewsListScreenViewModel.newsState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(
            title = { Text("DroidConKe23 Tujenge App",
                color = MaterialTheme.colorScheme.primary)
                },
            modifier = Modifier.background(Color(0xff0f9d58))
        )
        },
        content = {

            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column(
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    SearchComposable { query ->
                        googleNewsListScreenViewModel.onSearchQueryChanged(query)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyColumn {
                        item {
                            Text(text = "Our news page")
                        }
                        item {
                            Text(text = "size = ${newsList.size}")
                        }
                        items(newsList) { newsItem ->
                            GoogleNewsCard(
                                newsItem = newsItem,
                                navController = navController
                            )
                        }
                    }
                }

                if (isSyncing) {
                    CustomCircularProgressIndicator()
                }
            }


        }
    )


}