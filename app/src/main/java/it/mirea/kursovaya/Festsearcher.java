package it.mirea.kursovaya;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Festsearcher extends AppCompatActivity implements FestAdapter.OnItemClickListener{
    private EditText searchEditText;
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fest_searcher);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        festAdapter = new FestAdapter(new ArrayList<>());
        recyclerView.setAdapter(festAdapter);
        festAdapter.setOnItemClickListener(this);
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        Button freeEventsButton = findViewById(R.id.freeEventsButton);
        freeEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFreeEvents();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchEditText.getText().toString();
                performSearch(searchQuery);
            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchQuery = searchEditText.getText().toString();
                if (searchQuery.isEmpty()) {
                    festAdapter.setFestList(originalFestList);
                }
            }
        });

        fetchFests();
    }

    private List<FestModel> originalFestList;

    private void fetchFests() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FestApi festApi = retrofit.create(FestApi.class);
        Call<List<FestModel>> call = festApi.getFests();
        call.enqueue(new Callback<List<FestModel>>() {
            @Override
            public void onResponse(Call<List<FestModel>> call, Response<List<FestModel>> response) {
                if (response.isSuccessful()) {
                    originalFestList = response.body();
                    festAdapter.setFestList(originalFestList);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<FestModel>> call, Throwable t) {

            }
        });
    }





    private RecyclerView recyclerView;
    private FestAdapter festAdapter;


    private void performSearch(String query) {
        List<FestModel> searchResults = new ArrayList<>();

        for (FestModel fest : festAdapter.getFestList()) {
            if (fest.getName().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(fest);
            }
        }

        festAdapter.setFestList(searchResults);
    }



    @Override
    public void onItemClick(FestModel fest) {
        String description = fest.getDescription();
        String location = fest.getLocation();
        AlertDialog.Builder builder = new AlertDialog.Builder(Festsearcher.this);
        builder.setTitle("Описание мероприятия")
                .setMessage("Description: " + description + " " + "Location: " + location)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
    private void showFreeEvents() {
        List<FestModel> freeEventsList = new ArrayList<>();

        for (FestModel fest : festAdapter.getFestList()) {
            if (fest.getPrice().equalsIgnoreCase("free")) {
                freeEventsList.add(fest);
            }
        }

        festAdapter.setFestList(freeEventsList);
    }


}
