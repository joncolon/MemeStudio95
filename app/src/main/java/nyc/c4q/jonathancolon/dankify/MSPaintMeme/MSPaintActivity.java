package nyc.c4q.jonathancolon.dankify.mspaintmeme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.UUID;

import nyc.c4q.jonathancolon.dankify.MemeSaver;
import nyc.c4q.jonathancolon.dankify.R;

/**
 * Created by mathcore on 1/16/17.
 */

public class MSPaintActivity extends Activity implements View.OnClickListener {

    private MSPaintView drawView;
    private ImageButton eraseBtn, newBtn, drawBtn, currPaint, saveBtn;
    private float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mspaint_meme);
        initViews();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.draw_btn) {

            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);

            smallBtn.setOnClickListener(v -> {
                drawView.setBrushSize(smallBrush);
                drawView.setLastBrushSize(smallBrush);
                drawView.setErase(false);
                brushDialog.dismiss();
            });

            ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);

            mediumBtn.setOnClickListener(v -> {
                drawView.setBrushSize(mediumBrush);
                drawView.setLastBrushSize(mediumBrush);
                drawView.setErase(false);
                brushDialog.dismiss();
            });

            ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);

            largeBtn.setOnClickListener(v -> {
                drawView.setBrushSize(largeBrush);
                drawView.setLastBrushSize(largeBrush);
                drawView.setErase(false);
                brushDialog.dismiss();
            });

            brushDialog.show();

        } else if (view.getId() == R.id.erase_btn) {

            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);

            smallBtn.setOnClickListener(v -> {
                drawView.setErase(true);
                drawView.setBrushSize(smallBrush);
                brushDialog.dismiss();
            });

            ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(v -> {
                drawView.setErase(true);
                drawView.setBrushSize(mediumBrush);
                brushDialog.dismiss();
            });
            ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(v -> {
                drawView.setErase(true);
                drawView.setBrushSize(largeBrush);
                brushDialog.dismiss();
            });

            brushDialog.show();

        } else if (view.getId() == R.id.new_btn) {

            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);

            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            newDialog.show();

        } else if (view.getId() == R.id.save_btn) {

            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);

            saveDialog.setTitle("Save drawing");
            saveDialog.setMessage("Save drawing to device Gallery?");
            saveDialog.setPositiveButton("Yes", (dialog, which) -> {

                //save drawing
                drawView.setDrawingCacheEnabled(true);
                String path = MediaStore.Images.Media.insertImage(
                        getContentResolver(), drawView.getDrawingCache(),
                        UUID.randomUUID().toString() + ".png", "drawing");
                MemeSaver memeSaver = new MemeSaver();
                memeSaver.saveMemeToDB(getApplicationContext(), path);

                if (path != null) {
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                } else {
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }

                drawView.destroyDrawingCache();
            });

            saveDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            saveDialog.show();
        }


    }

    private void initViews() {
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        drawView = (MSPaintView) findViewById(R.id.drawing);
        drawBtn = (ImageButton) findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);
        currPaint = (ImageButton) paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getDrawable(R.drawable.paint_pressed));
        eraseBtn = (ImageButton) findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);
        newBtn = (ImageButton) findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);
        saveBtn = (ImageButton) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        drawView.setBrushSize(mediumBrush);
    }

    public void paintClicked(View view) {
        drawView.setErase(false);
        drawView.setBrushSize(drawView.getLastBrushSize());

        if (view != currPaint) {
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getDrawable(R.drawable.paint));
            currPaint = (ImageButton) view;

        }
    }
}

