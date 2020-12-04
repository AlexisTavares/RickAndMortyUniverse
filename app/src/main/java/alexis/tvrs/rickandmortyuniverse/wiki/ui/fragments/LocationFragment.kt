package alexis.tvrs.rickandmortyuniverse.wiki.ui.fragments

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.RickAndMortyDatasource
import alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters.LocationAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_locations.*

class LocationFragment : Fragment() {
    private var mAdapter: LocationAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = LocationAdapter()
        location_recyclerview.adapter = mAdapter
        fetchLocations()
    }

    private fun fetchLocations(){
        RickAndMortyDatasource.rickAndMortyLocationsLiveData.observe(viewLifecycleOwner, {
            listLocations ->
            mAdapter?.setData(listLocations)
        })
        RickAndMortyDatasource.fetchRickAndMortyLocations()
    }
}