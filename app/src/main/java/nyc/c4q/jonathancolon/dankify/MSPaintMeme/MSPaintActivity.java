package nyc.c4q.jonathancolon.dankify.mspaintmeme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import nyc.c4q.jonathancolon.dankify.R;

/**
 * Created by mathcore on 1/16/17.
 */

public class MSPaintActivity extends Activity implements View.OnClickListener {

    private MSPaintView drawView;
    private ImageButton currPaint;
    private ImageButton eraseBtn;

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mspaint_meme);
        drawView = (MSPaintView) findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getDrawable(R.drawable.paint_pressed));
        eraseBtn = (ImageButton)findViewById(R.id.erase_btn);

    }

    public void paintClicked(View view){
        if(view!=currPaint){
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;

        }

    }
}

