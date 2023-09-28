package it.mirea.kursovaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import it.mirea.kursovaya.adapter.CourseAdapter;
import it.mirea.kursovaya.adapter.CategoryAdapter;
import it.mirea.kursovaya.model.Category;
import it.mirea.kursovaya.model.Course;

public class SearchPage extends AppCompatActivity {

    private RecyclerView categoryRecycler;
    private RecyclerView courseRecycler;
    private CategoryAdapter categoryAdapter;

    public static CourseAdapter courseAdapter;
    public static final List<Course> courseList = new ArrayList<>();
    public static final List<Course> fullCourseList = new ArrayList<>();

    private ImageView searchBtn;
    private ImageView overviewBtn;
    private ImageView profileBtn;
    private Button searcherfestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setColorFilter(getResources().getColor(R.color.white));
        overviewBtn = findViewById(R.id.overviewBtn);
        profileBtn = findViewById(R.id.profileBtn);
        searcherfestBtn = findViewById(R.id.searcherfestBtn);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(0, "Романтика"));
        categoryList.add(new Category(1, "Искусство"));
        categoryList.add(new Category(2, "Активный"));
        categoryList.add(new Category(3, "Мода"));
        categoryList.add(new Category(4, "Музыка"));
        categoryList.add(new Category(5, "Прочее"));

        setCategoryRecycler(categoryList);

        if (fullCourseList.isEmpty()) {
            courseList.add(new Course(0, 0, "japan", "Фестиваль\n японской культуры", "26 июля", "от 300 руб.", "#EC5252", "Ощутите себя в Японии на день - Косплей, вкусная еда, а также самурайские дуэли в полном обмундировании!"));
            courseList.add(new Course(1, 1, "banksy", "Открытие выставки\n BANKSY", "1 августа", "от 500 руб.", "#D8A501", "Картины соврменного художника/графитчика уже давно сводят с ума 21 век, никто не останется равнодушныи"));
            courseList.add(new Course(2, 2, "photo", "Мастер класс\n по городской съемке", "5 августа", "бесплатно", "#2AE78C", "Всего лишь за 4 часа ведущие профессионалы своего дела научат делать вас восхитительные снимки"));
            courseList.add(new Course(3, 3, "brand", "Закрытый показ\n бренда WIHARO", "26 июля", "от 250 руб.", "#5A60E1", "Новые формы, принты, а также крой - у тебя будет шанс получить архивную вещь"));
            courseList.add(new Course(4, 4, "painting", "Выставка картин\n Гюстава Курбе", "1 августа", "от 700 руб.", "#D39800", "Великий французский основатель реализма в живописи, эти картины должен видеть каждый"));

            fullCourseList.addAll(courseList);
        }

        setCourseRecycler(courseList);

        this.setOnClickListeners();
    }

    private void setOnClickListeners() {
        overviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOverview();
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        searcherfestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFestsearcher();
            }
        });
    }

    public void openOverview() {
        Intent intent = new Intent(this, OverviewPage.class);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    public void openFestsearcher() {
        Intent intent = new Intent(this, Festsearcher.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public static void showCoursesByCategory(int category) {
        courseList.clear();
        courseList.addAll(fullCourseList);
        List<Course> filterCourses = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getCategory() == category)
                filterCourses.add(c);
        }

        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();
    }
}