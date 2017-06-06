package com.storeyworthy.prototype;

/**
 * Created by Darth Bane on 6/3/2017.
 */

import static android.provider.BaseColumns._ID;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_TABLE_NAME;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeTableHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "recipemaster.db";
    private static final int DATABASE_VERSION = 1;
    private final Context myContext;

    public RecipeTableHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + RECIPE_TABLE_NAME +
                        "(" + _ID + " INTEGER PRIMARY KEY, " +
                        RECIPE_NAME + " TEXT) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRecipe(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(RECIPE_NAME, name);

        db.insert(RECIPE_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, RECIPE_TABLE_NAME);
        return numRows;
    }

    public boolean updateRecipe(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_ID, id);
        db.update(RECIPE_TABLE_NAME, contentValues, _ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteRecipe(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RECIPE_TABLE_NAME,
                _ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + RECIPE_TABLE_NAME + " WHERE " +
                _ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getRecipeID(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + RECIPE_TABLE_NAME + " WHERE " +
                RECIPE_NAME + "=?", new String[]{name});
        return res;
    }

    public Cursor getAllRecipes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + RECIPE_TABLE_NAME, null );
        return res;
    }


}
