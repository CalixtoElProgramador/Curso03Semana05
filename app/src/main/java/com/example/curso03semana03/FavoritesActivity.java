package com.example.curso03semana03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.curso03semana03.db.DataBase;
import com.example.curso03semana03.presenter.FavoritesActivityPresenter;
import com.example.curso03semana03.presenter.IFavoritesActivityPresenter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity implements IFavoritesActivity{

    private RecyclerView recyclerPetsFavorites;
    private IFavoritesActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerPetsFavorites = findViewById(R.id.recycler_petsFavorites);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar_favorites);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationOnClickListener(view -> onBackPressed());
        presenter = new FavoritesActivityPresenter(this, FavoritesActivity.this);

    }

    @Override
    public void setLayoutManager() {
        recyclerPetsFavorites.setLayoutManager(new LinearLayoutManager(FavoritesActivity.this));
    }

    @Override
    public PetsAdapter createAdapter(ArrayList<Pets> pets) {
        PetsAdapter adapter = new PetsAdapter(pets, FavoritesActivity.this, true);
        return adapter;
    }

    @Override
    public void setAdapter(PetsAdapter adapter) {
        recyclerPetsFavorites.setAdapter(adapter);
    }
}