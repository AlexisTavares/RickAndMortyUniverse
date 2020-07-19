package alexis.tvrs.rickandmortyuniverse.ui.episodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import alexis.tvrs.rickandmortyuniverse.R;

public class EpisodeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Episode> list;

    EpisodeAdapter(Context context, ArrayList<Episode> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        LayoutInflater mInflater = LayoutInflater.from(context);

        if (convertView == null) {
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.episode_layout, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        TextView nameView =layoutItem.findViewById(R.id.characterLayout_genre);
        String nameText = "Name: "+list.get(position).getName();
        nameView.setText(nameText);

        TextView orderView =layoutItem.findViewById(R.id.characterLayout_status);
        String orderText = "Order: "+list.get(position).getEpisodeOrder();
        orderView.setText(orderText);

        TextView airDateView =layoutItem.findViewById(R.id.characterLayout_species);
        String airDateText = "Air Date: "+list.get(position).getAir_date();
        airDateView.setText(airDateText);


        return layoutItem;
    }
}

