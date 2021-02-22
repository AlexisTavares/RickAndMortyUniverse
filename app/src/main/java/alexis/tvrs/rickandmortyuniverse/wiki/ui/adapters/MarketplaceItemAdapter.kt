package alexis.tvrs.rickandmortyuniverse.wiki.ui.adapters

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.utils.ScreenUtils
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.MarketplaceOffer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_marketplace_item.view.*

class MarketplaceItemAdapter(private val onClick: (View, MarketplaceOffer) -> Unit): RecyclerView.Adapter<MarketplaceItemAdapter.MarketplaceItemViewHolder>(){
    private var mMarketplaceOffers = emptyList<MarketplaceOffer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketplaceItemViewHolder {
        return MarketplaceItemViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: MarketplaceItemViewHolder, position: Int) {
        holder.bind(mMarketplaceOffers[position], onClick)
    }

    override fun getItemCount(): Int {
        return mMarketplaceOffers.size
    }

    fun setData(listMarketplaceItems: List<MarketplaceOffer>?) {
        if (listMarketplaceItems != null) {
            this.mMarketplaceOffers = listMarketplaceItems
            notifyDataSetChanged()
        }
    }

    class MarketplaceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(marketplaceOffer: MarketplaceOffer, onClick: (View, MarketplaceOffer) -> Unit) {
            Glide.with(itemView)
                    .load(marketplaceOffer.icon)
                    .override(ScreenUtils.getScreenWidth(itemView.context) / 3)
                    .centerCrop()
                    .into(itemView.marketplace_item_icon)
            itemView.marketplace_item_name.text = marketplaceOffer.name
            itemView.marketplace_item_price.text = marketplaceOffer.price.toString()
            itemView.marketplace_item_buy_button.setOnClickListener { onClick(it, marketplaceOffer) }
        }


        companion object {
            fun newInstance(parent: ViewGroup) = MarketplaceItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.view_holder_marketplace_item,
                            parent,
                            false
                    )
            )
        }
    }
}
