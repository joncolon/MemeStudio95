package nyc.c4q.jonathancolon.dankify;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by catwong on 1/15/17.
 */

public class SplashFragment extends Fragment {

    private static final String TAG = SplashFragment.class.getSimpleName();
    private Button startButton;
    private MediaPlayer player;
    private View mRoot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = MediaPlayer.create(getActivity(), R.raw.windows95_startup);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_main, parent, false);
        clickMemeButton();
        playBootUpTheme();
        return mRoot;
    }

    private void playBootUpTheme() {
        player = MediaPlayer.create(getActivity(), R.raw.windows95_startup);
        player.start();
    }

    private void startMemeStudio() {
        Intent intent = new Intent(getActivity(), MemeMain.class);
        SplashFragment.this.startActivity(intent);
    }

    private void clickMemeButton(){
        startButton = (Button) mRoot.findViewById(R.id.button_start);
        startButton.setOnClickListener(v -> startMemeStudio());
    }



}
