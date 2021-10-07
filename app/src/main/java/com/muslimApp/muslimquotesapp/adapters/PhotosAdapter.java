package com.muslimApp.muslimquotesapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.muslimApp.muslimquotesapp.R;
import com.muslimApp.muslimquotesapp.models.Photos;

import java.util.List;


public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.WallpaperViewHolder> {

    private Context mCtx;
    private List<Photos> wallpaperList;



    public PhotosAdapter(Context mCtx, List<Photos> wallpaperList) {
        this.mCtx = mCtx;
        this.wallpaperList = wallpaperList;
    }

    @Override
    public WallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_photos, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WallpaperViewHolder holder, int position) {
        final Photos w = wallpaperList.get(position);

        holder.textView.setText(w.getTitle());

        Glide.with(mCtx)
                .load(w.getUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        int[] androidColors;


        public WallpaperViewHolder(View itemView) {
            super(itemView);

            androidColors = itemView.getResources().getIntArray(R.array.androidcolors);
            relativeLayout = itemView.findViewById(R.id.relative);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            textView = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.iv_photos);

        }
    }
}
