package com.programrabbit.adsmart.Network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.programrabbit.adsmart.FacebookLoginActivity;
import com.programrabbit.adsmart.R;
import com.programrabbit.adsmart.TwitterLoginActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;
import com.twitter.sdk.android.core.services.StatusesService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

public class UserData {

    private static UserData single_instance = null;

    private static String userEmail = "";

    private static JSONArray textArray;

    private static final String SERVER_IP = "192.168.0.104";
    private static final String SERVER_PORT = "51488";


    private static final String API_URL = "http://"+SERVER_IP+":"+SERVER_PORT+"/api/";


    public UserData() {
    }

    // static method to create instance of Singleton class
    public static UserData getInstance()
    {
        if (single_instance == null)
            single_instance = new UserData();

        return single_instance;
    }

    // method to fetch facebook
    public void startFetchingFacebook(final Context context){
        // first fetching data from facebook

        /*
        GraphRequest.Callback gCallback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                JSONObject fbJson = response.getJSONObject();
            }
        };*/

        final GraphRequest fbReq = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "me?fields=email,posts.limit(99)",
                null,
                HttpMethod.GET,
                null
        );

        fbReq.executeAsync();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                GraphResponse gResponse = fbReq.executeAndWait();
                try {
                    if(gResponse.getError() != null && gResponse.getError().getErrorCode() == 2500){
                        Log.d("fb_call", "user not logged in");
                        Intent i = new Intent(context, FacebookLoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                        return;
                    }


                    Log.d("fb_call", gResponse.toString());

                    JSONObject fbJson = gResponse.getJSONObject();
                    try {
                        userEmail = fbJson.get("email").toString();
                        Log.d("fb_call", userEmail);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        JSONObject postObj = fbJson.getJSONObject("posts");
                        JSONArray postArr = postObj.getJSONArray("data");

                        Log.d("fb_call", postObj.toString());

                        if(textArray == null){
                            textArray = new JSONArray();
                        }

                        for (int i = 0; i < postArr.length(); i++) {
                            JSONObject post = postArr.getJSONObject(i);

                            Log.d("fb_call", post.toString());

                            JSONObject toPut = new JSONObject();
                            if(!post.isNull("message")){
                                toPut.put("Text", post.getString("message"));
                                textArray.put(toPut);
                                Log.d("fb_call", post.getString("message"));
                            }
                        }
                        //facebookJSON.put("Data", textArray);

                        startFetchingTwitter(context);

                        //Log.d("test1", facebookJSON.toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    //Log.e("fb_call", e.toString() );
                }
            }
        });
        t.start();

    }

    public void startFetchingTwitter(final Context context){
        TwitterConfig config = new TwitterConfig.Builder(context)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(context.getString(R.string.CONSUMER_KEY), context.getString(R.string.CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);

        Log.d("twitter1", "twitter method");

        if(TwitterCore.getInstance().getSessionManager().getActiveSession() == null){
            Log.d("twitter1", "user not logged in");
            Intent i = new Intent(context, TwitterLoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            return;
        }


        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        statusesService
                .userTimeline(TwitterCore
                                .getInstance()
                                .getSessionManager()
                                .getActiveSession()
                                .getUserId(),
                        null, null, null, null, null, null, null, null)
                .enqueue(new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        if(textArray == null){
                            textArray = new JSONArray();
                        }
                        for(int i=0; i < result.data.size(); i++){
                            Log.d("twitter1", result.data.get(i).text);
                            JSONObject toPut = new JSONObject();
                            try {
                                toPut.put("Text", result.data.get(i).text);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            textArray.put(toPut);
                        }

                        startSendingToApi(context);
                    }

                    @Override
                    public void failure(TwitterException exception) {

                    }
                });
    }


    public void startFetchingUserData(final Context context){
        textArray = null;
        this.startFetchingFacebook(context);
        //this.startFetchingTwitter(context);
    }

    private void startSendingToApi(final Context context){
        JSONObject toSend = new JSONObject();
        try {
            toSend.put("Email", userEmail);
            toSend.put("Data", textArray);
            Log.d("twitter2", toSend.toString());
            sendToApi(toSend, context);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textArray = null;
    }

    private void sendToApi(JSONObject toSend, final Context context){
        RequestQueue requstQueue = Volley.newRequestQueue(context);

        Log.d("adsmart", "sendToApi: "+toSend.toString());
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, this.API_URL+"post",toSend,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /*
                        if(mResultCallback != null){
                            mResultCallback.notifySuccess(response);
                        }*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("adsmart", "onErrorResponse: "+error.toString());
                    }
                }
        );
        requstQueue.add(jsonobj);
    }


}
