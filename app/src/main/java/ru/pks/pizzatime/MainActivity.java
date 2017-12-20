package ru.pks.pizzatime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int item, itemBonus, itemTotal;
    TextView itemView, itemBonusView, itemTotalView;
    ImageButton plus, minus, offer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        updateUI();
    }

    void itemPlus() {
        item++;
        itemBonus();
        itemTotal = item + itemBonus;

    }

    void itemMinus() {
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

    void itemBonus() {
        itemBonus = (int) Math.ceil(item / 3);
    }

    void updateUI() {
        itemView.setText(getString(R.string.you) + item);
        itemBonusView.setText(getString(R.string.bonus) + itemBonus);
        itemTotalView.setText(getString(R.string.total) + itemTotal);
    }
}
