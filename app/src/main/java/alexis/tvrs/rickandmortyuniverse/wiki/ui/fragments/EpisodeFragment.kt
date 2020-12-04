package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.RickAndMortyDatasource
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.EpisodeAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodeFragment : Fragment() {
    private var mAdapter: EpisodeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = EpisodeAdapter()
        episode_recyclerview.adapter = mAdapter
        fetchEpisodes()
    }

    private fun fetchEpisodes(){
        RickAndMortyDatasource.rickAndMortyEpisodesLiveData.observe(viewLifecycleOwner, {
            listEpisodes ->
            mAdapter?.setData(listEpisodes)
        })
        RickAndMortyDatasource.fetchRickAndMortyEpisodes()
    }
}