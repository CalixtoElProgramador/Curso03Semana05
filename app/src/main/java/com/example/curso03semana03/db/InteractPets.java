package com.example.curso03semana03.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.curso03semana03.Constants;
import com.example.curso03semana03.Pets;
import com.example.curso03semana03.R;

import java.util.ArrayList;

public class InteractPets {

    private Context context;

    public InteractPets(Context context) {
    this.context = context;
    }

    public ArrayList<Pets> getData(){

        DataBase dataBase = new DataBase(context);
        if (dataBase.getAllPets().size()==0){
            initializePets(dataBase);
        }
        return dataBase.getAllPets();

    }

    public void initializePets(DataBase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PETS_NAME, "Perro01");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog01);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro02");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog02);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro03");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog03);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro04");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog04);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro05");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog05);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro06");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog06);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro07");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog07);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro08");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog08);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro09");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog09);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

        contentValues.put(Constants.TABLE_PETS_NAME, "Perro10");
        contentValues.put(Constants.TABLE_PETS_DESCRIPTION, context.getString(R.string.cardview_description));
        contentValues.put(Constants.TABLE_PETS_PHOTO, R.drawable.dog10);
        contentValues.put(Constants.TABLE_PETS_LIKES, 0);
        contentValues.put(Constants.TABLE_PETS_FAVORITE, 0);
        db.insertPet(contentValues);

    }

    /*public void setPetLikes(Pets pets, Context context) {
        DataBase dataBase = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_LIKES_ID_PETS, pets.getId());
        contentValues.put(Constants.TABLE_LIKES_QUANTITY, Constants.LIKES);
        dataBase.insertLike(contentValues, context);
    }*/

    public void setPetLikes(Pets pets, Context context) {
        DataBase dataBase = new DataBase(context);
        dataBase.insertLike(pets, context);
    }

    public int getPetLikes(Pets pets, Context context) {
        DataBase dataBase = new DataBase(context);
        return dataBase.getPetLikes(pets);
    }

    public void setPetFavorite(Pets pets, int flag) {
        DataBase dataBase = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_PETS_ID, pets.getId());
        contentValues.put(Constants.TABLE_PETS_FAVORITE, flag);
        dataBase.updateFavorite(contentValues, String.valueOf(pets.getId()));
    }

    public int getPetsFavorites(Pets pets) {
        DataBase dataBase = new DataBase(context);
        return dataBase.isThisPetFavorite(pets);
    }


}
