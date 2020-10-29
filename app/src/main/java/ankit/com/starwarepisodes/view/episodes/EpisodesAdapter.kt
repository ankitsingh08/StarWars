package ankit.com.starwarepisodes.view.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ankit.com.starwarepisodes.databinding.ListItemEpisodeBinding
import ankit.com.starwarepisodes.model.Episode

/**
 * Created by AnkitSingh on 10/28/20.
 */

class EpisodesAdapter: ListAdapter<Episode, RecyclerView.ViewHolder>(EpisodesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EpisodeViewHolder(ListItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episode = getItem(position)
        (holder as EpisodeViewHolder).bind(episode)
    }

    class EpisodeViewHolder(private val binding: ListItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Episode) {
            binding.apply {
                episode = item
                executePendingBindings()
            }
        }
    }

}