package com.storeyworthy.prototype;

import static android.provider.BaseColumns._ID;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_CONTENT_URI;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_NAME;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.storeyworthy.prototype.R;
import com.storeyworthy.prototype.RecipeTableHelper;

import java.io.Serializable;

import static com.storeyworthy.prototype.R.id.input_amt;
import static com.storeyworthy.prototype.R.id.input_ing;
import static com.storeyworthy.prototype.R.id.spinner_weight;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_CONTENT_URI;
import static com.storeyworthy.prototype.RecipeConstants.RECIPE_NAME;

public class RecipeBlueprint extends AppCompatActivity {

//    IngredientTable it;
    RecipeProvider rt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_blueprint);

        Intent orb = getIntent();
        final Intent orl = new Intent(this, RecipeList.class);

//        it = new IngredientTable(this);
        rt = new RecipeProvider();

        EditText fragbutt = (EditText) findViewById(R.id.text_nxt_ing);
        fragbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();

                IngredientLine ing_frag = new IngredientLine();

                ft.add(R.id.frag_layout, ing_frag);

                ft.commit();

            }
        });
        Button add = (Button) findViewById(R.id.button_add_recipe);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                View list = findViewById(R.id.frag_layout);
                View rname = findViewById(R.id.text_nameofrecipe);
                ContentValues n = new ContentValues();
                n.put(RECIPE_NAME, rname.toString());

                    rt.insert(RECIPE_CONTENT_URI, n);

                    startActivity(orl);



               /* Cursor masterID = rt.getRecipeID(rname.toString());
                while (masterID.moveToNext()) {
                    int mID = masterID.getInt(0);
                }
                int mID = Integer.parseInt(masterID.toString());

                for(int index=0; index<((ViewGroup)list).getChildCount(); ++index) {
                    View firstChild = ((ViewGroup) list).getChildAt(index);
                    for (int indexa = 0; index < ((ViewGroup) firstChild).getChildCount(); ++indexa) {
                        View secondChild = ((ViewGroup) firstChild).getChildAt(indexa);
                        for (int indexi = 0; index < ((ViewGroup) secondChild).getChildCount(); ++indexi) {
                            View lastChild = ((ViewGroup) secondChild).getChildAt(indexi);

                            String ing = (findViewById(input_ing).toString());
                            Float amt = Float.valueOf(findViewById(input_amt).toString());
                            String wei =(findViewById(spinner_weight).toString());

                            it.insertIngredient(mID, ing, amt, wei);

                        }
                    }
                }*/


                }
            });

        };

    }