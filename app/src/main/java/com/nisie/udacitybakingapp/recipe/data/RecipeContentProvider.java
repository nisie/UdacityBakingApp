package com.nisie.udacitybakingapp.recipe.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author by nisie on 9/23/17.
 */

public class RecipeContentProvider extends ContentProvider {


    public static final int RECIPES = 101;
    public static final int RECIPES_WITH_ID = 102;

    public static final UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(RecipeContract.AUTHORITY, RecipeContract.PATH_INGREDIENTS, RECIPES);
        uriMatcher.addURI(RecipeContract.AUTHORITY, RecipeContract.PATH_INGREDIENTS + "/#", RECIPES_WITH_ID);

        return uriMatcher;
    }

    private static final int DBVERSION = 1;
    RecipeDBHelper dbHelper;
    private static final String DBNAME = "RecipeDB";
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new RecipeDBHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {
        db = dbHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        Cursor cursor;
        switch (match) {
            case RECIPES:
                cursor = db.query(RecipeContract.RecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Not implemented yet");
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case RECIPES:
                long id = db.insert(
                        RecipeContract.RecipeEntry.TABLE_NAME,
                        null,
                        contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(RecipeContract.RecipeEntry
                            .CONTENT_URI, id);
                } else
                    throw new SQLException("Failed to Insert row into " + uri);
                break;
            default:
                throw new IllegalArgumentException();
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[]
            selectionArgs) {
        db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        int deleteCount;
        switch (match) {
            case RECIPES:
                deleteCount = db.delete(RecipeContract.RecipeEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException();
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
