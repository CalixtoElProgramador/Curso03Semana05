package com.example.curso03semana03;

public final class Constants {


    //TODO NO CAMBIEN EL PASSWORD POR FAVOR JAJA
    private static final String KEY_EMAIL               = "calixto.xmakfjq@gmail.com";
    private static final String KEY_PASSWORD            = "@rMRJLk?d4PM";
    private static final String KEY_TO_EMAIL            = "calixto.programador@gmail.com";

    //DATABASE
    public static final String DATABASE_NAME            = "pets";
    public static final int DATABASE_VERSION            = 2;

    //TABLE PETS
    public static final String TABLE_PETS               = "pets";
    public static final String TABLE_PETS_ID            = "ids";
    public static final String TABLE_PETS_NAME          = "name";
    public static final String TABLE_PETS_DESCRIPTION   = "description";
    public static final String TABLE_PETS_PHOTO         = "photo";
    public static final String TABLE_PETS_LIKES         = "likes";
    public static final String TABLE_PETS_FAVORITE      = "favorite";

    /*//TABLE PETS_LIKES
    public static final String TABLE_LIKES              = "pets_likes";
    public static final String TABLE_LIKES_ID           = "id";
    public static final String TABLE_LIKES_ID_PETS      = "id_pets";
    public static final String TABLE_LIKES_QUANTITY     = "quantity";
    public static final int LIKES = 1;

    //TABLE PETS_FAVORITES
    public static final String TABLE_FAVORITES          = "pets_favorites";
    public static final String TABLE_FAVORITES_ID       = "id";
    public static final String TABLE_FAVORITES_ID_PETS  = "id_pets";
    public static final String TABLE_FAVORITES_FLAG     = "flag";*/

    public static final int FAVORITE_VALUE              = 1;
    public static final int NOT_FAVORITE_VALUE          = 0;

    public static String getKeyEmail() {
        return KEY_EMAIL;
    }

    public static String getKeyPassword() {
        return KEY_PASSWORD;
    }

    public static String getKeyToEmail() {
        return KEY_TO_EMAIL;
    }
}
