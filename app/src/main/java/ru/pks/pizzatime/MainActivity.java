package ru.pks.pizzatime;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
}
