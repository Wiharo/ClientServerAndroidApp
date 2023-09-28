package it.mirea.kursovaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Moderator extends AppCompatActivity {
    private Button add_fest, all_fest, delete_fest, mainpage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_moderator_page);

        add_fest = findViewById(R.id.add_fest);
        all_fest = findViewById(R.id.all_fest);
        delete_fest = findViewById(R.id.delete_fest);
        mainpage = findViewById(R.id.main);
        this.setOnClickListeners();
    }

    private void setOnClickListeners() {
        add_fest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFest();
            }
        });
        all_fest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllFest();
            }
        });
        delete_fest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFest();
            }
        });
        mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainPage();
            }
        });
    }

    public void openAddFest() {
        Intent intent = new Intent(this, AddFest.class);
        startActivity(intent);
    }
    public void openAllFest() {
        Intent intent = new Intent(this, Festsearcher.class);
        startActivity(intent);
    }
    public void deleteFest() {
        Intent intent = new Intent(this, deleteFest.class);
        startActivity(intent);
    }
    public void openMainPage() {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }


}