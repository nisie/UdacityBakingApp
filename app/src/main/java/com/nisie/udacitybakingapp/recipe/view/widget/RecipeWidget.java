package com.nisie.udacitybakingapp.recipe.view.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.widget.RemoteViews;

import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.data.RecipeContract;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        int position = 0;
        ContentResolver contentResolver = context.getContentResolver();

        Cursor cursor = contentResolver.query(
                RecipeContract.RecipeEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        String recipeName = "";
        String ingredients = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                context.getString(R.string.widget_shown_recipe), Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt(context.getString(R.string.widget_shown_recipe), 0);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(RecipeContract.RecipeEntry
                    .COLUMN_ID)) == id) {
                recipeName = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry
                        .COLUMN_NAME));
                ingredients = cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry
                        .COLUMN_INGREDIENT));
            }

        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.setTextViewText(R.id.recipe_name, recipeName + "'s " +
                "Ingredients : ");
        views.setTextViewText(R.id.ingredient, ingredients);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

