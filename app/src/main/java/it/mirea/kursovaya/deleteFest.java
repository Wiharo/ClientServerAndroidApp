package it.mirea.kursovaya;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class deleteFest extends AppCompatActivity {
    private Button delete_fest;
    private EditText deleteEditText;
    private ApiClient apiClient;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_fest);

        delete_fest = findViewById(R.id.delete_button);
        deleteEditText = findViewById(R.id.deleteEditText);
        apiClient = new ApiClient(this);
        delete_fest.setOnClickListener(v -> {
            String festName = deleteEditText.getText().toString();

            apiClient.deleteFestByName(festName, response -> {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Fest deleted successfully", Toast.LENGTH_SHORT).show();
                });
            }, error -> {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Failed to delete fest", Toast.LENGTH_SHORT).show();
                });
            });
        });


    }

}
