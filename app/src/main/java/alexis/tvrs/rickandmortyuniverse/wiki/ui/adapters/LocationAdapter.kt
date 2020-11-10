package alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyLocation
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_location.view.*

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationItemViewHolder>() {
    private var mLocationList = emptyList<RickAndMortyLocation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationItemViewHolder {
        return LocationItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: LocationItemViewHolder, position: Int) {
        holder.bind(mLocationList[position])
    }

    override fun getItemCount(): Int {
        return mLocationList.size
    }

    fun setData(listLocation: List<RickAndMortyLocation>?) {
        if(listLocation != null) {
            this.mLocationList = listLocation
            notifyDataSetChanged()
        }
    }

    class LocationItemViewHolder(
            itemView: View
    ): RecyclerView.ViewHolder(itemView){
        fun bind(location: RickAndMortyLocation) {
            itemView.location_layout_name.text = location.name
            itemView.location_layout_dimension.text = location.dimension
            itemView.location_layout_type.text = location.type
        }

        companion object{
            fun newInstance(parent: ViewGroup) = LocationItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.view_holder_location,
                            parent,
                            false
                    )
            )
        }
    }
}