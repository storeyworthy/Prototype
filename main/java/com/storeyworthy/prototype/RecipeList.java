package com.storeyworthy.prototype;

import static android.provider.BaseColumns._ID;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_CONTENT_URI;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_NAME;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.storeyworthy.prototype.Item.Item;

import java.util.ArrayList;

public class RecipeList extends AppCompatActivity {

    private RecyclerView mRecycle;
    private static RecipeRecyclerCursorAdapter mAdapter;
    private LinearLayoutManager mLayout;
    private static String [] rFROM = {_ID, RECIPE_NAME};
    private static int[] rTO = {R.id.t_rec_name, R.id.t_rec_name};
    private static String ORDER_BY = _ID + "DESC";
    private final static int LOADER_ID = 1;
    private SimpleCursorAdapter rAdapter;
    private  RecipeTableHelper helper;
    private ArrayList<Item> arrayList = new ArrayList<Item>();
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecycle = (RecyclerView) findViewById(R.id.rec_list);
        mLayout = new LinearLayoutManager(this);
        mRecycle.setAdapter(mAdapter);
        mRecycle.setLayoutManager(mLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        helper = new RecipeTableHelper(this);
        Intent orl = getIntent();
        final Intent orb = new Intent (this, RecipeBlueprint.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(orb);

            }
        });

        mAdapter = new RecipeRecyclerCursorAdapter(cursor) {

            @Override
            public void onBindViewHolder(VH holder, Cursor cursor) {
                if (cursor.moveToFirst()){
                    do {
                        VH item = new VH(findViewById(R.id.rec_list));
                        item.id.setText(BaseColumns._ID);
                        item.name.setText(convertToString(cursor));
                        item.itemView.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {

                            }

                    });


            }while (cursor.moveToNext());
        ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(orb);

            }
        });
    }}

    private Cursor cursor(){

        return helper.getAllRecipes();
    }

};}}
