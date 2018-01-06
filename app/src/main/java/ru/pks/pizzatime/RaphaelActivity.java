package ru.pks.pizzatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RaphaelActivity extends MainActivity {

    public static final String ORDER_RAFAEL = "orderRafael";
    private static final String ITEM_KEY = "item_key";
    private static final String ITEM_BONUS_KEY = "item_bonus_key";
    private static final String ITEM_TOTAL_KEY = "item_total_key";
    private static final String TAG = "RaphaelActivity";
    private static final int TYPE = 1;
    private int item;
    protected static int itemBonusRaphael;
    private int itemTotal;
    private TextView itemView;
    private TextView itemBonusView;
    private TextView itemTotalView;
    private ImageButton plus;
    private ImageButton minus;
    private FloatingActionButton offer;
    private FloatingActionButton addToOrder;
    private String orderRafael;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raphael);

        Log.d(TAG, "onCreate Started");

        if (savedInstanceState != null) {
            item = savedInstanceState.getInt(ITEM_KEY);
            itemBonusRaphael = savedInstanceState.getInt(ITEM_BONUS_KEY);
            itemTotal = savedInstanceState.getInt(ITEM_TOTAL_KEY);
        } else {
            item = 0;
            itemBonusRaphael = 0;
            itemTotal = 0;
        }

        initUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState Started");
        outState.putInt(ITEM_KEY, item);
        outState.putInt(ITEM_BONUS_KEY, itemBonusRaphael);
        outState.putInt(ITEM_TOTAL_KEY, itemTotal);
    }

    private void itemPlus() {
        item++;
        Bonus.bonusItem(TYPE, item);
        itemTotal = item + itemBonusRaphael;
    }

    private void itemMinus() {
        if (item > 0) {
            item--;
            Bonus.bonusItem(TYPE, item);
            itemTotal = item + itemBonusRaphael;
        } else {
            item = 0;
            itemBonusRaphael = 0;
            itemTotal = 0;
        }
    }


    private void initUI() {
        itemView = findViewById(R.id.itemView);
        itemBonusView = findViewById(R.id.itemBonusView);
        itemTotalView = findViewById(R.id.itemTotalView);

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
                Intent orderReturn = new Intent();
                orderReturn.putExtra(ORDER_RAFAEL, orderRafael);
                setResult(RESULT_OK, orderReturn);
                finish();
            }
        });

        updateUI();
    }

    private void updateUI() {
        itemView.setText(getString(R.string.you) + item);
        itemBonusView.setText(getString(R.string.bonus) + itemBonusRaphael);
        itemTotalView.setText(getString(R.string.total) + itemTotal);
        orderRafael = getString(R.string.raphael_pizza) + ": " + itemTotal + " (" + itemBonusRaphael + " "+ getString(R.string.free) + ")";
    }
}
