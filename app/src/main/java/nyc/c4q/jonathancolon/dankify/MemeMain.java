package nyc.c4q.jonathancolon.dankify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by catwong on 1/15/17.
 */

public class MemeMain extends AppCompatActivity {

    private Button drawStudio;
    private View mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_main);

    }

    public void startDrawStudio(View view){
        //TODO fill in next intent -> DrawStudio activity
    }
}
