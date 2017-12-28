package ru.pks.pizzatime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private static final int REQUEST = 1;

    private String order;
    private String orderFull;

    ListView itemView;
    TextView orderView;
    Button sendOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void raphaelStarted() {
        Intent intent = new Intent(WelcomeActivity.this, RaphaelActivity.class);
        startActivityForResult(intent, REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            orderFull = data.getStringExtra(RaphaelActivity.ORDER_RAFAEL);
            updateUI();
        }
    }

    private void initUI() {
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
                    default:
                        inDevelop();
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
                //TODO E-mail
                mail.putExtra(Intent.EXTRA_SUBJECT, "Order");
                Intent chosenIntent = Intent.createChooser(mail, getString(R.string.send_order));
                startActivity(chosenIntent);
            }
        });

        orderView = findViewById(R.id.orderView);
        orderView.setText(order);
    }

    public void updateUI() {
        orderView.setText(orderFull);
    }

    private void inDevelop() {
        Toast inDevelop = Toast.makeText(getApplicationContext(), getString(R.string.in_develop), Toast.LENGTH_LONG);
        inDevelop.setGravity(Gravity.CENTER, 0, 0);
        inDevelop.show();
    }
}
