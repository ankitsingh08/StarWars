package ankit.com.starwarepisodes.data.repository

import ankit.com.starwarepisodes.model.Episode

/**
 * Created by AnkitSingh on 10/28/20.
 */
interface EpisodeRepository {

    suspend fun getAllEpisode(): List<Episode>
}