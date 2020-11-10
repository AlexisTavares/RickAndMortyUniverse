package alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.utils.ScreenUtils
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.repositories.RickAndMortyRepository
import alexis.tvrs.rickandmortyuniverse.wiki.ui.activities.SplashScreenActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_character.view.*

class CharacterAdapter(private val onClick: (View, RickAndMortyCharacter) -> Unit, private val onLongClick: (View, RickAndMortyCharacter) -> Boolean): RecyclerView.Adapter<CharacterAdapter.CharacterItemViewHolder>(){
    private var mCharacterList = emptyList<RickAndMortyCharacter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bind(mCharacterList[position]
                ,onClick
                ,onLongClick)
    }

    override fun getItemCount(): Int {
        return mCharacterList.size
    }

    fun setData(listCharacters: List<RickAndMortyCharacter>?) {
        if (listCharacters != null) {
            this.mCharacterList = listCharacters
            notifyDataSetChanged()
        }
    }

    class CharacterItemViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        fun bind(character: RickAndMortyCharacter
                 ,onClick: (View, RickAndMortyCharacter) -> Unit
                 ,onLongClick: (View, RickAndMortyCharacter) -> Boolean) {
            Glide.with(itemView)
                    .load(character.image)
                    .override( ScreenUtils.getScreenWidth(itemView.context)/2)
                    .centerCrop()
                    .into(itemView.character_grid_icon)
            itemView.character_grid_name.text = character.name

            itemView.setOnClickListener{onClick(it, character)}
            itemView.setOnLongClickListener{onLongClick(it, character)}
        }

        companion object{
            fun newInstance(parent: ViewGroup) = CharacterItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_holder_character,
                    parent,
                    false
                )
            )
        }
    }
}