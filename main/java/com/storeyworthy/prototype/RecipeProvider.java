package com.storeyworthy.prototype;

/**
 * Created by Darth Bane on 6/3/2017.
 */

import static android.provider.BaseColumns._ID;
import static com.storeyworthy.prototype.RecipeConstants.AUTHORITY;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_CONTENT_URI;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_TABLE_NAME;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class RecipeProvider extends ContentProvider {
    private static final int RECIPES = 1;
    private static final int RECIPES_ID = 2;

    /** The MIME type of a directory of events */
    private static final String CONTENT_TYPE
            = "vnd.android.cursor.dir/vnd.storeyworthy.prototype";

    /** The MIME type of a single event */
    private static final String CONTENT_ITEM_TYPE
            = "vnd.android.cursor.item/vnd.storeyworthy.prototype";

    private RecipeTableHelper recipes;
    private UriMatcher uriMatcherR;
    // ...



    @Override
    public boolean onCreate() {
        uriMatcherR = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcherR.addURI(AUTHORITY, "recipe", RECIPES);
        uriMatcherR.addURI(AUTHORITY, "recipe/#", RECIPES_ID);
        recipes = new RecipeTableHelper(getContext());
        return true;
    }



    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String orderBy) {
        if (uriMatcherR.match(uri) == RECIPES_ID) {
            long id = Long.parseLong(uri.getPathSegments().get(1));
            selection = appendRowId(selection, id);
        }

        // Get the database and run the query
        SQLiteDatabase db = recipes.getReadableDatabase();
        Cursor cursor = db.query(RECIPE_TABLE_NAME, projection, selection,
                selectionArgs, null, null, orderBy);

        // Tell the cursor what uri to watch, so it knows when its
        // source data changes
        cursor.setNotificationUri(getContext().getContentResolver(),
                uri);
        return cursor;
    }



    @Override
    public String getType(Uri uri) {
        switch (uriMatcherR.match(uri)) {
            case RECIPES:
                return CONTENT_TYPE;
            case RECIPES_ID:
                return CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }



    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = recipes.getWritableDatabase();

        // Validate the requested uri
        if (uriMatcherR.match(uri) != RECIPES) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Insert into database
        long id = db.insertOrThrow(RECIPE_TABLE_NAME, null, values);

        // Notify any watchers of the change
        Uri newUri = ContentUris.withAppendedId(RECIPE_CONTENT_URI, id);
        getContext().getContentResolver().notifyChange(newUri, null);
        return newUri;
    }



    @Override
    public int delete(Uri uri, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = recipes.getWritableDatabase();
        int count;
        switch (uriMatcherR.match(uri)) {
            case RECIPES:
                count = db.delete(RECIPE_TABLE_NAME, selection, selectionArgs);
                break;
            case RECIPES_ID:
                long id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.delete(RECIPE_TABLE_NAME, appendRowId(selection, id),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Notify any watchers of the change
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }



    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        SQLiteDatabase db = recipes.getWritableDatabase();
        int count;
        switch (uriMatcherR.match(uri)) {
            case RECIPES:
                count = db.update(RECIPE_TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case RECIPES_ID:
                long id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.update(RECIPE_TABLE_NAME, values, appendRowId(
                        selection, id), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Notify any watchers of the change
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }



    /** Append an id test to a SQL selection expression */
    private String appendRowId(String selection, long id) {
        return _ID + "=" + id
                + (!TextUtils.isEmpty(selection)
                ? " AND (" + selection + ')'
                : "");
    }


}
