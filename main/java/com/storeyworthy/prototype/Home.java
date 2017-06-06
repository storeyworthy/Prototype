package com.storeyworthy.prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Home extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View button1 = findViewById(R.id.bu_recipes);
        button1.setOnClickListener(this);
        View button2 = findViewById(R.id.bu_planner);
        button2.setOnClickListener(this);
        View button3 = findViewById(R.id.bu_shop);
        button3.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bu_recipes:
                Intent orl = new Intent(this, RecipeList.class);
                startActivity(orl);
//            case R.id.bu_planner:
//                Intent opc = new Intent (this, PlannerCalendar.class);
//                finish();
//            case R.id.bu_shop:
//                Intent osl = new Intent (this, ShoppingList.class);
        }
    }
}
