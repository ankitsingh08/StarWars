package ankit.com.starwarepisodes.view.episodes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ankit.com.starwarepisodes.data.repository.EpisodeRepository
import ankit.com.starwarepisodes.model.Episode
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AnkitSingh on 10/28/20.
 */
class EpisodesViewModel @ViewModelInject constructor(var episodeRepository: EpisodeRepository) : ViewModel() {

    private var episodesList = MutableLiveData<List<Episode>>()

    fun getAllEpisode(){
        viewModelScope.launch {
            episodesList.value = episodeRepository.getAllEpisode()
        }
    }

    fun getEpisodeLiveData() = episodesList
}