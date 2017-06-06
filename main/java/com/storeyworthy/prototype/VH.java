package com.storeyworthy.prototype;

import android.annotation.SuppressLint;
import static android.provider.BaseColumns._ID;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Darth Bane on 6/5/2017.
 */

public class VH extends RecyclerView.ViewHolder {
    public TextView id;
    public TextView name;
    public Button x;


    public VH(View itemView) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.t_rec_id);
        name = (TextView) itemView.findViewById(R.id.t_rec_name);
        x = (Button) itemView.findViewById(R.id.bu_delete_rec);

    }
}
