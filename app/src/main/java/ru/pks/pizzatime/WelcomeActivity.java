package ru.pks.pizzatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private String order;
    private String orderFull;

    ListView itemView;
    TextView orderView;
    Button sendOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        order = getString(R.string.order_text_view);

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

        sendOrder = findViewById(R.id.sendOrder);
        sendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("text/plain");
                mail.putExtra(Intent.EXTRA_TEXT, orderFull);
                mail.putExtra(Intent.EXTRA_EMAIL, "user@server.com");
                mail.putExtra(Intent.EXTRA_BCC, "user@server.com");
                mail.putExtra(Intent.EXTRA_CC, "user@server.com");
                mail.putExtra(Intent.EXTRA_SUBJECT, "Order");
                Intent chosenIntent = Intent.createChooser(mail, getString(R.string.send_order));
                startActivity(chosenIntent);
            }
        });

        orderView = findViewById(R.id.orderView);
        orderView.setText(order);
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
        orderFull = data.getStringExtra("orderRafael");
        updateUI();
    }

    public void updateUI() {
        orderView.setText(orderFull);
    }
}
