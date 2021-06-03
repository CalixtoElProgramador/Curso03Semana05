package com.example.curso03semana03;

import java.util.ArrayList;

public interface IFavoritesActivity {

    void setLayoutManager();

    PetsAdapter createAdapter(ArrayList<Pets> pets);

    void setAdapter(PetsAdapter adapter);

}
