package nyc.c4q.jonathancolon.dankify.museum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import nyc.c4q.jonathancolon.dankify.Meme;
import nyc.c4q.jonathancolon.dankify.PicassoHelper;
import nyc.c4q.jonathancolon.dankify.R;

/**
 * Created by jonathancolon on 1/21/17.
 */

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.MemeViewHolder> {
    private final Listener listener;
    private List<Meme> memeList;
    private final Context context;

    public MemeAdapter(Listener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MemeAdapter.MemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_meme_rv,
                parent, false);

        MemeViewHolder vh = new MemeViewHolder(itemView);
        Context context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(MemeViewHolder holder, int position) {
        Meme meme = memeList.get(position);
        holder.bind(meme);
    }

    @Override
    public int getItemCount() {
        return memeList.size();
    }

    public void addItem(Meme meme) {
        memeList.add(meme);
        notifyDataSetChanged();
    }

    public interface Listener {
        void onMemeClicked(Meme meme);
        void onMemeLongClicked(Meme meme);
    }

    public void setData(List<Meme> memes) {
        this.memeList = memes;
        notifyDataSetChanged();
    }

    //_____________________________________VIEWHOLDER___________________________________________________
    class MemeViewHolder extends RecyclerView.ViewHolder {
        private final ImageView memeIV;
        MemeViewHolder(View itemView) {
            super(itemView);
            memeIV = (ImageView) itemView.findViewById(R.id.meme_iv);
        }

        void bind(Meme m) {
            final Meme meme = m;
            PicassoHelper ph = new PicassoHelper(context);
            ph.loadImageFromString(meme.getMemeImage(), memeIV);

            itemView.setOnClickListener(v -> listener.onMemeClicked(meme));
            itemView.setOnLongClickListener(v -> {
                listener.onMemeLongClicked(meme);
                return true;
            });
        }
    }
}