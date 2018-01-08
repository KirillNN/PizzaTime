package ru.pks.pizzatime;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RaphaelActivity extends MainActivity {

    private static final String TAG = "RaphaelActivity";
    private static final int TYPE = 1;
    protected static int itemRaphaelBonus;
    protected static int itemRaphael;

    private TextView itemView;
    private TextView itemBonusView;
    private ImageButton plus;
    private ImageButton minus;
    private FloatingActionButton gift;
    private FloatingActionButton addToOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raphael);

        Log.d(TAG, "onCreate Started");

        initUI();
    }

    @Override
    protected void onStart() { //Connect DB
        super.onStart();
        connectDBWrite();

    }

    @Override
    protected void onResume() { //Final setting UI
        super.onResume();
        readFromDB();
        updateUI();
    }

    @Override
    protected void onStop() { //Disconnect DB
        super.onStop();
        db.close();
    }

    private void itemPlus() {
        itemRaphael++;
        Bonus.bonusItem(TYPE, itemRaphael);
        updateDB();
    }

    private void itemMinus() {
        if (itemRaphael > 0) {
            itemRaphael--;
            Bonus.bonusItem(TYPE, itemRaphael);
            updateDB();
        } else {
            itemRaphael = 0;
            itemRaphaelBonus = 0;
            updateDB();
        }
    }

    private void initUI() {
        itemView = findViewById(R.id.itemView);
        itemBonusView = findViewById(R.id.itemBonusView);

        gift = findViewById(R.id.gift);
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastCenterLong(getString(R.string.gift_3_1));
            }
        });

        plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPlus();
                updateUI();
            }
        });

        minus = findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemMinus();
                updateUI();
            }
        });

        addToOrder = findViewById(R.id.addToOrder);
        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    private void readFromDB() {
        if (isConnectedWrite) {
            Cursor cursor = db.query("PTIME",
                    new String[]{"ORDER_QUANTITY"},
                    "TYPE = ?",
                    new String[]{"1"},
                    null, null, null);
            if (cursor.moveToFirst()) {
                itemRaphael = cursor.getInt(0);
            }
            if (cursor.moveToNext()) {
                itemRaphaelBonus = cursor.getInt(0);
            }
            cursor.close();
        } else {
            itemRaphael = 0;
            itemRaphaelBonus = 0;
        }
    }

    private void updateDB() {
        ContentValues pizzaValues = new ContentValues();
        pizzaValues.put("ORDER_QUANTITY", itemRaphael);
        db.update("PTIME",
                pizzaValues,
                "_id = ?",
                new String[]{"1"});
        pizzaValues.put("ORDER_QUANTITY", itemRaphaelBonus);
        db.update("PTIME",
                pizzaValues,
                "_id = ?",
                new String[]{"2"});
    }

    private void updateUI() {
        itemView.setText(getString(R.string.you) + itemRaphael);
        itemBonusView.setText(getString(R.string.bonus) + itemRaphaelBonus);
    }
}
