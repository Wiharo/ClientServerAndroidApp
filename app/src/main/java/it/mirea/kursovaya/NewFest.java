package it.mirea.kursovaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewFest extends AppCompatActivity {
    private Button backToBack;
    private EditText inputFestName, inputFestDescription, inputFestDate, inputFestLocation;
    private DatabaseReference mDataBase;
    private String USER_KEY = "festToDataBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_fest);
        backToBack = findViewById(R.id.backToBack);
        inputFestName = findViewById(R.id.inputFestName);
        inputFestDescription = findViewById(R.id.inputFestDescription);
        inputFestDate = findViewById(R.id.inputFestDate);
        inputFestLocation = findViewById(R.id.inputFestLocation);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        this.setOnClickListeners();


    }

    public void onClickSave(View view){
        String id = mDataBase.getKey();
        String name = inputFestName.getText().toString();
        String description = inputFestDescription.getText().toString();
        String date = inputFestDate.getText().toString();
        String location = inputFestLocation.getText().toString();
        festToDataBase NewNewFest = new festToDataBase(id, name, description, date, location);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(date)
                && !TextUtils.isEmpty(location)) {
            mDataBase.push().setValue(NewNewFest);
            Toast.makeText(this, "Описание мероприятия отправлено!", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }


    }

    private void setOnClickListeners() {
        backToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBack();
            }
        });
    }


    public void openBack() {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }
}