package com.muslimApp.muslimquotesapp.adapters;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.muslimApp.muslimquotesapp.R;
import com.muslimApp.muslimquotesapp.models.Photos;


import java.util.List;
import java.util.Random;


public class WallpapersAdapter extends RecyclerView.Adapter<WallpapersAdapter.WallpaperViewHolder> {

    private Context mCtx;
    private List<Photos> wallpaperList;



    public WallpapersAdapter(Context mCtx, List<Photos> wallpaperList) {
        this.mCtx = mCtx;
        this.wallpaperList = wallpaperList;
    }

    @Override
    public WallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_news_list, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WallpaperViewHolder holder, int position) {
        final Photos w = wallpaperList.get(position);

        holder.textView.setText(w.title);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.linearLayout.setBackgroundColor(color);

    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        ImageView imageView;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout, shareLayout, copyLayout, whatsappLayout;
        int[] androidColors;


        public WallpaperViewHolder(View itemView) {
            super(itemView);

            androidColors = itemView.getResources().getIntArray(R.array.androidcolors);
            relativeLayout = itemView.findViewById(R.id.relative);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            textView = itemView.findViewById(R.id.text_view_title);
            imageView = itemView.findViewById(R.id.image_view);
            shareLayout = itemView.findViewById(R.id.shareLayout);
            copyLayout = itemView.findViewById(R.id.copyLayout);
            whatsappLayout = itemView.findViewById(R.id.whatsappLayout);

            shareLayout.setOnClickListener(this);
            copyLayout.setOnClickListener(this);
            whatsappLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.copyLayout:

                    copyQuote(wallpaperList.get(getAdapterPosition()));

                    break;
                case R.id.shareLayout:

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT,textView.getText());
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Quotes App");
                    mCtx.startActivity(Intent.createChooser(shareIntent, "Share Quote"));
                    Toast.makeText(mCtx, "share Quote text", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.whatsappLayout:

                    Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    whatsappIntent.setType("text/plain");
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT,textView.getText());
                    whatsappIntent.putExtra(Intent.EXTRA_SUBJECT, "Quotes App");
                    mCtx.startActivity(Intent.createChooser(whatsappIntent, "Share Quote"));
                    Toast.makeText(mCtx, "share Quote text", Toast.LENGTH_SHORT).show();

                    break;

            }

        }

        private void copyQuote(Photos wallpaper) {
            ClipboardManager clipboard = (ClipboardManager) mCtx.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label",wallpaper.title);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(mCtx, "Quotes Copied", Toast.LENGTH_SHORT).show();
        }
    }
}
