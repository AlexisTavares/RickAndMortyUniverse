package alexis.tvrs.rickandmortyuniverse.ui.locations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import alexis.tvrs.rickandmortyuniverse.NetworkUtil;
import alexis.tvrs.rickandmortyuniverse.R;

public class LocationFragment extends Fragment {
    private static final int NBLOCATIONS = 76;
    private ArrayList<Location> locations = new ArrayList<>();
    private LocationAdapter locationAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_locations, container, false);
        final ListView listView = root.findViewById(R.id.fragment_locations_listView);
        locationAdapter= new LocationAdapter(this.getContext(),locations);
        listView.setAdapter(locationAdapter);

        final SwipeRefreshLayout pullToRefresh = root.findViewById(R.id.fragment_locations_pullToResfresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLocations();
                pullToRefresh.setRefreshing(false);
            }
        });
        loadLocations();
        return root;
    }

    private void loadLocations(){
        if(NetworkUtil.getConnectivityStatus(getActivity())){
            loadOnline();
        }
        else {
            loadOffline();
        }
    }

    private void loadOffline(){
        Toast.makeText(getActivity(), R.string.networkError, Toast.LENGTH_LONG).show();
    }

    private void loadOnline(){
        locations.clear();
        ArrayList<Integer> ids = new ArrayList<>(5);
        Random r = new Random();
        int randomNumber;
        for(int i = 0; i < 5; i++) {
            randomNumber =  r.nextInt((NBLOCATIONS - 1) + 1) + 1;
            if(!ids.contains(randomNumber)) {
                ids.add(randomNumber);
                new AsyncDownloadLocation().execute(randomNumber, locations, locationAdapter);
            }
        }
    }
}
