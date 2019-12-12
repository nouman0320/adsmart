package com.programrabbit.adsmart.Network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.programrabbit.adsmart.MainActivity;
import com.programrabbit.adsmart.Model.Ad;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class AdManager {

    private static AdManager single_instance = null;
    public static ArrayList<Ad> advertisements = null;
    private static int currentBannerAd = 0;


    public AdManager() { }
    // static method to create instance of Singleton class
    public static AdManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new AdManager();
            advertisements = new ArrayList<>();

            advertisements.add(new Ad("Best watch for this season", "", "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MWUQ2_VW_34FR+watch-44-alum-silver-nc-5s_VW_34FR_WF_CO?wid=750&hei=712&fmt=p-jpg&qlt=80&op_usm=0.5,0.5&.v=1572037927131,1569365637670", "http://www.google.com/?q=watch"));
            advertisements.add(new Ad("New mobile phone is here", "", "https://www.91-img.com/pictures/133188-v4-oppo-f11-mobile-phone-large-1.jpg", "http://www.google.com/?q=mobile"));
            advertisements.add(new Ad("Want to play football?", "", "https://images-na.ssl-images-amazon.com/images/I/61F-Epj6A9L._SL1104_.jpg", "http://www.google.com/?q=football"));
        }
        return single_instance;
    }


    public void startAds(final Context mContext, MainActivity myActivity, final TextView tv_title, final ImageView iv_image, final ConstraintLayout constraintLayout){
        Log.d("AdManager", "startAds: I'm working.");
        if(advertisements.size() > 0){
            myActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    final int i = currentBannerAd;
                    tv_title.setText(advertisements.get(i).title);
                    Picasso.get().load(advertisements.get(i).imageUrl).into(iv_image);
                    constraintLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = advertisements.get(i).url;
                            if (!url.startsWith("http://") && !url.startsWith("https://"))
                                url = "http://" + url;
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(browserIntent);
                        }
                    });
                    if(currentBannerAd == advertisements.size()-1){
                        currentBannerAd = 0;
                    } else currentBannerAd++;
                }
            });
        }
    }



}
