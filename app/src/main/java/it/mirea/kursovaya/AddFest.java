package it.mirea.kursovaya;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFest extends AppCompatActivity {
    private EditText nameEditText, descriptionEditText, dateTimeEditText, categoryNameEditText,
    priceEditText, locationEditText;
    private ApiClient apiClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_fest);
        nameEditText = findViewById(R.id.name_input);
        descriptionEditText = findViewById(R.id.description_input);
        dateTimeEditText = findViewById(R.id.date_time_input);
        categoryNameEditText = findViewById(R.id.category_name_input);
        priceEditText = findViewById(R.id.price_input);
        locationEditText = findViewById(R.id.location_input);
        Button addfester = findViewById(R.id.festadder_button);
        apiClient = new ApiClient(this);

        addfester.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String date_time = dateTimeEditText.getText().toString();
            String category_name = categoryNameEditText.getText().toString();
            String price = priceEditText.getText().toString();
            String location = locationEditText.getText().toString();

            apiClient.AddNewFest(name, description, date_time, category_name, price, location,
                    response -> {
                runOnUiThread(() -> {
                    Intent intent = new Intent(this, Moderator.class);
                    this.startActivity(intent);
                    Toast.makeText(this, "Мероприятие добавлено успешно", Toast.LENGTH_SHORT).show();
                });
            }, error -> {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show();
                });
            });
        });
    }
}