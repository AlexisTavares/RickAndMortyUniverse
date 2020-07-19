package alexis.tvrs.rickandmortyuniverse.ui.characters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import alexis.tvrs.rickandmortyuniverse.NetworkUtil;
import alexis.tvrs.rickandmortyuniverse.R;
import alexis.tvrs.rickandmortyuniverse.SharedPreferencesCharacters;
import alexis.tvrs.rickandmortyuniverse.SharedPreferencesFavorites;

public class CharacterFragment extends Fragment {
    private static final int NBCHARACTERS = 493;
    private ArrayList<Character> characters = new ArrayList<>();
    private CharacterGridAdapter characterGridAdapter;
    private SharedPreferencesFavorites sharedPreferencesFavorites;
    private SharedPreferencesCharacters sharedPreferencesCharacters;
    private static CharacterFragment instance;

    public static CharacterFragment getInstance(){
        if(instance == null)
            instance = new CharacterFragment();

        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_characters, container, false);

        sharedPreferencesFavorites = new SharedPreferencesFavorites();
        sharedPreferencesCharacters = new SharedPreferencesCharacters();

        GridView simpleGrid = root.findViewById(R.id.fragment_characters_simpleGridView);
        characterGridAdapter = new CharacterGridAdapter(this.getContext(),characters);
        simpleGrid.setAdapter(characterGridAdapter);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CharacterFullActivity.class);
                intent.putExtra("CHARACTER",characters.get(position));
                startActivity(intent);
            }
        });

        simpleGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sharedPreferencesFavorites.addFavorite(getActivity(),characters.get(position));
                //TODO Check if characters is already in favorites
                Toast.makeText(getActivity(), "Added to favorites", Toast.LENGTH_LONG).show();
                return true;
            }
        });


        final SwipeRefreshLayout pullToRefresh = root.findViewById(R.id.fragment_characters_pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCharacters();
                pullToRefresh.setRefreshing(false);
            }
        });

        loadCharacters();
        return root;
    }

    private void loadCharacters(){
        if(NetworkUtil.getConnectivityStatus(Objects.requireNonNull(getActivity()))){
            loadOnline();
        }
        else {
            loadOffline();
        }
    }

    private void loadOffline(){
        Toast.makeText(getActivity(), R.string.networkError, Toast.LENGTH_SHORT).show();
        characters = (sharedPreferencesCharacters.getcharacters(Objects.requireNonNull(getActivity())));
        characterGridAdapter.notifyDataSetChanged();
    }

    private void loadOnline(){
        characters.clear();
        ArrayList<Integer> ids = new ArrayList<>(5);
        Random r = new Random();
        int randomNumber;
        for(int i = 0; i < 8; i++) {
            randomNumber =  r.nextInt(NBCHARACTERS) + 1;
            if(!ids.contains(randomNumber)) {
                ids.add(randomNumber);
                new AsyncDownloadCharacter().execute(randomNumber, characters, characterGridAdapter);
            }
        }
        sharedPreferencesCharacters.savecharacters(Objects.requireNonNull(getActivity()),characters);
    }
}
