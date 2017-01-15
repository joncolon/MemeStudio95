package nyc.c4q.jonathancolon.dankify.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.jonathancolon.dankify.Meme;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;


public class MemeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "meme.db";
    private static final int DATABASE_VERSION = 1;
    private static MemeDatabaseHelper instance;

    public static synchronized MemeDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MemeDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public MemeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(Meme.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
