package ke.droidcon.tujenge.presentation.article_page

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(title: String, url: String){
    Scaffold(
        topBar = { TopAppBar(
            title = { Text(
                "DroidConKe23 Tujenge App",
                color = MaterialTheme.colorScheme.primary)
                    },
            modifier = Modifier.background(Color(0xff0f9d58))
        )}
    ){
        MyContent(title = title, url = url)
    }

}

@Composable
fun MyContent(
    title: String,
    url: String
){
    val articleUrl = url

    Column {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Title: $title",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.height(20.dp)
        )

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(articleUrl)
            }
        }, update = {
            it.loadUrl(articleUrl)
        })
    }

}