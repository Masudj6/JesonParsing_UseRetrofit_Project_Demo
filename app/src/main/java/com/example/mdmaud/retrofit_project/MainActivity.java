package com.example.mdmaud.retrofit_project;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://services.hanselandpetal.com/";
    public static final String BASE_URL_WEATHER = "http://api.openweathermap.org/data/2.5/";
    private  FlowerAdapter flowerAdapter;
    private FlowerService service;
    private List<FlowerResponse>flowers=new ArrayList<>();
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);


        //Retrofit object call
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(FlowerService.class);

       //data gulo jeson theke palam......
        Call<List<FlowerResponse>> call = service.getAllFlowers();


        call.enqueue(new Callback<List<FlowerResponse>>() {
            @Override
            public void onResponse(Call<List<FlowerResponse>> call, Response<List<FlowerResponse>> response) {
                //sob data akon reponse er vitor ase ..abar data gulo niye ja essa koro
                if(response.code()==200){
               flowers= response.body();//body er vitor sob data ase

               //akon adapter er maddome response gulo list view te set or diye dilam...
               flowerAdapter=new FlowerAdapter(MainActivity.this,flowers);
               listView.setAdapter(flowerAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<FlowerResponse>> call, Throwable t) {

            }
        });

        //je data paisi list thkeke akon item clik kore akta activity te dakhabo...
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FlowerResponse flowerResponse = flowers.get(position);
                Log.e(TAG,"flower"+flowerResponse);


                //intent er maddome data onno activity te niye jassi & puro object ta satha niye jassi
                //object niye gele (intent.getSerializableExtra("flower")) diye data dorte hobe vvi
                Intent intent = new Intent(MainActivity.this, FlowerDetailsActivity.class);
               // EditText editText = (EditText) findViewById(R.id.editText);
               // String message = editText.getText().toString();
                intent.putExtra("flower", flowerResponse);
                Log.e(TAG,"flower"+flowerResponse);
                startActivity(intent);

            }
        });



    }
}
