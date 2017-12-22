package ru.pks.pizzatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WelcomeActivity extends AppCompatActivity {

    private String order;

    ListView itemView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        order = "";

        final String[] itemNames = {
                getString(R.string.raphael_pizza),
                getString(R.string.splinter_burger)
        };

        itemView = findViewById(R.id.ListView);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemNames);
        itemView.setAdapter(itemAdapter);
        itemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        raphaelStarted();
                        break;
                }
            }
        });
    }

    protected void raphaelStarted() {
        Intent intent = new Intent(WelcomeActivity.this, RaphaelActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String orderReturn = data.getStringExtra("orderReturn");
    }

    public void updateUI() {

    }
}
