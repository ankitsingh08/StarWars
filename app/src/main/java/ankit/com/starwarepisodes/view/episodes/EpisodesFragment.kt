package ankit.com.starwarepisodes.view.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ankit.com.starwarepisodes.databinding.EpisodesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by AnkitSingh on 10/28/20.
 */
@AndroidEntryPoint
class EpisodesFragment: Fragment() {

    private lateinit var binding: EpisodesFragmentBinding

    private val episodesViewModel: EpisodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EpisodesFragmentBinding.inflate(inflater, container, false)
        initializeUI()
        return binding.root
    }

    private fun initializeUI() {
        val adapter = EpisodesAdapter()
        binding.episodeList.adapter = adapter

        //get Episodes
        episodesViewModel.getAllEpisode()

        //observe for episodes data
        episodesViewModel.getEpisodeLiveData().observe(viewLifecycleOwner, Observer {
             adapter.submitList(it)
         })
    }
}