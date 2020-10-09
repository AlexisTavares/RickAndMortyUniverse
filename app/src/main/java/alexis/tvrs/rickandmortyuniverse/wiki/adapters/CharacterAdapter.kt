package alexis.tvrs.rickandmortyuniverse.wiki.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.utils.ScreenUtils
import alexis.tvrs.rickandmortyuniverse.wiki.models.RickAndMortyCharacter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_character.view.*
import java.util.*

class CharacterAdapter(var context: Context, private var mList: ArrayList<RickAndMortyCharacter>) : BaseAdapter() {
    private val mImageSize = ScreenUtils.getScreenWidth(context)/2
    override fun getCount(): Int {
        return mList.size
    }

    override fun getItem(position: Int): RickAndMortyCharacter {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutItem: LinearLayout
        val mInflater = LayoutInflater.from(context)
        layoutItem = if (convertView == null) {
            mInflater.inflate(R.layout.adapter_character, parent, false) as LinearLayout
        } else {
            convertView as LinearLayout
        }

        Glide.with(context)
                .load(mList[position].image)
                .override(mImageSize, mImageSize)
                .into(layoutItem.character_grid_icon)
        layoutItem.character_grid_name.text = mList[position].name

        return layoutItem
    }
}