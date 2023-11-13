package ke.droidcon.tujenge.data.remote.dto

import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


@Serializable
data class GoogleNewsApiResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<NewsArticle>?
)

@Serializable
data class NewsArticle(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

@Serializable
data class Source(
    val id: String?,
    val name: String?
)
