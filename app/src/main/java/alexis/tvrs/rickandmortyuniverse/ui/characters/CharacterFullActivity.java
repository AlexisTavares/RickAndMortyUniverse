package alexis.tvrs.rickandmortyuniverse.ui.characters;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import alexis.tvrs.rickandmortyuniverse.R;

public class CharacterFullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_full_layout);

        final Character character = (Character) getIntent().getSerializableExtra("CHARACTER");

        ImageView imageView = findViewById(R.id.characterLayout_image);
        Picasso.get().load(character.getImageUri()).into(imageView);

        TextView nameView = findViewById(R.id.characterLayout_name);
        String nameText = "Name: "+character.getName();
        nameView.setText(nameText);

        TextView statusView = findViewById(R.id.characterLayout_status);
        String statusText = "Status: "+character.getStatus();
        statusView.setText(statusText);

        TextView genderView = findViewById(R.id.characterLayout_genre);
        String genderText = "Gender: "+character.getGender();
        genderView.setText(genderText);

        TextView speciesView = findViewById(R.id.characterLayout_species);
        String speciesText = "Species: "+character.getSpecies();
        speciesView.setText(speciesText);

        final TextView originView = findViewById(R.id.characterLayout_origin);
        String originText = "Origin: "+character.getOrigin();
        originView.setText(originText);

        originView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CharacterFullActivity.this, character.getOriginUri(), Toast.LENGTH_LONG).show();
            }
        });

        TextView locationView = findViewById(R.id.characterLayout_lastLocation);
        String lastLocationText = "Last Location: "+character.getLastLocation();
        locationView.setText(lastLocationText);

    }
}
