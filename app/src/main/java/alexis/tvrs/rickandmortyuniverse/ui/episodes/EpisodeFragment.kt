package alexis.tvrs.rickandmortyuniverse.ui.episodes

import alexis.tvrs.rickandmortyuniverse.NetworkUtil.getConnectivityStatus
import alexis.tvrs.rickandmortyuniverse.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.util.*

class EpisodeFragment : Fragment() {
    private val episodes = ArrayList<Episode>()
    private var episodeAdapter: EpisodeAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_episodes, container, false)
        val listView = root.findViewById<ListView>(R.id.fragment_episodes_listView)
        episodeAdapter = EpisodeAdapter(this.context, episodes)
        listView.adapter = episodeAdapter
        val pullToRefresh: SwipeRefreshLayout = root.findViewById(R.id.fragment_episodes_pullToResfresh)
        pullToRefresh.setOnRefreshListener {
            loadEpisodes()
            pullToRefresh.isRefreshing = false
        }
        loadEpisodes()
        return root
    }

    private fun loadEpisodes() {
        if (getConnectivityStatus(requireActivity())) {
            loadOnline()
        } else {
            loadOffline()
        }
    }

    private fun loadOffline() {
        Toast.makeText(activity, R.string.networkError, Toast.LENGTH_LONG).show()
    }

    private fun loadOnline() {
        episodes.clear()
        val ids = ArrayList<Int>(5)
        val r = Random()
        var randomNumber: Int
        for (i in 0..4) {
            randomNumber = r.nextInt(NBEPISODES - 1 + 1) + 1
            if (!ids.contains(randomNumber)) {
                ids.add(randomNumber)
                AsyncDownloadEpisode().execute(randomNumber, episodes, episodeAdapter)
            }
        }
    }

    companion object {
        private const val NBEPISODES = 31
    }
}