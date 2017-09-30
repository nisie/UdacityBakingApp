package com.nisie.udacitybakingapp.recipe.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author by nisie on 9/23/17.
 */

public class RecipeContract {
    public static final String AUTHORITY = "com.nisie.udacitybakingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_INGREDIENTS = "ingredient";

    public static final class RecipeEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_INGREDIENTS)
                .build();

        public static final String TABLE_NAME = "ingredient";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INGREDIENT = "ingredients";


        public static Uri buildIngredientsUriWithId(long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }
    }
}
