package com.programrabbit.adsmart;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends FragmentActivity {


    private BottomSheetBehavior mBottomSheetBehavior;
    private Button btnMoreOptions;

    private ImageView ivSearch;

    private DrawerLayout mDrawerLayout;

    private ImageView iv_menu;

    private ConstraintLayout cl_service;
    private ConstraintLayout cl_fuel;
    private ConstraintLayout cl_price;
    private ConstraintLayout cl_rate;


    FloatingActionButton fab_help;




    private EditText et_search;


    private TextView tv_service;
    private TextView tv_fuel;
    private TextView tv_price;

    private AlertDialog progressDialog;



    ConstraintLayout cl_admin;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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


        tv_service = findViewById(R.id.tv_service);
        tv_fuel = findViewById(R.id.tv_fuel);
        tv_price = findViewById(R.id.tv_price);


        tv_service.setTypeface(custom_font);
        tv_fuel.setTypeface(custom_font);
        tv_price.setTypeface(custom_font);


        cl_service = findViewById(R.id.cl_service);
        cl_fuel = findViewById(R.id.cl_fuel);
        cl_price = findViewById(R.id.cl_price);
        cl_rate = findViewById(R.id.cl_exchange);

        View bottomSheet = findViewById(R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        btnMoreOptions = findViewById(R.id.btn_options);
        btnMoreOptions.setTypeface(custom_font);

        ivSearch = findViewById(R.id.imageViewSearch);

        mDrawerLayout = findViewById(R.id.drawer_layout);


        btnMoreOptions.setVisibility(View.GONE);


        fab_help = findViewById(R.id.fab_help);
        fab_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        iv_menu = findViewById(R.id.iv_menu);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });


        cl_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SignupActivity.this.finish();
            }
        });

        cl_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SignupActivity.this.finish();
            }
        });


        cl_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SignupActivity.this.finish();
            }
        });

        cl_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivSearch.setVisibility(View.GONE);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_HIDDEN){
                    btnMoreOptions.setVisibility(View.VISIBLE);
                    ivSearch.setVisibility(View.VISIBLE);
                }
                else if(newState == BottomSheetBehavior.STATE_COLLAPSED){
                    ivSearch.setVisibility(View.GONE);
                }
                else if(newState == BottomSheetBehavior.STATE_HALF_EXPANDED){
                    ivSearch.setVisibility(View.GONE);
                }
                else {
                    btnMoreOptions.setVisibility(View.GONE);
                    ivSearch.setVisibility(View.GONE);
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








}
