package com.example.curso03semana03.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.curso03semana03.Constants;
import com.example.curso03semana03.Pets;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private final Context context;

    public DataBase(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Se crea la estrucutura y características de la tabla, no de la base de datos.
        String queryCreateTablePets = "CREATE TABLE " + Constants.TABLE_PETS + "(" +
                Constants.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.TABLE_PETS_NAME + " TEXT, " +
                Constants.TABLE_PETS_DESCRIPTION + " TEXT, " +
                Constants.TABLE_PETS_PHOTO + " INTEGER, " +
                Constants.TABLE_PETS_LIKES + " INTEGER DEFAULT 0, " +
                Constants.TABLE_PETS_FAVORITE + " INTEGER DEFAULT 0 " +
                ")";

/*
        String queryCreateTableLikes = "CREATE TABLE " + Constants.TABLE_LIKES + "(" +
                Constants.TABLE_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.TABLE_LIKES_ID_PETS + " INTEGER, " + //Mi llave foráena
                Constants.TABLE_LIKES_QUANTITY + " INTEGER, " +
                "FOREIGN KEY (" + Constants.TABLE_LIKES_ID_PETS + ")" +
                "REFERENCES " + Constants.TABLE_PETS + "(" + Constants.TABLE_PETS_ID + ")" +
                ")";
*/

        /*String queryCreateTableFavorites = "CREATE TABLE " + Constants.TABLE_FAVORITES + "(" +
                Constants.TABLE_FAVORITES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.TABLE_FAVORITES_ID_PETS + " INTEGER, " +
                Constants.TABLE_FAVORITES_FLAG + " INTEGER DEFAULT 0, " +
                "FOREIGN KEY (" + Constants.TABLE_FAVORITES_ID_PETS + ") " +
                "REFERENCES " + Constants.TABLE_PETS + "(" + Constants.TABLE_PETS_ID + ")" +
                ")";*/

        sqLiteDatabase.execSQL(queryCreateTablePets);
        //sqLiteDatabase.execSQL(queryCreateTableLikes);
        //sqLiteDatabase.execSQL(queryCreateTableFavorites);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PETS);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_LIKES);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_FAVORITES);
        onCreate(sqLiteDatabase);
    }

    // Consultas
    public ArrayList<Pets> getAllPets() {
        ArrayList<Pets> pets = new ArrayList<>();
        String query = "SELECT * FROM " + Constants.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor records = db.rawQuery(query, null);

        while (records.moveToNext()) {
            Pets petActual = new Pets();
            petActual.setId(records.getInt(0));
            petActual.setName(records.getString(1));
            petActual.setDescription(records.getString(2));
            petActual.setPhoto(records.getInt(3));
            petActual.setLikes(records.getInt(4));
            petActual.setFavorite(records.getInt(5));

            /*String queryLikes = "SELECT COUNT (" + Constants.TABLE_LIKES_QUANTITY + ")" +
                    " FROM " + Constants.TABLE_LIKES +
                    " WHERE " + Constants.TABLE_LIKES_ID_PETS + "=" + petActual.getId();

            Cursor recordsLikes = db.rawQuery(queryLikes, null);
            if (recordsLikes.moveToNext()) {
                petActual.setLikes(recordsLikes.getInt(0));
            }
            recordsLikes.close();*/

/*            String queryFavorite = "SELECT " + Constants.TABLE_PETS_FAVORITE +
                    " FROM " + Constants.TABLE_PETS +
                    " WHERE " + Constants.TABLE_PETS_ID + "=" + petActual.getId();

            Cursor recordsFavorite = db.rawQuery(queryFavorite, null);

            if (recordsFavorite.moveToNext()) {
                petActual.setFavorite(recordsFavorite.getColumnIndex(Constants.TABLE_PETS_FAVORITE));
            } else {
                petActual.setFavorite(0);
            }
            recordsFavorite.close();*/
            pets.add(petActual);
        }
        records.close();
        db.close();
        return pets;
    }

    public void insertPet(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Constants.TABLE_PETS, null, contentValues);
        db.close();
    }

    /*public void insertLike(ContentValues contentValues, Context context) {
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();
        db.insert(Constants.TABLE_LIKES, null, contentValues);
        db.close();
    }*/

    public void insertLike(Pets pets, Context context) {
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();
        String query = "UPDATE " + Constants.TABLE_PETS +
                " SET " + Constants.TABLE_PETS_LIKES + " = " + Constants.TABLE_PETS_LIKES + " + 1" +
                " WHERE " + Constants.TABLE_PETS_ID + " = ";
        db.execSQL(query + pets.getId());
        db.close();
    }

    public void updateFavorite(ContentValues contentValues, String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(Constants.TABLE_PETS, contentValues,
                Constants.TABLE_PETS_ID + "=?", new String[]{row_id});
        db.close();
    }

    public int getPetLikes(Pets pets) {
        int likes = 0;

        /*String query = "SELECT COUNT(" + Constants.TABLE_LIKES_QUANTITY + ")" +
                " FROM " + Constants.TABLE_LIKES +
                " WHERE " + Constants.TABLE_LIKES_ID_PETS + "=" + pets.getId();*/

        String query = "SELECT " + Constants.TABLE_PETS_LIKES + " FROM " + Constants.TABLE_PETS +
                " WHERE " + Constants.TABLE_PETS_ID + "=" + pets.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor records = db.rawQuery(query, null);

        if (records.moveToNext()) {
            likes = records.getInt(0);
        }
        records.close();
        db.close();
        return likes;
    }

    public ArrayList<Pets> getAllFavoritePets() {
        ArrayList<Pets> pets = new ArrayList<>();
        //String query = "SELECT * FROM " + Constants.TABLE_PETS;
        String query = "SELECT * FROM " + Constants.TABLE_PETS +
                " WHERE " + Constants.TABLE_PETS_FAVORITE + "=" + Constants.FAVORITE_VALUE +
                " ORDER BY " + Constants.TABLE_PETS_LIKES + " DESC LIMIT 0,5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor records = db.rawQuery(query, null);

        while (records.moveToNext()) {
            Pets petActual = new Pets();
            petActual.setId(records.getInt(0));
            petActual.setName(records.getString(1));
            petActual.setDescription(records.getString(2));
            petActual.setPhoto(records.getInt(3));
            petActual.setLikes(records.getInt(4));
            petActual.setFavorite(records.getInt(5));

            /*String queryFavorite = "SELECT " + Constants.TABLE_PETS_FAVORITE +
                    " FROM " + Constants.TABLE_PETS +
                    " WHERE " + Constants.TABLE_PETS_ID + "=" + petActual.getId();

            Cursor recordsFavorite = db.rawQuery(queryFavorite, null);

            if (recordsFavorite.moveToNext()) {
                petActual.setFavorite(recordsFavorite.getInt(0));
            }*/

            pets.add(petActual);
        }
        records.close();
        db.close();
        return pets;
    }

    public int isThisPetFavorite(Pets pets) {
        int isFavorite = 0;

        String query = "SELECT (" + Constants.TABLE_PETS_FAVORITE + ")" +
                " FROM " + Constants.TABLE_PETS +
                " WHERE " + Constants.TABLE_PETS_ID + "=" + pets.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor records = db.rawQuery(query, null);

        if (records.moveToNext()) {
            isFavorite = records.getInt(records.getColumnIndex(Constants.TABLE_PETS_FAVORITE));
        }
        records.close();
        db.close();
        return isFavorite;
    }

}
