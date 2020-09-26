package alexis.tvrs.rickandmortyuniverse.ui.locations

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

class LocationFragment : Fragment() {
    private val locations = ArrayList<Location>()
    private var locationAdapter: LocationAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_locations, container, false)
        val listView = root.findViewById<ListView>(R.id.fragment_locations_listView)
        locationAdapter = LocationAdapter(this.context, locations)
        listView.adapter = locationAdapter
        val pullToRefresh: SwipeRefreshLayout = root.findViewById(R.id.fragment_locations_pullToResfresh)
        pullToRefresh.setOnRefreshListener {
            loadLocations()
            pullToRefresh.isRefreshing = false
        }
        loadLocations()
        return root
    }

    private fun loadLocations() {
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
        locations.clear()
        val ids = ArrayList<Int>(5)
        val r = Random()
        var randomNumber: Int
        for (i in 0..4) {
            randomNumber = r.nextInt(NBLOCATIONS - 1 + 1) + 1
            if (!ids.contains(randomNumber)) {
                ids.add(randomNumber)
                AsyncDownloadLocation().execute(randomNumber, locations, locationAdapter)
            }
        }
    }

    companion object {
        private const val NBLOCATIONS = 76
    }
}