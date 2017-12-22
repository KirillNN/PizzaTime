package ru.pks.pizzatime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WelcomeActivity extends AppCompatActivity {

    ListView itemView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final String[] itemNames = {
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.raphael_pizza),
                getString(R.string.splinter_burger)
        };

        itemView = findViewById(R.id.ListView);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemNames);
        itemView.setAdapter(itemAdapter);
    }
}
