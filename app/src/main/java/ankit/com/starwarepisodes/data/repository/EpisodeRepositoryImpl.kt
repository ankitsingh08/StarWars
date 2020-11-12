package ankit.com.starwarepisodes.data.repository

import ankit.com.starwarepisodes.data.network.EpisodeService
import ankit.com.starwarepisodes.model.Episode
import ankit.com.starwarepisodes.util.UIResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by AnkitSingh on 10/29/20.
 */
class EpisodeRepositoryImpl @Inject constructor(private val episodeService: EpisodeService): EpisodeRepository {

    override suspend fun getAllEpisode(): Flow<UIResponseState<List<Episode>>> {
        return flow {
            emit(UIResponseState.Loading)
            try {
                val episodes = episodeService.getAllEpisodes()
                emit(UIResponseState.Success(episodes))
            } catch (exception: Exception) {
                emit(UIResponseState.Error(exception))
            }
        }
    }
}