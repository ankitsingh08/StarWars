package ankit.com.starwarepisodes.model

import androidx.room.PrimaryKey

/**
 * Created by AnkitSingh on 10/28/20.
 */
data class Episode(
    val episodeId: String,
    val name: String,
    val director: String,
    val number: Float,
    val poster: String,
    val trilogy: Int
)