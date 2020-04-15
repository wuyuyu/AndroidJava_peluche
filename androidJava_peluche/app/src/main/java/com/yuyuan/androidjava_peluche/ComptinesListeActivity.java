package com.yuyuan.androidjava_peluche;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ComptinesListeActivity extends AppCompatActivity {
    private List<Comptine> comptineList = new ArrayList<>();
    private ComptineAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comptines_liste);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        View view =getSupportActionBar().getCustomView();
        //getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAcceuil();
            }
        });

        //Intent srcIntent = getIntent();
        //comptineList = srcIntent.getParcelableArrayListExtra("ComptineList");


        adapter = new ComptineAdapter(comptineList);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewComptines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loadDataFromApi();


    }

    private void loadDataFromApi() {
        //    String userChoice = userchoice();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?api_key=d0f80747d8ac43db918936f4a3d09e9c&language=fr&sort_by=popularity.desc&page=1")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("MainActivity", "onFailue", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();


                try {

                    JSONObject jsonBody = new JSONObject(body);
                    JSONArray results = jsonBody.getJSONArray("results");


                    // boucle pour enregistrer les films dans une liste

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject comptine1 = results.getJSONObject(i);

                        String comptineImageUrlTronquee = comptine1.getString("poster_path");
                        String comptineImage = "https://image.tmdb.org/t/p/original/" + comptineImageUrlTronquee;
                        Log.i("MainActivity", "comptineImage" + comptineImage);

                        String nom = comptine1.getString("original_title");
                        String date = comptine1.getString("release_date");
                        String divers = comptine1.getString("overview");



                        comptineList.add(new Comptine(comptineImage,nom,date,divers));

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                    System.out.println(comptineList.get(3).toString());



                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }

            }
        });
    }

    private void goToAcceuil() {
        final Intent intentAcceuil = new Intent(this, MainActivity.class);
        startActivity(intentAcceuil);
        finish();
    }
}