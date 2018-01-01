package ru.pks.pizzatime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends MainActivity {

    private static final int REQUEST_RAPHAEL = 1;
    private static final String TAG = "WelcomeActivity";
    private static final String ORDER_FULL = "order_full";

    private String order;
    private String orderFull;


    TextView orderView;
    FloatingActionButton sendOrder;
    CardView raphael;
    CardView april;
    CardView splinter;
    CardView casey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Log.d(TAG, "onCreate Started");

        if (savedInstanceState != null) {
            orderFull = savedInstanceState.getString(ORDER_FULL);
        } else {
            orderFull = getString(R.string.empty);
        }

        initUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Started");
        outState.putString(ORDER_FULL, orderFull);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Started");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop Started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Started");
    }

    protected void raphaelStarted() {
        Intent intent = new Intent(WelcomeActivity.this, RaphaelActivity.class);
        startActivityForResult(intent, REQUEST_RAPHAEL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RAPHAEL && resultCode == Activity.RESULT_OK && data != null) {
            orderFull = data.getStringExtra(RaphaelActivity.ORDER_RAFAEL);
            updateUI();
        }
    }

    private void initUI() {
        order = getString(R.string.order_text_view);

        raphael = findViewById(R.id.raphael);
        raphael.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raphaelStarted();
            }
        });

        april = findViewById(R.id.april);
        april.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDevelop();
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
        updateUI();
    }

    public void updateUI() {
        if (orderFull.equals(getString(R.string.empty))) {
            orderView.setText(order);
        } else {
            orderView.setText(orderFull);
        }
    }
}
