package bunnycatch.com.catchthebunny.background;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammed Siddiq on 6/11/2015.
 */
public class CatchTheBunnyVolley {

    Activity activity;

   public RequestQueue queue;
    public CatchTheBunnyVolley(Activity activity) {

        this.activity = activity;
        queue = Volley.newRequestQueue(activity.getApplicationContext());
    }

//

    public JSONObject getPostDataLogin(String URL,final HashMap<String,String> params, final BackGroundObjResponse responseCallBack ){
        queue.getCache().clear();


        //Log.d("volley","response "+essiParam.toString());
        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                try {
                    arg0 = arg0.trim();
                    Log.d("data ", "remove "+arg0);
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
                Toast.makeText(activity, "Please check username and password", Toast.LENGTH_LONG).show();


                Log.e("getPostData ", "server error " + error.networkResponse + " - " + error.toString());
            }
        }) {
//            @Override
//            protected Map<String, String> getParams() {
////                HashMap<String, String> params = new HashMap<String, String>();
////                params.put("usertoken", "");
//                return params;
//            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }


        };

        int socketTimeout = 150000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);
        return  null;

    }


    public JSONObject getPostData(String URL,final HashMap<String,String> params, final BackGroundObjResponse responseCallBack ){


        //Log.d("volley","response "+essiParam.toString());
        StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                try {
                    arg0 = arg0.trim();
                    Log.d("data ", "remove "+arg0);
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
                NetworkResponse networkResponse = error.networkResponse;


            }
        }) {
            //@Override
           // protected Map<String, String> getParams() {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("usertoken", "");
            //    return params;
           // }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }


        };

        int socketTimeout = 150000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);
        return  null;

    }


    public JSONObject getPostDataLogOut(String URL,final HashMap<String,String> params, final BackGroundObjResponse responseCallBack ){


        //Log.d("volley","response "+essiParam.toString());
        StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                try {
                    arg0 = arg0.trim();
                    Log.d("data ", "remove "+arg0);
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
        }) {
            //@Override
            // protected Map<String, String> getParams() {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("usertoken", "");
            //    return params;
            // }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }


        };

        int socketTimeout = 150000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);
        return  null;

    }

    public JSONObject getPostDataRegister(String URL,final HashMap<String,String> param, final BackGroundObjResponse responseCallBack ){


        //Log.d("volley","response "+essiParam.toString());
        StringRequest sr = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {

                try {
                    arg0 = arg0.trim();
                    Log.d("data ", "remove "+arg0);
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
        }) {
            @Override
             protected Map<String, String> getParams() {
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("usertoken", "");
                return param;
             }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }


        };

        int socketTimeout = 150000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);
        return  null;

    }


    public JSONObject getPostData(String URL, HashMap<String,String> params, final BackGroundArrResponse responseCallBack ){
        SharedPreferences pref = activity.getSharedPreferences("MyPref", 0);
        final HashMap<String,String> essiParam = new HashMap<String,String>();

        queue.getCache().clear();

        if(params==null)
        {

            essiParam.put("my_user_token",pref.getString("usertoken", "no"));
        }else{
            essiParam.putAll(params);
            essiParam.put("my_user_token", pref.getString("usertoken", "no"));

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

                Log.e("getPostData ", "server error " + error.networkResponse + " - " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //HashMap<String, String> params = new HashMap<String, String>();
                //params.put("usertoken", "");
                return essiParam;
            }
        };




        Volley.newRequestQueue(activity.getApplicationContext());


        int socketTimeout = 150000;//50 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        sr.setRetryPolicy(policy);
        queue.add(sr);
        return  null;
    }





    public static interface BackGroundObjResponse{
        public void onResponse(JSONObject response);
    }

    public static interface BackGroundArrResponse{
        public void onResponse(JSONArray response);
    }

}
