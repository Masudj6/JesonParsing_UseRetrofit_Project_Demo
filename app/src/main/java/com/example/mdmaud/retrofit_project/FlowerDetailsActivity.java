package com.example.mdmaud.retrofit_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class FlowerDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_details);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);


        Intent intent = getIntent();
       // String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //textView.setText(message);
        FlowerResponse flowerResponse= (FlowerResponse) intent.getSerializableExtra("flower");

        //picasso er maddome amra server theke data niye ase then activity te show korate hoy
        String photo = flowerResponse.getPhoto();
        Uri photoUri = Uri.parse(MainActivity.BASE_URL+"photos/"+photo);
        Picasso.with(this).load(photoUri).into(imageView);

        //akhane just String text  gulo show korassi
        textView.setText(flowerResponse.getName()+"\n"
                        +flowerResponse.getCategory()+"\n"
                        +flowerResponse.getInstructions()+"\n"
                        +flowerResponse.getPrice());




    }
}
