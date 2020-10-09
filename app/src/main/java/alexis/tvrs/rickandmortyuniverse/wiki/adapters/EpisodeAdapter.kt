package alexis.tvrs.rickandmortyuniverse.wiki.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.models.Episode
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.adapter_episode.view.*
import java.util.*

class EpisodeAdapter internal constructor(private val context: Context, private val list: ArrayList<Episode>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutItem: LinearLayout
        val mInflater = LayoutInflater.from(context)
        layoutItem = if (convertView == null) {
            mInflater.inflate(R.layout.adapter_episode, parent, false) as LinearLayout
        } else {
            convertView as LinearLayout
        }

        val nameText = "Name: " + list[position].name
        layoutItem.episode_layout_name.text = nameText

        val orderText = "Order: " + list[position].episode
        layoutItem.episode_layout_order.text = orderText

        val airDateText = "Air Date: " + list[position].airDate
        layoutItem.episode_layout_air_date.text = airDateText

        return layoutItem
    }
}