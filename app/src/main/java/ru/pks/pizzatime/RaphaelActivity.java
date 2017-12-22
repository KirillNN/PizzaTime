package ru.pks.pizzatime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RaphaelActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raphael);

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
                
            }
        });

        updateUI();
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

    private void updateUI() {
        itemView.setText(getString(R.string.you) + item);
        itemBonusView.setText(getString(R.string.bonus) + itemBonus);
        itemTotalView.setText(getString(R.string.total) + itemTotal);
    }
}
