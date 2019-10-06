package com.programrabbit.adsmart;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;


import androidx.fragment.app.FragmentActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends FragmentActivity implements FeedFragment.OnFragmentInteractionListener, AdvertisementFragment.OnFragmentInteractionListener {


    private BottomSheetBehavior mBottomSheetBehavior;
    private Button btnMoreOptions;



    private DrawerLayout mDrawerLayout;

    //private ImageView iv_menu;

    private ConstraintLayout cl_facebook;
    private ConstraintLayout cl_twitter;
    private ConstraintLayout cl_pref;
    private ConstraintLayout cl_ad;






    private EditText et_search;


    private TextView tv_facebook;
    private TextView tv_ad;
    private TextView tv_twitter;

    private AlertDialog progressDialog;


    FeedFragment feed_frag;
    AdvertisementFragment ad_frag;

    ConstraintLayout cl_admin;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle bundle = new Bundle();
        bundle.putString("open", "facebook");
        feed_frag = new FeedFragment();
        feed_frag.setArguments(bundle);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, feed_frag)
                .commit();

        et_search = findViewById(R.id.et_search);
        et_search.setFocusable(true);
        //et_search.setSelected(false);


        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    //Toast.makeText(MainActivity.this, "it's not working as expected.. it will show the markers on map", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/MYRIADPRO-REGULAR.OTF");


        tv_facebook = findViewById(R.id.tv_facebook);
        tv_ad = findViewById(R.id.tv_ad);
        tv_twitter = findViewById(R.id.tv_twitter);


        tv_facebook.setTypeface(custom_font);
        tv_ad.setTypeface(custom_font);
        tv_twitter.setTypeface(custom_font);


        cl_facebook = findViewById(R.id.cl_facebook);
        cl_ad = findViewById(R.id.cl_ad);
        cl_twitter = findViewById(R.id.cl_twitter);
        cl_pref = findViewById(R.id.cl_pref);

        View bottomSheet = findViewById(R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        btnMoreOptions = findViewById(R.id.btn_options);
        btnMoreOptions.setTypeface(custom_font);



        mDrawerLayout = findViewById(R.id.drawer_layout);


        btnMoreOptions.setVisibility(View.GONE);





        //iv_menu = findViewById(R.id.iv_menu);

        /*
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
        */

        cl_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("open", "facebook");
                feed_frag = new FeedFragment();
                feed_frag.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, feed_frag)
                        .commit();
            }
        });

        cl_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("open", "twitter");
                feed_frag = new FeedFragment();
                feed_frag.setArguments(bundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, feed_frag)
                        .commit();
            }
        });


        cl_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad_frag = new AdvertisementFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, ad_frag)
                        .commit();
            }
        });

        cl_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_HIDDEN){
                    btnMoreOptions.setVisibility(View.VISIBLE);

                }
                else if(newState == BottomSheetBehavior.STATE_COLLAPSED){

                }
                else if(newState == BottomSheetBehavior.STATE_HALF_EXPANDED){

                }
                else {
                    btnMoreOptions.setVisibility(View.GONE);

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

        btnMoreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });




        /*
        View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
// position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        rlp.setMargins(0, 360, 220, 0);*/








    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
