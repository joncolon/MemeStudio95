package nyc.c4q.jonathancolon.dankify.museum;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import nyc.c4q.jonathancolon.dankify.Meme;
import nyc.c4q.jonathancolon.dankify.R;
import nyc.c4q.jonathancolon.dankify.sqlite.MemeDatabaseHelper;
import nyc.c4q.jonathancolon.dankify.sqlite.SqlHelper;

public class MemeMuseum extends AppCompatActivity implements MemeAdapter.Listener {

    private RecyclerView recyclerView;
    private List<Meme> memeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_museum);

        setupRecyclerView();
        refreshRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.meme_museum_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MemeAdapter(this, this));
    }

    private void refreshRecyclerView() {
        MemeDatabaseHelper dbHelper = MemeDatabaseHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        memeList = SqlHelper.selectAllMemes(db);
        MemeAdapter adapter = (MemeAdapter) recyclerView.getAdapter();
        adapter.setData(memeList);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshRecyclerView();
    }

    @Override
    public void onMemeClicked(Meme meme) {

    }

    @Override
    public void onMemeLongClicked(Meme meme) {

    }
}
