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

import java.util.ArrayList;
import java.util.Objects;

import alexis.tvrs.rickandmortyuniverse.R;
import alexis.tvrs.rickandmortyuniverse.SharedPreferencesFavorites;

public class FavoriteFragment extends Fragment {
    private static ArrayList<Character> favoriteCharacters;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        final SharedPreferencesFavorites sharedPreferencesFavorites = new SharedPreferencesFavorites();
        favoriteCharacters = new ArrayList<>(sharedPreferencesFavorites.getFavorites(Objects.requireNonNull(getActivity())));

        GridView simpleGrid = root.findViewById(R.id.fragment_favorites_simpleGridView);
        final CharacterGridAdapter characterGridAdapter = new CharacterGridAdapter(this.getContext(), favoriteCharacters);
        simpleGrid.setAdapter(characterGridAdapter);

        characterGridAdapter.notifyDataSetChanged();

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CharacterFullActivity.class);
                intent.putExtra("CHARACTER",favoriteCharacters.get(position));
                startActivity(intent);
            }
        });

        simpleGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                favoriteCharacters.remove(favoriteCharacters.get(position));
                sharedPreferencesFavorites.saveFavorites(getActivity(),favoriteCharacters);
                characterGridAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return root;
    }
}
