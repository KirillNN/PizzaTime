package ru.pks.pizzatime;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PizzaTimeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "pizza_time";
    private static final int DB_VERSION = 1;

    PizzaTimeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertPizza(SQLiteDatabase db, String name, int orderQuantity) {
        ContentValues pizzaValues = new ContentValues();
        pizzaValues.put("NAME", name);
        pizzaValues.put("ORDER_QUANTITY", orderQuantity);
        db.insert("PIZZA", null, pizzaValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE PIZZA ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ORDER_QUANTITY INTEGER);");
            insertPizza(db, "RaphaelPIZZA", 0);
        }
    }
}