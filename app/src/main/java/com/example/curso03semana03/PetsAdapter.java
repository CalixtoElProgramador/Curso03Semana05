package com.example.curso03semana03;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.curso03semana03.db.InteractPets;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.PetsViewHolder> {

    private ArrayList<Pets> pets;
    private Context context;
    private InteractPets interactPets;
    private boolean isFavoriteActivity;

    public PetsAdapter(ArrayList<Pets> pets, Context context, boolean isFavoriteActivity) {
        this.pets = pets;
        this.context = context;
        this.isFavoriteActivity = isFavoriteActivity;
    }

    public PetsAdapter() {

    }

    @Override
    public PetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_pets, parent, false);
        return new PetsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PetsAdapter.PetsViewHolder holder, int position) {
        interactPets = new InteractPets(context);
        Pets pet = pets.get(position);
        holder.title.setText(pet.getName());
        holder.likes.setText(pet.getLikes() + " huesitos de oro");
        holder.description.setText(pet.getDescription());
        holder.photo.setImageResource(pet.getPhoto());

        if (isFavoriteActivity) {
            holder.buttonGiftBone.setVisibility(View.GONE);
            holder.buttonAddFavorites.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }
        holder.buttonAddFavorites.setOnClickListener(view -> {
            setViewButtonFavorite(holder, pet);
        });
        holder.buttonGiftBone.setOnClickListener(view -> {
            interactPets.setPetLikes(pet, context);
            holder.likes.setText(interactPets.getPetLikes(pet, context) + " huesitos de oro");

        });
    }

    public void setViewButtonFavorite(PetsViewHolder holder, Pets pet) {
        if (interactPets.getPetsFavorites(pet) == Constants.NOT_FAVORITE_VALUE) {
            interactPets.setPetFavorite(pet, Constants.FAVORITE_VALUE);
            holder.buttonAddFavorites.setText(R.string.cardview_remove_favorites);
            Toast.makeText(context, "Añadido", Toast.LENGTH_SHORT).show();
            holder.buttonAddFavorites.setIconResource(R.drawable.ic_favorite);


        } else {
            interactPets.setPetFavorite(pet, Constants.NOT_FAVORITE_VALUE);
            Toast.makeText(context, "Removido", Toast.LENGTH_SHORT).show();
            holder.buttonAddFavorites.setText(R.string.cardview_add_favorites);
            holder.buttonAddFavorites.setIconResource(R.drawable.ic_favorite_border);

        }
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetsViewHolder extends RecyclerView.ViewHolder {

        private TextView title, likes, description;
        private ImageView photo;
        private MaterialButton buttonAddFavorites, buttonGiftBone;
        private View line;

        public PetsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            likes = itemView.findViewById(R.id.text_rate);
            description = itemView.findViewById(R.id.text_description);
            photo = itemView.findViewById(R.id.image_photo);
            buttonGiftBone = itemView.findViewById(R.id.button_giftBone);
            buttonAddFavorites = itemView.findViewById(R.id.button_addFavorite);
            line = itemView.findViewById(R.id.view_lineCard);
        }
    }

}
