package ru.pks.pizzatime;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected SQLiteOpenHelper pizzaDB;
    protected SQLiteDatabase db;
    protected boolean isConnectedRead;
    protected boolean isConnectedWrite;

   protected void inDevelop() {
       Toast inDevelop = Toast.makeText(this, getString(R.string.in_develop), Toast.LENGTH_LONG);
       inDevelop.setGravity(Gravity.CENTER, 0, 0);
       inDevelop.show();
   }

   protected void toastCenterLong(String message) {
       Toast toastCenter = Toast.makeText(this, message, Toast.LENGTH_LONG);
       toastCenter.setGravity(Gravity.CENTER, 0, 0);
       toastCenter.show();
   }

   protected void connectDBRead() {
       try {
           pizzaDB = new PizzaTimeDatabaseHelper(this);
           db = pizzaDB.getReadableDatabase();
           isConnectedRead = true;
       } catch (SQLiteException e) {
           toastCenterLong(getString(R.string.db_error));
           isConnectedRead = false;
       }
   }

    protected void connectDBWrite() {
        try {
            pizzaDB = new PizzaTimeDatabaseHelper(this);
            db = pizzaDB.getReadableDatabase();
            isConnectedWrite = true;
        } catch (SQLiteException e) {
            toastCenterLong(getString(R.string.db_error));
            isConnectedWrite = false;
        }
    }
}
