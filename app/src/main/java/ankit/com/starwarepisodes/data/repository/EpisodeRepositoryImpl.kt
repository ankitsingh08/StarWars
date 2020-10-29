package ankit.com.starwarepisodes.data.repository

import ankit.com.starwarepisodes.data.network.EpisodeService
import ankit.com.starwarepisodes.model.Episode
import javax.inject.Inject

/**
 * Created by AnkitSingh on 10/29/20.
 */
class EpisodeRepositoryImpl @Inject constructor(private val episodeService: EpisodeService): EpisodeRepository {

    override suspend fun getAllEpisode(): List<Episode> {
        return episodeService.getAllEpisodes()
    }
}