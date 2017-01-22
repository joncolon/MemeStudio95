package nyc.c4q.jonathancolon.dankify.twittermeme.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import nyc.c4q.jonathancolon.dankify.MemeSaver;
import nyc.c4q.jonathancolon.dankify.PicassoHelper;
import nyc.c4q.jonathancolon.dankify.R;
import nyc.c4q.jonathancolon.dankify.SoundFX;
import nyc.c4q.jonathancolon.dankify.twittermeme.fragments.EditTweetFragment;

public class TweetActivity extends AppCompatActivity implements EditTweetFragment.OnButtonSelection {

    private static final String TAG = "listener";
    private static final int RESULT_LOAD_PROFILE_PIC = 1;
    private static final int RESULT_LOAD_ADD_PHOTO = 2;
    private TextView twitterHandleTV, nameTV, tweetTV, timeStampTV, likeValueTV, retweetValueTV;
    private ImageView editTweetIV, profilePicIV, saveBitmapIV, addPhotoIV, tweetPicIV;
    private LinearLayout tweetLayout;
    private EditTweetFragment fragment;
    private SoundFX soundFX = new SoundFX();
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        context = getApplicationContext();

        initViews();
        setOnClickListeners();
    }

    private void initViews() {
        twitterHandleTV = (TextView) findViewById(R.id.twitter_handle_tv);
        nameTV = (TextView) findViewById(R.id.name_tv);
        tweetTV = (TextView) findViewById(R.id.tweet_body_tv);
        timeStampTV = (TextView) findViewById(R.id.tweet_body_tv);
        likeValueTV = (TextView) findViewById(R.id.likes_value_tv);
        retweetValueTV = (TextView) findViewById(R.id.retweet_value_tv);
        editTweetIV = (ImageView) findViewById(R.id.edit_button_iv);
        profilePicIV = (ImageView) findViewById(R.id.profile_pic_iv);
        saveBitmapIV = (ImageView) findViewById(R.id.save_to_gallery_iv);
        tweetLayout = (LinearLayout) findViewById(R.id.twitter_meme_linlayout);
        tweetPicIV = (ImageView) findViewById(R.id.tweet_pic_iv);
        addPhotoIV = (ImageView) findViewById(R.id.add_photo_iv);
    }

    private void setOnClickListeners() {
        editTweetIV.setOnClickListener(v -> {
            soundFX.playSingleClick(context);
            showEditDialog();
        });
        saveBitmapIV.setOnClickListener(v -> {
            soundFX.playSingleClick(context);
            saveBitmap(viewToBitmap(tweetLayout));
        });
        addPhotoIV.setOnClickListener(v -> {
            soundFX.playSingleClick(context);
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, RESULT_LOAD_ADD_PHOTO);
        });
        profilePicIV.setOnClickListener(v -> {
            soundFX.playSingleClick(context);
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, RESULT_LOAD_PROFILE_PIC);
        });
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        fragment = EditTweetFragment.newInstance("Some Title");
        fragment.show(fm, "fragment_edit_tweet");
    }

    public void saveBitmap(Bitmap bitmap) {
        MemeSaver memeSaver = new MemeSaver();
        String type = "image/*";
        String path = memeSaver.saveToGallery(bitmap);
        memeSaver.saveMemeToDB(path);
        memeSaver.createSharePhotoIntent(type, path);
        Toast.makeText(this, "Saved to gallery", Toast.LENGTH_SHORT).show();
    }

    public Bitmap viewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public void onSaved(String name, String tHandle, String body) {
        Log.e(TAG, "name is: " + name);
        Log.e(TAG, "tHandle is: " + tHandle);
        Log.e(TAG, "body is: " + body);

        setTweetText(name, tHandle, body);
        removeEditDialog();
    }

    @Override
    public void onCancel() {
        removeEditDialog();
    }

    synchronized private void setTweetText(String name, String tHandle, String body) {
        nameTV.setText(name);
        twitterHandleTV.setText(tHandle);
        tweetTV.setText(body);
    }

    synchronized private void removeEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().remove(fragment).commit();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == Activity.RESULT_OK) {

                if (requestCode == RESULT_LOAD_PROFILE_PIC) {
                    PicassoHelper ph = new PicassoHelper(this);
                    Uri uri = data.getData();
                    ph.loadTwitterProfileImg(uri, profilePicIV);
                }
                if (requestCode == RESULT_LOAD_ADD_PHOTO) {
                    PicassoHelper ph = new PicassoHelper(this);
                    Uri uri = data.getData();
                    ph.loadTweetBodyImg(uri, tweetPicIV);
                }
            } else {
                Toast.makeText(this, "No photo selected",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "onActivityResult: " + e.toString());
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
