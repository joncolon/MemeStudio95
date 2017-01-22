package nyc.c4q.jonathancolon.dankify;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by jonathancolon on 1/21/17.
 */

public class SoundFX {
    private Context context;
    private MediaPlayer player;

    public void playBootUpTheme(Context context) {
        player = MediaPlayer.create(context, R.raw.windows95_startup);
        player.start();
        releasePlayer();
    }

    public void playSingleClick(Context context) {
        player = MediaPlayer.create(context, R.raw.fx_singleclick);
        player.start();
        releasePlayer();
    }

    public void playDoubleClick(Context context) {
        player = MediaPlayer.create(context, R.raw.fx_doubleclick);
        player.start();
        releasePlayer();
    }

    private void releasePlayer() {
        if (!player.isPlaying()){
            player.release();
        }
    }
}
