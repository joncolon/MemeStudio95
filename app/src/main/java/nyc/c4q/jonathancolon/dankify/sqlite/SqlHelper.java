package nyc.c4q.jonathancolon.dankify.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.jonathancolon.dankify.Meme;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class SqlHelper {

    public SqlHelper() {
    }

    public static List<Meme> selectAllMemes(SQLiteDatabase db) {
        List<Meme> memes = new ArrayList<>();
        try {
            QueryResultIterable<Meme> itr = cupboard().withDatabase(db).query(Meme.class).query();
            for (Meme meme : itr) {
                memes.add(meme);
            }
            itr.close();
        } catch (Exception e) {
            Log.e("Meme List", "selectAllMemes: ", e);
        }
        return memes;
    }

    public static Meme getRandomContact(SQLiteDatabase db) {
        Random rand = new Random();
        Meme meme = cupboard().withDatabase(db).get(Meme.class, rand.nextInt(20));
        return meme;
    }
}
