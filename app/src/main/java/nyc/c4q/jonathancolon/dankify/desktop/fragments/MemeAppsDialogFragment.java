package nyc.c4q.jonathancolon.dankify.desktop.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nyc.c4q.jonathancolon.dankify.R;
import nyc.c4q.jonathancolon.dankify.SoundFX;
import nyc.c4q.jonathancolon.dankify.mspaintmeme.MSPaintActivity;
import nyc.c4q.jonathancolon.dankify.twitterclone.activities.TweetActivity;

/**
 * Created by catwong on 1/20/17.
 */

public class MemeAppsDialogFragment extends DialogFragment {

    private ImageView iconTwitter;
    private ImageView iconDrawStudio;
    private View mRoot;

    public static MemeAppsDialogFragment newInstance() {
        MemeAppsDialogFragment fragment = new MemeAppsDialogFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_meme_apps, container);
        clickTwitterIcon();
        clickDrawStudio();
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void clickTwitterIcon() {
        iconTwitter = (ImageView) mRoot.findViewById(R.id.icon_twitter);
        iconTwitter.setOnClickListener(v -> {
            SoundFX soundFX = new SoundFX();
            soundFX.playDoubleClick(getActivity().getApplicationContext());
            startTwitter();
        });
    }

    private void startTwitter() {
        Intent intent = new Intent(getActivity(), TweetActivity.class);
        MemeAppsDialogFragment.this.startActivity(intent);
    }

    public void clickDrawStudio(){
        iconDrawStudio = (ImageView) mRoot.findViewById(R.id.icon_draw_studio);
        iconDrawStudio.setOnClickListener(v -> {
            SoundFX soundFX = new SoundFX();
            soundFX.playDoubleClick(getActivity().getApplicationContext());
            startDrawStudio();
        });
    }

    private void startDrawStudio() {
        Intent intent = new Intent(getActivity(), MSPaintActivity.class);
        MemeAppsDialogFragment.this.startActivity(intent);
    }


}
