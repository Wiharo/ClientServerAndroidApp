package it.mirea.kursovaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProfilePage extends AppCompatActivity {

    private ImageView searchBtn;
    private ImageView overviewBtn;
    private ImageView profileBtn;
    private Button exit_app, tellAboutNew, moderatorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_page);

        searchBtn = findViewById(R.id.searchBtn);
        overviewBtn = findViewById(R.id.overviewBtn);
        profileBtn = findViewById(R.id.profileBtn);
        exit_app = findViewById(R.id.exit_app);
        profileBtn.setColorFilter(getResources().getColor(R.color.white));
        tellAboutNew = findViewById(R.id.tellAboutNew);
        moderatorBtn = findViewById(R.id.moderatorBtn);

        this.setOnClickListeners();
    }

    private void setOnClickListeners() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });

        overviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOverview();
            }
        });
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExitApp();
            }
        });
        tellAboutNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTellAboutNew();
            }
        });
        moderatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModerator();
            }
        });
    }

    public void openSearch() {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }

    public void openOverview() {
        Intent intent = new Intent(this, OverviewPage.class);
        startActivity(intent);
    }
    public void openExitApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openTellAboutNew() {
        Intent intent = new Intent(this, NewFest.class);
        startActivity(intent);
    }
    public void openModerator() {
        Intent intent = new Intent(this, Moderator.class);
        startActivity(intent);
    }
}