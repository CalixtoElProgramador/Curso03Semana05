package com.example.curso03semana03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.curso03semana03.presenter.IMainFragmentPresenter;
import com.example.curso03semana03.presenter.MainFragmentPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainFragment extends Fragment implements IMainFragment{

    private RecyclerView recyclerView;
    private IMainFragmentPresenter presenter;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_petsExplore);
        presenter = new MainFragmentPresenter(this, getActivity());
    }

    @Override
    public void setLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public PetsAdapter createAdapter(ArrayList<Pets> pets) {
        PetsAdapter petsAdapter = new PetsAdapter(pets, getActivity(), false);
        return petsAdapter;
    }

    @Override
    public void setAdapter(PetsAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}