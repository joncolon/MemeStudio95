package nyc.c4q.jonathancolon.dankify;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import nyc.c4q.jonathancolon.dankify.twittermeme.activities.TweetActivity;

/**
 * Created by catwong on 1/15/17.
 */

public class MemeMain extends AppCompatActivity {

    private MyDocumentsDialogFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_main);
    }

    public void startDocuments(View view){
        showMyDocumentsDialog();
    }

    private void showMyDocumentsDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragment = MyDocumentsDialogFragment.newInstance("My Documents");
        fragment.show(ft, "fragment_mydocuments");
    }
}
