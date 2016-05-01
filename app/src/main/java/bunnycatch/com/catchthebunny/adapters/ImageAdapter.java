package bunnycatch.com.catchthebunny.adapters;
/**
 * Created by santhanam-pc on 08-04-2016.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import bunnycatch.com.catchthebunny.R;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public   int random,randomInt;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.elephant, R.drawable.bengal_tiger,
            R.drawable.golden_jackal, R.drawable.gaur,
            R.drawable.leopard, R.drawable.snow_leopard,
            R.drawable.ridley_turtle, R.drawable.lion,
            R.drawable.rhino
    };

    public   void randomNumber(int rNo){
        randomInt = rNo;
    }
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
        //Random Number

    }

    @Override
    public int getCount()
    {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);

        int width = 90;
        int height = 90;
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
        imageView.setLayoutParams(parms);

        if(position==randomInt)
        {imageView.setVisibility(View.VISIBLE);
            imageView.setEnabled(true);}
        else
            imageView.setVisibility(View.INVISIBLE);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }

}
