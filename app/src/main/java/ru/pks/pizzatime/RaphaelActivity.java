package ru.pks.pizzatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RaphaelActivity extends AppCompatActivity {

    public static final String ORDER_RAFAEL = "orderRafael";
    private int item;
    private int itemBonus;
    private int itemTotal;
    private TextView itemView;
    private TextView itemBonusView;
    private TextView itemTotalView;
    private ImageButton plus;
    private ImageButton minus;
    private ImageButton offer;
    private Button addToOrder;
    private String orderRafael;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raphael);



        initUI();
    }

    private void itemPlus() {
        item++;
        itemBonus();
        itemTotal = item + itemBonus;

    }

    private void itemMinus() {
        if (item > 0) {
            item--;
            itemBonus();
            itemTotal = item + itemBonus;
        } else {
            item = 0;
            itemBonus = 0;
            itemTotal = 0;
        }

    }

    private void itemBonus() {
        itemBonus = (int) Math.ceil(item / 3);
    }

    private void initUI() {
        item = 0;
        itemBonus = 0;
        itemTotal = 0;

        itemView = findViewById(R.id.itemView);
        itemBonusView = findViewById(R.id.itemBonusView);
        itemTotalView = findViewById(R.id.itemTotalView);

        offer = findViewById(R.id.offer);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.offer_3_1, Toast.LENGTH_LONG).show();
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
    }

    private void updateUI() {
        itemView.setText(getString(R.string.you) + item);
        itemBonusView.setText(getString(R.string.bonus) + itemBonus);
        itemTotalView.setText(getString(R.string.total) + itemTotal);
        orderRafael = getString(R.string.raphael_pizza) + ": " + itemTotal + " (" + itemBonus + " "+ getString(R.string.free) + ")";
    }
}
