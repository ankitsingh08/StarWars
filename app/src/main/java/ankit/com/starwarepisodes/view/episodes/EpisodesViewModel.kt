package ankit.com.starwarepisodes.view.episodes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import ankit.com.starwarepisodes.data.repository.EpisodeRepository
import ankit.com.starwarepisodes.model.Episode
import ankit.com.starwarepisodes.util.UIResponseState
import ankit.com.starwarepisodes.util.successOr
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AnkitSingh on 10/28/20.
 */
class EpisodesViewModel @ViewModelInject constructor(var episodeRepository: EpisodeRepository) : ViewModel() {

    private val episodesList =  MutableLiveData<List<Episode>>()

    fun getAllEpisode(){
        viewModelScope.launch {
            episodeRepository.getAllEpisode()
                .map { it.successOr(emptyList()) }
                .collect {
                episodesList.value = it
            }
        }

    }

    fun getEpisodeLiveData() = episodesList
}