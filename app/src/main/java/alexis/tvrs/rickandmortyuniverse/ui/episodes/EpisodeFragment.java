package alexis.tvrs.rickandmortyuniverse.ui.episodes;

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
import java.util.Random;

import alexis.tvrs.rickandmortyuniverse.NetworkUtil;
import alexis.tvrs.rickandmortyuniverse.R;

public class EpisodeFragment extends Fragment {
    private static final int NBEPISODES = 31;
    private ArrayList<Episode> episodes = new ArrayList<>();
    private EpisodeAdapter episodeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_episodes, container, false);
        final ListView listView = root.findViewById(R.id.fragment_episodes_listView);
        episodeAdapter= new EpisodeAdapter(this.getContext(),episodes);
        listView.setAdapter(episodeAdapter);

        final SwipeRefreshLayout pullToRefresh = root.findViewById(R.id.fragment_episodes_pullToResfresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadEpisodes();
                pullToRefresh.setRefreshing(false);
            }
        });
        loadEpisodes();
        return root;
    }

    private void loadEpisodes(){
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
        episodes.clear();
        ArrayList<Integer> ids = new ArrayList<>(5);
        Random r = new Random();
        int randomNumber;
        for(int i = 0; i < 5; i++) {
            randomNumber =  r.nextInt((NBEPISODES - 1) + 1) + 1;
            if(!ids.contains(randomNumber)) {
                ids.add(randomNumber);
                new AsyncDownloadEpisode().execute(randomNumber, episodes, episodeAdapter);
            }
        }
    }
}
