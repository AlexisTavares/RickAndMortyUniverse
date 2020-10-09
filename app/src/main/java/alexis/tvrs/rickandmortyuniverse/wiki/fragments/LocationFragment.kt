package alexis.tvrs.rickandmortyuniverse.wiki.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.activities.SplashScreenActivity
import alexis.tvrs.rickandmortyuniverse.wiki.adapters.LocationAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class LocationFragment : Fragment() {
    private var locationAdapter: LocationAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_locations, container, false)
        val listView = root.findViewById<ListView>(R.id.fragment_locations_listView)
        locationAdapter = LocationAdapter(root.context, SplashScreenActivity.LOCATIONS)
        listView.adapter = locationAdapter
        return root
    }
}