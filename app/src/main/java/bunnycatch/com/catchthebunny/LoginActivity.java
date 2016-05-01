package bunnycatch.com.catchthebunny;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import bunnycatch.com.catchthebunny.background.AppController;
import bunnycatch.com.catchthebunny.background.CatchVolley;
import bunnycatch.com.catchthebunny.beans.UserDetails;
import bunnycatch.com.catchthebunny.helper.Config;

/**
 * Created by VIJAYAKUMAR MUNIAPPA on 30-04-2016.
 */
public class LoginActivity extends Activity {
    CallbackManager callbackManager;
     LoginButton loginButton;
    UserDetails myUserDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(LoginActivity.this);
        setContentView(R.layout.login_activity);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        myUserDetails = new UserDetails("Catch");

        UserDetails userDetails = UserDetails.getMyUserDetails(LoginActivity.this);
        if(userDetails != null && userDetails.getUserToken() != null){
            Intent in = new Intent(LoginActivity.this, AndroidGridLayoutActivity.class);
            startActivity(in);
            finish();
        }else {
            fbLogin();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }*/
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void fbLogin() {

        loginButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        loginButton.setReadPermissions(Arrays.asList("user"));
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        loginButton.setReadPermissions(Arrays.asList("user_about_me"));
        loginButton.setReadPermissions(Arrays.asList("user_photos"));
        loginButton.setReadPermissions(Arrays.asList("user_friends"));
       Log.e("Catch", "Fb login - inside");
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //  Log.e(TAG, "Fb login - done");
                //registerUser
//                dialog = new ProgressDialog(MainActivity_ViewPager.this);
//                dialog.setMessage("Logging In");
//                dialog.setCancelable(false);
//                dialog.show();


                Log.e("Catch","loginResult : " +loginResult);
                loginButton.setVisibility(View.INVISIBLE);


                GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject jsonObject, GraphResponse graphResponse) {
                        Log.e("Catch","loginResult : " +jsonObject);
                        HashMap<String, String> param = new HashMap<String, String>();
try {
    myUserDetails.setFbId(jsonObject.getString("id"));
    myUserDetails.setGender(jsonObject.getString("gender"));
    myUserDetails.setUserName(jsonObject.getString("first_name"));
    myUserDetails.setLastName(jsonObject.getString("last_name"));
    final String profile = "https://graph.facebook.com/" + jsonObject.getString("id") + "/picture?type=large";
    myUserDetails.setUserProfile(profile);
   // myUserDetails.setDateOfBirth(null);
  //  myUserDetails.setUserLocation(null);
   // myUserDetails.setDeviceToken(gcmRegID);

    if (jsonObject.has("email")) {
        myUserDetails.setUserEmailId(jsonObject.getString("email"));
        param.put("user_email", myUserDetails.getUserEmailId());
    } else {

        param.put("user_email", "no email");

    }
        String regUrl = Config.URL + "registerUser";
        JSONObject registerResponse = new JSONObject();
        registerResponse.put("user_first_name", myUserDetails.getUserName());
        registerResponse.put("user_second_name", myUserDetails.getLastName());
        registerResponse.put("user_fb_id", myUserDetails.getFbId());
      //  registerResponse.put("device_id", "123456789");
        //registerResponse.put("device_name", "android");


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                regUrl, registerResponse, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("registration", "success  #### " + response);
try {
    if (response.toString().contains("user_token")) {
        String token = response.getJSONObject("data").getString("user_token");
        myUserDetails.setUserToken("" + token);
        UserDetails.setMyUserDetails(LoginActivity.this, myUserDetails);
        Intent in = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}catch (JSONException ex){
    Log.d("registration", "JSONException  #### " + ex.getMessage());
}

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("registration", "errror" + error.getMessage());



                Toast.makeText(getApplicationContext(), "Please check username and password", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }
        };

        Log.e("EazyTrack ", "jsonObjReq  " + jsonObjReq);
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
//                                listener.




    CatchVolley volley = new CatchVolley(LoginActivity.this);

}catch(Exception ex){
Log.e("Catch","ex : " +ex.getMessage());
}
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email,bio,picture,gender");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
//                ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
//                dialog.setMessage("Loading");
//                dialog.setCancelable(false);
//                dialog.show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                //    Log.e(TAG, "error - " + error.getMessage());
            }
        });

    }


}
