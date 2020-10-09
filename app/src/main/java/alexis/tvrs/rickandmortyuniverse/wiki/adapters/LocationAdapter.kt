package alexis.tvrs.rickandmortyuniverse.wiki.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.models.Location
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.adapter_location.view.*
import java.util.*

class LocationAdapter internal constructor(private val context: Context, private val list: ArrayList<Location>) : BaseAdapter() {
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
            mInflater.inflate(R.layout.adapter_location, parent, false) as LinearLayout
        } else {
            convertView as LinearLayout
        }
        val nameText = "Name: " + list[position].name
        layoutItem.locationLayout_name.text = nameText

        val typeText = "Type: " + list[position].type
        layoutItem.locationLayout_type.text = typeText

        val dimensionText = "Dimension: " + list[position].dimension
        layoutItem.locationLayout_dimension.text = dimensionText
        return layoutItem
    }
}