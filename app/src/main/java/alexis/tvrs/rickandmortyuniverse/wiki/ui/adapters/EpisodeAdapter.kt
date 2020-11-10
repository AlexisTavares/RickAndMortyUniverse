package alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyEpisode
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_episode.view.*

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.EpisodeItemViewHolder>() {
    private var mEpisodeList = emptyList<RickAndMortyEpisode>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeItemViewHolder {
        return EpisodeItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EpisodeItemViewHolder, position: Int) {
        holder.bind(mEpisodeList[position])
    }

    override fun getItemCount(): Int {
        return mEpisodeList.size
    }

    fun setData(listEpisodes: List<RickAndMortyEpisode>?) {
        if(listEpisodes != null) {
            this.mEpisodeList = listEpisodes
            notifyDataSetChanged()
        }
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