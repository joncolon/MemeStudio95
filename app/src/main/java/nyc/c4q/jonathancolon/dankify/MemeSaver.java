package nyc.c4q.jonathancolon.dankify;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import nyc.c4q.jonathancolon.dankify.sqlite.MemeDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by jonathancolon on 1/21/17.
 */

public class MemeSaver {
    private Context context;

    public void saveMemeToDB(Context context, String path) {
        Meme meme = new Meme();
        meme.setMemeImage(path);
        MemeDatabaseHelper dbHelper = MemeDatabaseHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cupboard().withDatabase(db).put(meme);
    }

    public String saveToGallery(Context context, Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "image1", "an image");
        scanMedia(context, path);
        return path;
    }

    private void scanMedia(Context context, String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Intent scanFileIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);

        context.sendBroadcast(scanFileIntent);
    }
}
