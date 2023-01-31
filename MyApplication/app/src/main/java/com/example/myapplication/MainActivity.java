package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Pizza;
import com.example.myapplication.PizzaAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pizza> pizzaList = new ArrayList<>();
    private GridView gridView;
    private PizzaAdapter adapter;
    private double totalPrice = 0;
    private TextView totalPriceTextView;
    private SharedPreferences sharedPreferences;

    private static final String PREFERENCE_NAME = "PizzaPrefs";
    private static final String KEY_TOTAL_PRICE = "TotalPrice";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        totalPriceTextView = findViewById(R.id.total_price_text_view);
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        Button totalPriceButton = findViewById(R.id.total_price_button);
        Button deleteCart = findViewById(R.id.delete_cart);



        pizzaList.add(new Pizza("Margherita", 9.99, "Normál pizza, sajt, paradicsomszósz, bazsalikom", R.drawable.margherita));
        pizzaList.add(new Pizza("Pepperoni", 11.99, "Normál pizza, sajt, paradicsomszósz, pepperoni.", R.drawable.pepperoni));
        pizzaList.add(new Pizza("Vegetarian", 12.99, "Normál pizza, paradicsomszósz, 4 zöldség.", R.drawable.vegetarian));
        pizzaList.add(new Pizza("Meat Lovers", 14.99, "Normál pizza, sajt, paradicsomszósz,sonka, virsli, kolbász, szalámi.", R.drawable.meat_lovers));


        adapter = new PizzaAdapter(this, R.layout.pizza_list_item, pizzaList);
        gridView.setAdapter(adapter);

        totalPriceTextView.setText(String.format("%.2f" + "RON", sharedPreferences.getFloat(KEY_TOTAL_PRICE, 0)));

        totalPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat(KEY_TOTAL_PRICE, (float) adapter.getTotalPrice());
                editor.apply();
                totalPriceTextView.setText(String.format("Vegosszeg: %.2f" + "RON", sharedPreferences.getFloat(KEY_TOTAL_PRICE, 0)));
                sharedPreferences.edit().putFloat(PREFERENCE_NAME, (float) totalPrice).apply();
            }
        });
//nem tudtam hogy kell megoldani, hogy nullazza, ennyire haladtam vele.

        deleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove(KEY_TOTAL_PRICE);
                edit.apply();
                totalPriceTextView.equals(0);
            }
        });

    }



}





