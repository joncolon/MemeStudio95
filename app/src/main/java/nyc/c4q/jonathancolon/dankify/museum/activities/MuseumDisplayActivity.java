package nyc.c4q.jonathancolon.dankify.museum.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import nyc.c4q.jonathancolon.dankify.Meme;
import nyc.c4q.jonathancolon.dankify.PicassoHelper;
import nyc.c4q.jonathancolon.dankify.R;

public class MuseumDisplayActivity extends AppCompatActivity {
    private static final String MEME_KEY = "museum_meme_key" ;
    private Meme meme;
    private ImageView memeIV;
    Context context;
    private ImageView shareIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_display);
        context = getApplicationContext();
        meme = (Meme) getIntent().getExtras().getSerializable(MEME_KEY);
        memeIV = (ImageView) findViewById(R.id.meme_display_iv);
        shareIV = (ImageView) findViewById(R.id.share_iv);

        loadMeme();
        setClickListener();

    }

    private void setClickListener() {
        shareIV.setOnClickListener(v -> createSharePhotoIntent(meme.getMemeImage()));
    }

    private void loadMeme() {
        PicassoHelper ph = new PicassoHelper(context);
        ph.loadImageForMuseum(meme.getMemeImage(), memeIV);
    }

    private void createSharePhotoIntent (String path) {
        Intent share = new Intent(Intent.ACTION_SEND);
        String type = "image/*";
        share.setType(type);
        Uri uri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share to"));
    }
}
