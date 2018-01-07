package ru.pks.pizzatime;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends MainActivity {

    private static final String TAG = "WelcomeActivity";
    private static final String ORDER_FULL = "order_full";

    private String order;
    private String orderFull;
    private String bonusFull;

    private TextView orderView;
    private FloatingActionButton sendOrder;
    private CardView raphael;
    private CardView april;
    private CardView splinter;
    private CardView casey;

    int test1;
    int test3;


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

    protected void raphaelStarted() {
        Intent raphael = new Intent(WelcomeActivity.this, RaphaelActivity.class);
        startActivity(raphael);
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

        splinter = findViewById(R.id.splinter);
        splinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inDevelop();
            }
        });

        casey = findViewById(R.id.casey);
        casey.setOnClickListener(new View.OnClickListener() {
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

        try {
            SQLiteOpenHelper pizzaDB = new PizzaTimeDatabaseHelper(this);
            SQLiteDatabase db = pizzaDB.getReadableDatabase();
            Cursor cursor = db.query("PTIME",
                    new String[]{"TYPE", "TYPE_BONUS", "ORDER_QUANTITY"},
                    null, null, null, null, null);

            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                test1 = cursor.getInt(0);
            }
            cursor.moveToNext();
            if (cursor.moveToNext()){
                test3 = cursor.getInt(0);
            }

            TextView textView = findViewById(R.id.textView);
            textView.setText(String.valueOf(test1));

            TextView textView2 = findViewById(R.id.textView2);
            textView2.setText(String.valueOf(test3));

            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            toastCenterLong(getString(R.string.db_error));
        }

        updateUI();
    }

    public void orderFull() {
        orderFull = order + "\n";
    }

    public void updateUI() {

        if (orderFull.equals(getString(R.string.empty))) {
            orderView.setText(order);
        } else {
            orderView.setText(orderFull);

        }
    }
}
