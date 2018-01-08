package ru.pks.pizzatime;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PizzaTimeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "pizza_time";
    private static final int DB_VERSION = 2;

    PizzaTimeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createMyDatabase(db,0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertPizza(SQLiteDatabase db, String name, String description, String bonus,
                                    int type, int type_bonus, int orderQuantity) {
        ContentValues pizzaValues = new ContentValues();
        pizzaValues.put("NAME", name);
        pizzaValues.put("DESCRIPTION", description);
        pizzaValues.put("BONUS", bonus);
        pizzaValues.put("TYPE", type);
        pizzaValues.put("TYPE_BONUS", type_bonus);
        pizzaValues.put("ORDER_QUANTITY", orderQuantity);
        db.insert("PTIME", null, pizzaValues);
    }

    private void createMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE PTIME ("
                + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "BONUS TEXT, "
                + "TYPE INTEGER, "
                + "TYPE_BONUS INTEGER, "
                + "ORDER_QUANTITY INTEGER);");

        insertPizza(db, "Raphael\'s PIZZA", "Pizza with secret ingredients.\n" +
                        "As Rafael himself says, I just take everything that is in the fridge.",
                "Take 3 and get 1 for free", 1, 0, 0);

        insertPizza(db, "Raphael\'s PIZZA (Bonus)", "N/A", "N/A",
                1, 1, 0);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE PTIME");
        createMyDatabase(db, oldVersion, newVersion);
    }
}
