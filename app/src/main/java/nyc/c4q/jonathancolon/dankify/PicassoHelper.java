package nyc.c4q.jonathancolon.dankify;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoHelper {
    private final Context context;

    public PicassoHelper(Context context) {
        this.context = context;
    }

    public void loadImageFromUri(Uri uri, ImageView imageView){
        Picasso.with(context).load(uri).into(imageView);
    }

    public void loadImageFromString(String uriString, ImageView imageView){
        Picasso.with(context).load(Uri.parse(uriString)).into(imageView);
    }

    public void loadTwitterProfileImg(Uri uri, ImageView imageView){
        Picasso.with(context).load(uri).resize(100, 100).centerCrop().into(imageView);
    }

    public void loadTweetBodyImg(Uri uri, ImageView imageView){
        Picasso.with(context).load(uri).into(imageView);
    }

}
