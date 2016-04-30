package bunnycatch.com.catchthebunny.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.UnsupportedEncodingException;

import bunnycatch.com.catchthebunny.R;

/**
 * Created by user on 12/15/2015.
 */
public class Helper {

    //Helper
    static  Dialog dialogs;
    public static Boolean nullChecker(String checkName,String checkValue,String TAG){

        if(checkValue==null || checkValue.equals("")){
            Log.e(TAG, checkName + " is null while using");
            return false;
        }else{

            return true;
        }
    }
    public static Dialog PopUp(Activity activity)
    {
        if(dialogs != null &&  dialogs.isShowing()){
            dialogs.dismiss();
            dialogs = null;
        }

        try {
            if (activity != null) {
                dialogs = new Dialog(activity);
                final Animation zoomin, zoominn, zoomout, zoomoutt;

                dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogs.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialogs.setContentView(R.layout.dialog);
                //   dialogs.setCancelable(false);
                //   dialogs.setCanceledOnTouchOutside(false);
                final ImageView trizziologo1 = (ImageView) dialogs.findViewById(R.id.logo_1);
                final ImageView trizziologo_1 = (ImageView) dialogs.findViewById(R.id.logo_2);
                zoomin = AnimationUtils.loadAnimation(activity, R.anim.zoom_in);
                zoominn = AnimationUtils.loadAnimation(activity, R.anim.zoom_in);
                zoomout = AnimationUtils.loadAnimation(activity, R.anim.zoom_out);
                zoomoutt = AnimationUtils.loadAnimation(activity, R.anim.zoom_out);
                trizziologo1.startAnimation(zoomin);
               /* trizziologo_1.startAnimation(zoominn);
                trizziologo1.startAnimation(zoomout);
                trizziologo_1.startAnimation(zoomoutt);*/

                zoomin.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub
                        trizziologo_1.startAnimation(zoominn);
                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {
                    }
                });

                zoominn.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        trizziologo1.startAnimation(zoomout);
                    }
                });

                zoomout.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub
                        trizziologo_1.startAnimation(zoomoutt);
                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {
                    }
                });

                zoomoutt.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        trizziologo1.startAnimation(zoomin);
                    }
                });

                dialogs.show();
                return dialogs;
            }
        }catch (RuntimeException ex){

          //  Log.e(UrlHelper.TAG,"Hello  ::" + ex.getMessage());
        //    dialogs.dismiss();
        }
        return null;
    }
    public static void SnackBarLayout(LinearLayout parent,String message,Context context) {

        try {
if(context != null) {
    Snackbar snackbar = Snackbar
            .make(parent, message, Snackbar.LENGTH_LONG);
    View snackbarView = snackbar.getView();

    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
    textView.setGravity(Gravity.CENTER);
    textView.setTextColor(context.getResources().getColor(R.color.tab_blue));

    snackbar.show();
}
        }catch (RuntimeException ex){

        }
    }
    public static void SnackBarLayout1(RelativeLayout parent,String message,Context context) {
try {
    if (context != null) {
        Snackbar snackbar = Snackbar
                .make(parent, message, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();

        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(context.getResources().getColor(R.color.tab_blue));
        snackbar.show();
    }
}catch (RuntimeException ex){

}
    }

    public static boolean isNetworkAvailable(final Activity activity) {
        try {
            if(activity!=null){
                ConnectivityManager connectivity = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivity != null) {
                    NetworkInfo[] info = connectivity.getAllNetworkInfo();
                    if (info != null) {
                        for (int i = 0; i < info.length; i++) {
                            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                                return true;
                            }
                        }
                    }
                }
            }else{

                Log.e("Is network","activity is null");
            }


        }catch (RuntimeException ex){
            Log.e("Is network","activity is null "+ ex.getMessage());
        }
//        Toast.makeText(activity,"Pls check your internet connection",Toast.LENGTH_LONG).show();
//show dialog message


        return false;
    }
    public static Typeface Bariol_Regular(Context ctx){
        Typeface face=Typeface.createFromAsset(ctx.getAssets(), "Bariol_Regular.otf");

        return  face;
    }
    public static Typeface bariol_Bold(Context ctx){
        Typeface face=Typeface.createFromAsset(ctx.getAssets(),"Bariol_Bold.otf");

        return  face;
    }

    public static Typeface fredokaOne_Regular(Context ctx){
        Typeface face=Typeface.createFromAsset(ctx.getAssets(),"FredokaOne-Regular.ttf");

        return  face;
    }
    public static Typeface Bariol_Bold_Italic(Context ctx){
        Typeface face=Typeface.createFromAsset(ctx.getAssets(),"Bariol_Bold_Italic.otf");

        return  face;
    }

    public static String specialCharacterSupport(String text) {
        String name = "";
        try {
            name = new String(text.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.d("Error ","Text viewEncoding error");
        }
        return name;
    }
}
