package nyc.c4q.jonathancolon.dankify;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.jonathancolon.dankify.twittermeme.activities.TweetActivity;

/**
 * Created by catwong on 1/20/17.
 */

public class MyDocumentsDialogFragment extends DialogFragment {

    private ImageView iconTwitter;
    private View mRoot;

    public static MyDocumentsDialogFragment newInstance(String title) {
        MyDocumentsDialogFragment fragment = new MyDocumentsDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_mydocuments, container);
        clickTwitterIcon();
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void clickTwitterIcon() {
        iconTwitter = (ImageView) mRoot.findViewById(R.id.icon_twitter);
        iconTwitter.setOnClickListener(v -> startTwitter());

    }

    private void startTwitter() {
        Intent intent = new Intent(getActivity(), TweetActivity.class);
        MyDocumentsDialogFragment.this.startActivity(intent);
    }


}
