package com.muslimApp.muslimquotesapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muslimApp.muslimquotesapp.adapters.WallpapersAdapter;
import com.muslimApp.muslimquotesapp.models.Category;
import com.muslimApp.muslimquotesapp.models.Photos;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Photos> wallpaperList;
    List<Category> categoryList;
    List<Photos> favList;
    RecyclerView recyclerView;
    WallpapersAdapter adapter;
    Context context;
    DatabaseReference dbWallpapers, dbFavs;
    ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favList = new ArrayList<>();
        wallpaperList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WallpapersAdapter(getActivity(), wallpaperList);

        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressbar);

        dbWallpapers = FirebaseDatabase.getInstance().getReference("quotes");


            fetchWallpapers();

    }

    private void fetchWallpapers() {
        progressBar.setVisibility(View.VISIBLE);
        dbWallpapers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                if (dataSnapshot.exists()) {
                    for (DataSnapshot wallpaperSnapshot : dataSnapshot.getChildren()) {

                        Photos quote = wallpaperSnapshot.getValue(Photos.class);


                        favList.add(quote);
                    }
                    for (int i = 0; i < favList.size(); i++) {
                        if (favList.get(i).getDevelopers().equals("G-devs")) {
                            wallpaperList.add(favList.get(i));
                        }
                    }
                    Collections.reverse(wallpaperList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}