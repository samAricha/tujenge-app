package ke.droidcon.tujenge.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import ke.droidcon.tujenge.domain.NewsArticleInfo
import ke.droidcon.tujenge.presentation.article_page.ArticleScreen
import ke.droidcon.tujenge.presentation.google_news_list_page.GoogleNewsListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = "newsList"
) {
    NavHost(
        navController = navController,
        startDestination = "newsList",
        builder = {
            composable(route = "newsList") {
                // Your NewsListScreen content
                GoogleNewsListScreen(navController = navController)
            }

            composable(
                route = "newsDetails/{title}/{url}",
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("url") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title")
                val url = backStackEntry.arguments?.getString("url")
                if (title != null && url != null) {
                    ArticleScreen(title = title, url = url)
                } else {
                    // Handle the case where the arguments are null
                }
            }

        }
    )
}