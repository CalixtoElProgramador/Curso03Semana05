package com.example.curso03semana03.presenter;

import android.content.Context;

import com.example.curso03semana03.IFavoritesActivity;
import com.example.curso03semana03.Pets;
import com.example.curso03semana03.db.DataBase;
import com.example.curso03semana03.db.InteractPets;

import java.util.ArrayList;

public class FavoritesActivityPresenter implements IFavoritesActivityPresenter {

    private final IFavoritesActivity iFavoritesActivity;
    private final Context context;
    private ArrayList<Pets> pets;

    public FavoritesActivityPresenter(IFavoritesActivity iFavoritesActivity, Context context) {
        this.iFavoritesActivity = iFavoritesActivity;
        this.context = context;
        getPets();
    }

    @Override
    public void getPets() {
        DataBase dataBase = new DataBase(context);
        pets = dataBase.getAllFavoritePets();
        showPets();
    }

    @Override
    public void showPets() {
        iFavoritesActivity.setAdapter(iFavoritesActivity.createAdapter(pets));
        iFavoritesActivity.setLayoutManager();
    }
}
