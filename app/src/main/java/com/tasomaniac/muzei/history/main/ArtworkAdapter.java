package com.tasomaniac.muzei.history.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tasomaniac.muzei.history.R;
import com.tasomaniac.muzei.history.data.artwork.Artwork;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ViewHolder> {

    private final Picasso picasso;
    private final LayoutInflater mLayoutInflater;
    private final float imageHeight;
    private List<Artwork> artworks = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public ArtworkAdapter(Activity activity, Picasso picasso, float imageHeight) {
        mLayoutInflater = LayoutInflater.from(activity);
        this.picasso = picasso;
        this.imageHeight = imageHeight;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater
                .inflate(R.layout.item_artwork, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Artwork artwork = getItem(position);
//        holder.itemView.setBackgroundColor(getColor(theme.getWindowBackgroundColor()));
        holder.title.setText(artwork.getTitle());
        holder.byline.setText(artwork.getByline());
        //TODO use Palette to detect these.
//        holder.title.setTextColor(getColor(theme.getTextPrimaryColor()));
//        holder.title.setBackgroundColor(getColor(theme.getPrimaryColor()));
        picasso.load(artwork.getImageUri()).resize((int) (imageHeight * 16 / 9), (int) imageHeight).centerCrop().into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getImageUri().hashCode();
    }

    @Override
    public int getItemCount() {
        return artworks.size();
    }

    public Artwork getItem(int position) {
        return artworks.get(position);
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.artwork)
        ImageView icon;
        @Bind(R.id.artwork_title)
        TextView title;
        @Bind(R.id.artwork_byline)
        TextView byline;

        public ViewHolder(View container) {
            super(container);
            ButterKnife.bind(this, container);
        }
    }
}
