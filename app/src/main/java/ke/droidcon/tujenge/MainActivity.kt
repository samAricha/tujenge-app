package ke.droidcon.tujenge

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ke.droidcon.tujenge.navigation.RootNavGraph
import ke.droidcon.tujenge.presentation.google_news_list_page.GoogleNewsListScreen
import ke.droidcon.tujenge.ui.theme.TujengeAppTheme
import org.jetbrains.annotations.NotNull
import timber.log.Timber



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // Release mode logging
        }

        
        setContent {
            val navController = rememberNavController()
            TujengeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    NewsListScreen()
//                    GoogleNewsListScreen()
                    RootNavGraph(navController = navController)

                }
            }

        }

    }
}

