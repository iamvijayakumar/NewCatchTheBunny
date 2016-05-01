package bunnycatch.com.catchthebunny;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Random;

import bunnycatch.com.catchthebunny.adapters.ImageAdapter;


public class AndroidGridLayoutActivity extends Activity {
    public int count=0,randompos;
    public ImageAdapter imageAdapter;
    public Handler handler =new Handler();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();

        final TextView textview4 =(TextView)findViewById(R.id.textView4);

        //Timer
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                textview4.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textview4.setText("Time's Up..!!!");

                editor.putString("score", String.valueOf(count));
                editor.commit();

                Intent i = new Intent(AndroidGridLayoutActivity.this, PopupActivity.class);
                startActivity(i);
                finish();

            }
        }.start();

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        final Runnable r = new Runnable() {
            public void run() {
                handler.postDelayed(this, 3000);

                Random randomGenerator = new Random();
                randompos = randomGenerator.nextInt(8);

                imageAdapter.randomNumber(randompos);
                imageAdapter.notifyDataSetInvalidated();
            }
        };
        handler.postDelayed(r, 0000);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                TextView textView = (TextView)findViewById(R.id.text1_view);
                Log.e("The Random value is", String.valueOf(randompos));
                Log.e("Position : ",String.valueOf(position));

                TextView text5View=(TextView)findViewById(R.id.text5_view);//Save the animals

                TextView text2View=(TextView)findViewById(R.id.text2_view);//Number of Animals Saved is

                TextView textView1=(TextView)findViewById(R.id.text3_view);//SUCCESS

                TextView textView2=(TextView)findViewById(R.id.text4_view);//Try Again
                textView2.setVisibility(View.INVISIBLE);

                if(randompos==position) {

                    text5View.setVisibility(View.GONE);
                    text2View.setVisibility(View.VISIBLE);
                    //textView1.setVisibility(View.VISIBLE);
                    count++;
                    textView.setText(String.valueOf(count));
                    Log.e("The Count value is", String.valueOf(count));

                                }
                else {
                    text5View.setVisibility(View.GONE);
                    //textView1.setVisibility(View.GONE);
                    //textView2.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}