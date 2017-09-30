package com.nisie.udacitybakingapp.recipe.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author by nisie on 9/23/17.
 */

public class RecipeDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bakingrecipe.db";
    private static final int DATABASE_VERSION = 1;

    public RecipeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_FAVORITE_MOVIES_TABLE =
                "CREATE TABLE " + RecipeContract.RecipeEntry.TABLE_NAME + " (" +
                        RecipeContract.RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        RecipeContract.RecipeEntry.COLUMN_ID + " INTEGER NOT NULL, " +
                        RecipeContract.RecipeEntry.COLUMN_NAME + " STRING NOT NULL, " +
                        RecipeContract.RecipeEntry.COLUMN_INGREDIENT + " STRING NOT NULL, " +
                        " UNIQUE (" + RecipeContract.RecipeEntry.COLUMN_ID + ") ON CONFLICT " +
                        "REPLACE); ";

        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
