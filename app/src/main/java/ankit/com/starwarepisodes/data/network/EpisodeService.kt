package ankit.com.starwarepisodes.data.network

import ankit.com.starwarepisodes.model.Episode
import retrofit2.http.GET

/**
 * Created by AnkitSingh on 10/28/20.
 */
interface EpisodeService {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    @GET("orionthewake/episodes-flow/master/data/episodes.json")
    suspend fun getAllEpisodes(): List<Episode>

    @GET("orionthewake/episodes-flow/master/data/favorites.json")
    suspend fun getFavoritesSortOrder() : List<Episode>
}