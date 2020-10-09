package alexis.tvrs.rickandmortyuniverse.wiki.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.adapters.EpisodeAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class EpisodeFragment : Fragment() {
    private var episodeAdapter: EpisodeAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_episodes, container, false)
        val listView = root.findViewById<ListView>(R.id.fragment_episodes_listView)
        episodeAdapter = EpisodeAdapter(root.context, SplashScreenActivity.EPISODES)
        listView.adapter = episodeAdapter
        return root
    }
}