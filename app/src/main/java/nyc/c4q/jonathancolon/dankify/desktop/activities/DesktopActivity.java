package nyc.c4q.jonathancolon.dankify.desktop.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nyc.c4q.jonathancolon.dankify.R;
import nyc.c4q.jonathancolon.dankify.SoundFX;
import nyc.c4q.jonathancolon.dankify.desktop.fragments.MemeAppsDialogFragment;
import nyc.c4q.jonathancolon.dankify.museum.activities.MemeMuseumActivity;

/**
 * Created by catwong on 1/15/17.
 */

public class DesktopActivity extends AppCompatActivity {
    private SoundFX soundFX = new SoundFX();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
    }

    public void startDocuments(View view){
        soundFX.playDoubleClick(getApplicationContext());
        showMyDocumentsDialog();
    }

    private void showMyDocumentsDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        MemeAppsDialogFragment fragment = MemeAppsDialogFragment.newInstance();
        fragment.show(ft, "fragment_meme_apps");
    }

    public void startMuseum(View view) {
        soundFX.playDoubleClick(getApplicationContext());
        Intent intent = new Intent(this, MemeMuseumActivity.class);
        startActivity(intent);
    }
}
