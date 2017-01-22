package nyc.c4q.jonathancolon.dankify.twittermeme.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import nyc.c4q.jonathancolon.dankify.R;
import nyc.c4q.jonathancolon.dankify.SoundFX;

/**
 * Created by jonathancolon on 1/16/17.
 */

public class EditTweetFragment extends DialogFragment {

    private EditText editTwitterHandle, editTweet, editName;
    private String name;
    private String twitterHandle;
    private String tweetBody;
    private OnButtonSelection listener;
    private ImageView saveButtonIV, cancelButtonIV;

    public EditTweetFragment() {
    }

    public static EditTweetFragment newInstance(String title) {
        EditTweetFragment fragment = new EditTweetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonSelection) {
            listener = (OnButtonSelection) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_tweet, container);


        initVIews(view);

        cancelButtonIV.setOnClickListener(v -> {
            SoundFX soundFX = new SoundFX();
            soundFX.playSingleClick(getActivity().getApplicationContext());
            listener.onCancel();
        });
        saveButtonIV.setOnClickListener(v -> {
            SoundFX soundFX = new SoundFX();
            soundFX.playSingleClick(getActivity().getApplicationContext());
            name = editName.getText().toString();
            twitterHandle = editTwitterHandle.getText().toString();
            tweetBody = editTweet.getText().toString();
            listener.onSaved(name, twitterHandle, tweetBody);
        });

        return view;
    }

    private void initVIews(View view) {
        editName = (EditText)view.findViewById(R.id.name_et);
        editTwitterHandle = (EditText)view.findViewById(R.id.twitter_handle_et);
        editTweet = (EditText)view.findViewById(R.id.name_et);
        saveButtonIV = (ImageView)view.findViewById(R.id.save_button_iv);
        cancelButtonIV = (ImageView)view.findViewById(R.id.cancel_button_iv);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        editName = (EditText) view.findViewById(R.id.name_et);
        editTwitterHandle = (EditText) view.findViewById(R.id.twitter_handle_et);
        editTweet = (EditText) view.findViewById(R.id.tweet_body_et);
        editName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    // Define the events that the fragment will use to communicate
    public interface OnButtonSelection {
        // This can be any number of events to be sent to the activity
        void onSaved(String name, String tHandle, String body);
        void onCancel();
    }
}
