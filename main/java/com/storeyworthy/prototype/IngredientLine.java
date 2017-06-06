package com.storeyworthy.prototype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static android.view.KeyEvent.KEYCODE_ENTER;


public class IngredientLine extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View line = inflater.inflate(R.layout.fragment_ingredient_line, container, false);

        final Spinner weights = (Spinner)  line.findViewById(R.id.spinner_weight);

        ArrayAdapter<CharSequence> W = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.weights, android.R.layout.simple_spinner_item);
        W.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weights.setAdapter(W);

        final View ing = line.findViewById(R.id.input_ing);
        final View amt = line.findViewById(R.id.input_amt);
        final View cle = line.findViewById(R.id.button_clear);

        ing.requestFocus();

        ing.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View ing, int keyCode, KeyEvent event) {

                if(keyCode == KEYCODE_ENTER) {
                    amt.requestFocus();
                }
                return false;
            }
        });

        amt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View ing, int keyCode, KeyEvent event) {

                if(keyCode == KEYCODE_ENTER) {
                    weights.requestFocus();
                }
                return false;
            }
        });

        cle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                kill();
            }
        });



        // Inflate the layout for this fragment
        return line;
    }
    public void kill(){
        this.getFragmentManager().beginTransaction().remove(this).commit();
    }
}