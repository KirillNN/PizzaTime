package ru.pks.pizzatime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RaphaelActivity extends MainActivity {

    private static final String ITEM_KEY = "item_key";
    private static final String ITEM_BONUS_KEY = "item_bonus_key";
    private static final String TAG = "RaphaelActivity";
    private static final int TYPE = 1;
    private int item;
    protected static int itemBonusRaphael_1;
    private TextView itemView;
    private TextView itemBonusView;
    private ImageButton plus;
    private ImageButton minus;
    private FloatingActionButton offer;
    private FloatingActionButton addToOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raphael);

        Log.d(TAG, "onCreate Started");

        if (savedInstanceState != null) {
            item = savedInstanceState.getInt(ITEM_KEY);
            itemBonusRaphael_1 = savedInstanceState.getInt(ITEM_BONUS_KEY);
        } else {
            item = 0;
            itemBonusRaphael_1 = 0;
        }

        initUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Started");
        outState.putInt(ITEM_KEY, item);
        outState.putInt(ITEM_BONUS_KEY, itemBonusRaphael_1);
    }

    private void itemPlus() {
        item++;
        Bonus.bonusItem(TYPE, item);
    }

    private void itemMinus() {
        if (item > 0) {
            item--;
            Bonus.bonusItem(TYPE, item);
        } else {
            item = 0;
            itemBonusRaphael_1 = 0;
        }
    }


    private void initUI() {
        itemView = findViewById(R.id.itemView);
        itemBonusView = findViewById(R.id.itemBonusView);

        offer = findViewById(R.id.offer);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastCenterLong(getString(R.string.offer_3_1));
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

        updateUI();
    }

    private void updateUI() {
        itemView.setText(getString(R.string.you) + item);
        itemBonusView.setText(getString(R.string.bonus) + itemBonusRaphael_1);
    }
}
