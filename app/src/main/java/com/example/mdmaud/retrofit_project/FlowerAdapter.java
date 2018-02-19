package com.example.mdmaud.retrofit_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BITM Trainer 601 on 12/24/2017.
 */

public class FlowerAdapter extends ArrayAdapter<FlowerResponse> {

    private  Context context;
    private  List<FlowerResponse>flowerResponses;
    LayoutInflater inflater;

    public FlowerAdapter(@NonNull Context context, List<FlowerResponse> flowerResponses) {
        super(context, R.layout.flower_row,flowerResponses);
        this.context = context;
        this.flowerResponses = flowerResponses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.flower_row,parent,false);

        TextView nameTv=convertView.findViewById(R.id.flowerName);
        TextView priceTv=convertView.findViewById(R.id.flowerPrice);

        nameTv.setText(flowerResponses.get(position).getName());
        priceTv.setText(String.valueOf(flowerResponses.get(position).getPrice()));
        return convertView;

    }
}
