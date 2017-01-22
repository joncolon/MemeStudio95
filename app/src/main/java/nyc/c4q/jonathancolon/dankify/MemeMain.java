package nyc.c4q.jonathancolon.dankify;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.stetho.Stetho;

/**
 * Created by catwong on 1/15/17.
 */

public class MemeMain extends AppCompatActivity {
    private SoundFX soundFX = new SoundFX();
    private MyDocumentsDialogFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(getApplicationContext());
        setContentView(R.layout.activity_meme_main);
    }

    public void startDocuments(View view){
        soundFX.playDoubleClick(getApplicationContext());
        showMyDocumentsDialog();
    }

    private void showMyDocumentsDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragment = MyDocumentsDialogFragment.newInstance();
        fragment.show(ft, "fragment_mydocuments");
    }
}
