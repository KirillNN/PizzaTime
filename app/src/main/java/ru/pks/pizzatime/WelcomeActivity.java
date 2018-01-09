package ru.pks.pizzatime;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends MainActivity {

    private static final String TAG = "WelcomeActivity";

    private String orderFull;

    private TextView orderView;
    private FloatingActionButton sendOrder;
    private CardView raphael;
    private CardView april;
    private CardView splinter;
    private CardView casey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Log.d(TAG, "onCreate Started");

        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        connectDBRead();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFromDB();
        updateUI();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    protected void raphaelStarted() {
        Intent raphael = new Intent(WelcomeActivity.this, RaphaelActivity.class);
        startActivity(raphael);
    }

    private void initUI() {
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
                toastCenterLong(getString(R.string.in_develop));
            }
        });

        splinter = findViewById(R.id.splinter);
        splinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastCenterLong(getString(R.string.in_develop));
            }
        });

        casey = findViewById(R.id.casey);
        casey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastCenterLong(getString(R.string.in_develop));
            }
        });

        sendOrder = findViewById(R.id.sendOrder);
        sendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("text/plain");
                mail.putExtra(Intent.EXTRA_TEXT, orderFull);
                String[] to = {getString(R.string.e_mail)};
                mail.putExtra(Intent.EXTRA_EMAIL, to);
                mail.putExtra(Intent.EXTRA_SUBJECT, "Order");
                Intent chosenIntent = Intent.createChooser(mail, getString(R.string.send_order));
                startActivity(chosenIntent);
            }
        });

        orderView = findViewById(R.id.orderView);

        updateFromDB();

//            DatabaseUtils.dumpCursorToString(cursor);
        //TODO Question about dumpCursorToString

        updateUI();
    }

    private void orderFull() {
        orderFull = getString(R.string.order_text_view);
        if (RaphaelActivity.itemRaphael > 0) {
            orderFull += (getString(R.string.raphael_pizza) + ": " + RaphaelActivity.itemRaphael + " " + getString(R.string.pieces) + "\n");
        }
        if (RaphaelActivity.itemRaphaelBonus > 0) {
            orderFull += (getString(R.string.raphael_pizza_bonus) + ": " + RaphaelActivity.itemRaphaelBonus + " " + getString(R.string.pieces) + "\n");
        }
    }

    private void updateFromDB() {
        if (isConnectedRead) {
            Cursor cursor = db.query("PTIME",
                    new String[]{"TYPE", "TYPE_BONUS", "ORDER_QUANTITY"},
                    null, null, null, null, null);
            if (cursor.moveToFirst()) {
                RaphaelActivity.itemRaphael = cursor.getInt(2);
            }
            if (cursor.moveToNext()) {
                RaphaelActivity.itemRaphaelBonus = cursor.getInt(2);
            }
            cursor.close();
        }
    }

    private void updateUI() {
        orderFull();
        orderView.setText(orderFull);
    }
}
