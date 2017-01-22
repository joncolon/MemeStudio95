package nyc.c4q.jonathancolon.dankify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MuseumDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_display);

        ImageView memeIV = (ImageView) findViewById(R.id.meme_display_iv);
    }
}
