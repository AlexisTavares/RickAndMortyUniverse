package alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyEpisode
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_episode.view.*

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeItemViewHolder {
        return EpisodeItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EpisodeItemViewHolder, position: Int) {
        holder.bind(SplashScreenActivity.EPISODES[position])
    }

    override fun getItemCount(): Int {
        return SplashScreenActivity.EPISODES.size
    }

    class EpisodeItemViewHolder(
            itemView: View
    ): RecyclerView.ViewHolder(itemView){
        fun bind(episode: RickAndMortyEpisode) {
            itemView.episode_layout_name.text = episode.name
            itemView.episode_layout_order.text = episode.episode
            itemView.episode_layout_air_date.text = episode.airDate
        }

        companion object{
            fun newInstance(parent: ViewGroup) = EpisodeItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.view_holder_episode,
                            parent,
                            false
                    )
            )
        }
    }
}