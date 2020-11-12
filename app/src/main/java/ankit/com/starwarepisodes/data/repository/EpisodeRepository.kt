package ankit.com.starwarepisodes.data.repository

import ankit.com.starwarepisodes.model.Episode
import ankit.com.starwarepisodes.util.UIResponseState
import kotlinx.coroutines.flow.Flow

/**
 * Created by AnkitSingh on 10/28/20.
 */
interface EpisodeRepository {

    suspend fun getAllEpisode(): Flow<UIResponseState<List<Episode>>>
}