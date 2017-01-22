package nyc.c4q.jonathancolon.dankify;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nyc.c4q.jonathancolon.dankify.mspaintmeme.MSPaintActivity;
import nyc.c4q.jonathancolon.dankify.twittermeme.activities.TweetActivity;

/**
 * Created by catwong on 1/20/17.
 */

public class MyDocumentsDialogFragment extends DialogFragment {

    private ImageView iconTwitter;
    private ImageView iconDrawStudio;
    private View mRoot;

    public static MyDocumentsDialogFragment newInstance() {
        MyDocumentsDialogFragment fragment = new MyDocumentsDialogFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_mydocuments, container);
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
            soundFX.playSingleClick(getActivity().getApplicationContext());
            startTwitter();
        });
    }

    private void startTwitter() {
        Intent intent = new Intent(getActivity(), TweetActivity.class);
        MyDocumentsDialogFragment.this.startActivity(intent);
    }

    public void clickDrawStudio(){
        iconDrawStudio = (ImageView) mRoot.findViewById(R.id.icon_draw_studio);
        iconDrawStudio.setOnClickListener(v -> {
            SoundFX soundFX = new SoundFX();
            soundFX.playSingleClick(getActivity().getApplicationContext());
            startDrawStudio();
        });
    }

    private void startDrawStudio() {
        Intent intent = new Intent(getActivity(), MSPaintActivity.class);
        MyDocumentsDialogFragment.this.startActivity(intent);
    }


}
