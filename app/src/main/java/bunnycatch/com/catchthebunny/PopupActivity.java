package bunnycatch.com.catchthebunny;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by santhanam-pc on 14-04-2016.
 */
public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        String temptoken = pref.getString("score", "No");

        TextView textview7=(TextView)findViewById(R.id.textView7);
        textview7.setText(temptoken);

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
                                   {
                                       @Override
                                       public void onClick(View view)
                                       {
                                           Intent intent = new Intent(PopupActivity.this, AndroidGridLayoutActivity.class);
                                           startActivity(intent);
                                           finish();
                                       }
                                   }
        );


    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}
