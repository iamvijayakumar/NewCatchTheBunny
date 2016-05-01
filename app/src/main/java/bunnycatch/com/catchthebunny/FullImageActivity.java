package bunnycatch.com.catchthebunny;

/**
 * Created by santhanam-pc on 08-04-2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import bunnycatch.com.catchthebunny.adapters.ImageAdapter;

public class FullImageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        Log.e("The Position is ",String.valueOf(position));
        ImageAdapter imageAdapter = new ImageAdapter(this);
//
//        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
//        imageView.setImageResource(imageAdapter.mThumbIds[position]);
    }

}
