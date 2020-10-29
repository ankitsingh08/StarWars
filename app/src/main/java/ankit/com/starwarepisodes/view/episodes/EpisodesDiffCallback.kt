package ankit.com.starwarepisodes.view.episodes

import androidx.recyclerview.widget.DiffUtil
import ankit.com.starwarepisodes.model.Episode

class EpisodesDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.episodeId == newItem.episodeId
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}
