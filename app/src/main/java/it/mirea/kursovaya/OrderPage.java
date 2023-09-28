package it.mirea.kursovaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.mirea.kursovaya.model.Order;

public class OrderPage extends AppCompatActivity {

    private ImageView addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        addToCart = findViewById(R.id.addToCart);

        ConstraintLayout courseBg = findViewById(R.id.coursePageBg);
        ImageView courseImage = findViewById(R.id.coursePageImage);
        TextView courseTitle = findViewById(R.id.coursePageTitle);
        TextView courseDate = findViewById(R.id.coursePageData);
        TextView courseLevel = findViewById(R.id.coursePageLevel);
        TextView courseText = findViewById(R.id.coursePageText);

        courseBg.setBackgroundColor(getIntent().getIntExtra("courseBg", 0));
        courseImage.setImageResource(getIntent().getIntExtra("courseImage", 0));
        courseTitle.setText(getIntent().getStringExtra("courseTitle"));
        courseDate.setText(getIntent().getStringExtra("courseDate"));
        courseLevel.setText(getIntent().getStringExtra("courseLevel"));
        courseText.setText(getIntent().getStringExtra("courseText"));

        this.setOnClickListeners();
    }

    private void setOnClickListeners() {
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }

    public void addToCart() {
        int item_id = getIntent().getIntExtra("courseId", 0);
        if (Order.items_id.contains(item_id)) {
            Toast.makeText(this, "Вы уже добавили это мероприятие!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Добавлено!", Toast.LENGTH_LONG).show();
            Order.items_id.add(item_id);
        }
    }
}