package com.programrabbit.adsmart.Network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.programrabbit.adsmart.FacebookLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserData {

    private static UserData single_instance = null;

    private static String userEmail = "";

    private static JSONArray textArray;


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


                    //Log.d("fb_call", gResponse.toString());

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

                        if(textArray == null){
                            textArray = new JSONArray();
                        }

                        for (int i = 0; i < postArr.length(); i++) {
                            JSONObject post = postArr.getJSONObject(i);

                            JSONObject toPut = new JSONObject();
                            textArray.put(toPut);

                            toPut.put("Text", post.getString("message"));
                            Log.d("fb_call", post.getString("message"));
                        }
                        //facebookJSON.put("Data", textArray);


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

    }

    public void startFetchingUserData(final Context context){
        this.startFetchingFacebook(context);
        this.startFetchingTwitter(context);
    }
}
