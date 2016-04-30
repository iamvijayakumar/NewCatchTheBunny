package bunnycatch.com.catchthebunny.background;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bunnycatch.com.catchthebunny.R;
import bunnycatch.com.catchthebunny.beans.UserDetails;
import bunnycatch.com.catchthebunny.helper.Helper;

/**
 * Created by Mohammed Siddiq on 6/11/2015.
 */
public class CatchVolley {

    Activity activity;
     Dialog exitQuestion;
    public static RequestQueue queue;
    public CatchVolley(Activity activity) {

        this.activity = activity;
        DiskBasedCache cache = new DiskBasedCache(activity.getCacheDir(), 16 * 1024 * 1024);
            queue = Volley.newRequestQueue(activity.getApplicationContext());

    }



    public JSONObject getPostData(String URL,final HashMap<String,String> params, final BackGroundObjResponse responseCallBack ){

        if(Helper.isNetworkAvailable(activity)){



        }else{

            if(exitQuestion == null ){


              exitQuestion=new Dialog(activity);

            exitQuestion.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        exitQuestion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            exitQuestion.setContentView(R.layout.custom_timeout_error);

            exitQuestion.setCancelable(true);
            exitQuestion.getWindow().getAttributes().windowAnimations= R.style.DialogAnimation;
            Button okBtn = (Button)exitQuestion.findViewById(R.id.yes);

            TextView error_msg = (TextView)exitQuestion.findViewById(R.id.error_msg);
            error_msg.setText("Please check your internet connection!");
            okBtn.setText("Go to settings");
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if(exitQuestion != null && exitQuestion.isShowing()){
                            exitQuestion.dismiss();
                        }
                        exitQuestion = null;
                        activity.startActivity(new Intent(Settings.ACTION_SETTINGS));
                    } catch (Exception e) {
                        Log.e("getPostData", "error " + e.getMessage());
                    }
                }
            });
            exitQuestion.show();
            }
            return  null;
        }

        UserDetails userDetails = UserDetails.getMyUserDetails(activity);

        final HashMap<String, String> yomeParam = new HashMap<String, String>();
        if (userDetails != null && userDetails.getUserToken()!=null) {
            if (params == null) {
                yomeParam.put("post_user_token", userDetails.getUserToken());
            } else {

                yomeParam.putAll(params);
                yomeParam.put("post_user_token", userDetails.getUserToken());

            }
        } else {
            yomeParam.putAll(params);
        }

        Log.d("volley", "response " + yomeParam.toString());
        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                try {
                    arg0 = arg0.trim();
                  //  Log.d("data ", "remove " + arg0);
                    responseCallBack.onResponse(new JSONObject(arg0));
                 //   Log.d("data ", arg0);
                } catch (Exception e) {

                    Log.e("data ", arg0);
                    Log.e("getPostDataObj", "error " + e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(!Helper.isNetworkAvailable(activity)){

                    if(exitQuestion == null ) {
                        exitQuestion = new Dialog(activity);

                        exitQuestion.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        exitQuestion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        exitQuestion.setContentView(R.layout.custom_timeout_error);

                        exitQuestion.setCancelable(true);
                        exitQuestion.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                        Button okBtn = (Button) exitQuestion.findViewById(R.id.yes);

                        TextView error_msg = (TextView) exitQuestion.findViewById(R.id.error_msg);
                        error_msg.setText("Please check your internet connection!");

                        okBtn.setText("Go to settings");
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    if (exitQuestion != null && exitQuestion.isShowing()) {
                                        exitQuestion.dismiss();

                                    }

                                    exitQuestion = null;
                                    activity.startActivity(new Intent(Settings.ACTION_SETTINGS));
                                } catch (Exception e) {
                                    Log.e("getPostData", "error " + e.getMessage());
                                }
                            }
                        });
                        exitQuestion.show();
                    }

                }else{
                if(exitQuestion == null  ) {
                    exitQuestion = new Dialog(activity);
                    exitQuestion.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        exitQuestion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    exitQuestion.setContentView(R.layout.custom_timeout_error);
                    exitQuestion.setCancelable(true);
                    exitQuestion.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                    Button okBtn = (Button) exitQuestion.findViewById(R.id.yes);

                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                if(exitQuestion != null && exitQuestion.isShowing()){
                                    exitQuestion.dismiss();

                                }
                                exitQuestion = null;
                            } catch (Exception e) {
                                Log.e("getPostData", "error " + e.getMessage());
                            }
                        }
                    });
                    exitQuestion.show();
                }}
                Log.e("getPostData ", "server error " + error.networkResponse + " - " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("usertoken", "");
                return yomeParam;
            }
        };

        int socketTimeout = 80000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);

        return  null;

    }

    public JSONObject getPostDataGeo(String URL, final BackGroundObjResponse responseCallBack ){





        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                try {

                    responseCallBack.onResponse(new JSONObject(arg0));
                    Log.d("data ", arg0);
                } catch (Exception e) {

                    Log.e("data ", arg0);
                    Log.e("getPostDataObj", "error " + e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("getPostData ", "server error " + error.networkResponse + " - " + error.toString());
            }
        });
//        {
//            @Override
//            protected Map<String, String> getParams() {
////                HashMap<String, String> params = new HashMap<String, String>();
////                params.put("usertoken", "");
//                return yomeParam;
//            }
//        };

        int socketTimeout = 80000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);
        return  null;

    }



    public JSONObject getPostData(String URL, HashMap<String,String> params, final BackGroundArrResponse responseCallBack ){
//        if(Helper.isNetworkAvailable(activity)) {


        if(Helper.isNetworkAvailable(activity)){



        }else{

            if(exitQuestion == null ) {
                exitQuestion = new Dialog(activity);

                exitQuestion.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        exitQuestion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                exitQuestion.setContentView(R.layout.custom_timeout_error);

                exitQuestion.setCancelable(true);
                exitQuestion.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                Button okBtn = (Button) exitQuestion.findViewById(R.id.yes);

                TextView error_msg = (TextView) exitQuestion.findViewById(R.id.error_msg);
                error_msg.setText("Please check your internet connection!");

                okBtn.setText("Go to settings");
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if(exitQuestion != null && exitQuestion.isShowing()){
                                exitQuestion.dismiss();

                            }

                            exitQuestion = null;
                            activity.startActivity(new Intent(Settings.ACTION_SETTINGS));
                        } catch (Exception e) {
                            Log.e("getPostData", "error " + e.getMessage());
                        }
                    }
                });
                exitQuestion.show();
            }
            return  null;
        }

        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        final HashMap<String, String> yomeParam = new HashMap<String, String>();

        if (params == null)

        {

            yomeParam.put("my_user_token", pref.getString("usertoken", "no"));
        } else {
            yomeParam.putAll(params);
            yomeParam.put("my_user_token", pref.getString("usertoken", "no"));

        }

        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                try {

                    responseCallBack.onResponse(new JSONArray(arg0));

                } catch (Exception e) {

                    Log.e("getPostDataArr", "error " + e.getMessage());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(!Helper.isNetworkAvailable(activity)){

                    if(exitQuestion == null ) {
                        exitQuestion = new Dialog(activity);

                        exitQuestion.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        exitQuestion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        exitQuestion.setContentView(R.layout.custom_timeout_error);

                        exitQuestion.setCancelable(true);
                        exitQuestion.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                        Button okBtn = (Button) exitQuestion.findViewById(R.id.yes);

                        TextView error_msg = (TextView) exitQuestion.findViewById(R.id.error_msg);
                        error_msg.setText("Please check your internet connection!");

                        okBtn.setText("Go to settings");
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    if (exitQuestion != null && exitQuestion.isShowing()) {
                                        exitQuestion.dismiss();

                                    }

                                    exitQuestion = null;
                                    activity.startActivity(new Intent(Settings.ACTION_SETTINGS));
                                } catch (Exception e) {
                                    Log.e("getPostData", "error " + e.getMessage());
                                }
                            }
                        });
                        exitQuestion.show();
                    }

                }else{
                if(exitQuestion == null  ) {
                    exitQuestion = new Dialog(activity);

                    exitQuestion.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        exitQuestion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    exitQuestion.setContentView(R.layout.custom_timeout_error);

                    exitQuestion.setCancelable(true);
                    exitQuestion.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                    Button okBtn = (Button) exitQuestion.findViewById(R.id.yes);

                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                if (exitQuestion != null && exitQuestion.isShowing()) {
                                    exitQuestion.dismiss();

                                }
                                exitQuestion = null;
                            } catch (Exception e) {
                                Log.e("getPostData", "error " + e.getMessage());
                            }
                        }
                    });
                    exitQuestion.show();
                }
                }
                Log.e("getPostData ", "server error " + error.networkResponse + " - " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("usertoken", "");
                return yomeParam;
            }

        };


        int socketTimeout = 80000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);
//        }
        return  null;
    }



    public static interface BackGroundObjResponse{
        public void onResponse(JSONObject response);
    }

    public static interface BackGroundArrResponse{
        public void onResponse(JSONArray response);
    }

}
