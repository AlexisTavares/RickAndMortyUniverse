package alexis.tvrs.rickandmortyuniverse.ui.locations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import alexis.tvrs.rickandmortyuniverse.R;

public class LocationAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Location> list;

    LocationAdapter(Context context, ArrayList<Location> list) {
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
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.location_layout, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        TextView nameView =layoutItem.findViewById(R.id.locationLayout_name);
        String nameText = "Name: "+list.get(position).getName();
        nameView.setText(nameText);

        TextView typeView =layoutItem.findViewById(R.id.locationLayout_type);
        String typeText = "Type: "+list.get(position).getType();
        typeView.setText(typeText);

        TextView dimensionView =layoutItem.findViewById(R.id.locationLayout_dimension);
        String dimensionText = "Dimension: "+list.get(position).getDimension();
        dimensionView.setText(dimensionText);


        return layoutItem;
    }
}
