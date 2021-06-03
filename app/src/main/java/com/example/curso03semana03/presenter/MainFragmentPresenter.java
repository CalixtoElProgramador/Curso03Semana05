package com.example.curso03semana03.presenter;

import android.content.Context;

import com.example.curso03semana03.IMainFragment;
import com.example.curso03semana03.Pets;
import com.example.curso03semana03.db.InteractPets;

import java.util.ArrayList;

public class MainFragmentPresenter implements IMainFragmentPresenter {

    private final IMainFragment iMainFragment;
    private final Context context;
    private ArrayList<Pets> pets;

    public MainFragmentPresenter(IMainFragment iMainFragment, Context context) {
        this.iMainFragment = iMainFragment;
        this.context = context;
        getPets();
    }

    @Override
    public void getPets() {
        InteractPets interactPets = new InteractPets(context);
        pets = interactPets.getData();
        showPets();
    }

    @Override
    public void showPets() {
        iMainFragment.setAdapter(iMainFragment.createAdapter(pets));
        iMainFragment.setLayoutManager();
    }
}
