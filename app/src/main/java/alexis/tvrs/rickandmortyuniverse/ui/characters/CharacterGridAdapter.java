package alexis.tvrs.rickandmortyuniverse.ui.characters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import alexis.tvrs.rickandmortyuniverse.R;

public class CharacterGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Character> list;

    CharacterGridAdapter(Context context, ArrayList<Character> list) {
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
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.character_grid_layout, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        ImageView icon = (ImageView) layoutItem.findViewById(R.id.character_grid_icon);
        Picasso.get().load(list.get(position).getImageUri()).resize(550, 550).centerCrop().into(icon);

        TextView name = (TextView) layoutItem.findViewById(R.id.character_grid_name);
        name.setText(list.get(position).getName());
        return layoutItem;
    }
}

