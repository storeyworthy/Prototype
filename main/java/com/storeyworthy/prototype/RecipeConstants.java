package com.storeyworthy.prototype;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Darth Bane on 6/3/2017.
 */

public interface RecipeConstants extends BaseColumns {

    public static final String RECIPE_TABLE_NAME = "reCIPes";
    public static final String AUTHORITY = "com.storeyworthy.prototype";
    public static final Uri RECIPE_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + RECIPE_TABLE_NAME);
    public static final String RECIPE_NAME = "recipe";

}