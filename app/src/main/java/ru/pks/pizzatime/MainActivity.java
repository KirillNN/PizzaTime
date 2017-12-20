package ru.pks.pizzatime;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int item, itemBonus, itemTotal;
    TextView tagline, productName, itemView, itemBonusView, itemTotalView;
    ImageButton plus, minus, offer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
