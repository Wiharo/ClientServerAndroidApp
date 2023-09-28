package it.mirea.kursovaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import it.mirea.kursovaya.model.Course;
import it.mirea.kursovaya.model.Order;

public class OverviewPage extends AppCompatActivity {

    private ImageView searchBtn;
    private ImageView overviewBtn;
    private ImageView profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overview_page);

        ListView orders_list = findViewById(R.id.orders_list);

        List<String> courseTitle = new ArrayList<>();
        System.out.println(courseTitle.size());
        for (Course c : SearchPage.fullCourseList) {
            System.out.println(c.getTitle());
            if (Order.items_id.contains(c.getId()))
                courseTitle.add(c.getTitle());
        }

        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseTitle));

        searchBtn = findViewById(R.id.searchBtn);
        overviewBtn = findViewById(R.id.overviewBtn);
        overviewBtn.setColorFilter(getResources().getColor(R.color.white));
        profileBtn = findViewById(R.id.profileBtn);

        this.setOnClickListeners();
    }

    private void setOnClickListeners() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearch();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    public void openSearch() {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }
}