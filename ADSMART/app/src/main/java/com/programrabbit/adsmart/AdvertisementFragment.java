package com.programrabbit.adsmart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdvertisementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdvertisementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdvertisementFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    List<Advertisement> pForYou;
    List<Advertisement> pPopular;

    public AdvertisementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdvertisementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdvertisementFragment newInstance(String param1, String param2) {
        AdvertisementFragment fragment = new AdvertisementFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        pForYou = loadProductForYou();
        pPopular = loadPopularProduct();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advertisement, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdvertisementAdapter firstAdapter = new AdvertisementAdapter(getContext(), pForYou);
        MultiSnapRecyclerView firstRecyclerView = view.findViewById(R.id.first_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);


        AdvertisementAdapter secondAdapter = new AdvertisementAdapter(getContext(), pPopular);
        MultiSnapRecyclerView secondRecyclerView = view.findViewById(R.id.second_recycler_view);
        LinearLayoutManager secondManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(secondManager);
        secondRecyclerView.setAdapter(secondAdapter);

    }

    List<Advertisement> loadProductForYou(){
        List<Advertisement> toReturn = new ArrayList<>();
        toReturn.add(new Advertisement("Apple iPhone XS Max - QHD+ Notch Display - Face ID - Dual sim (HK) With FaceTime PTA APPROVED", "", "Rs. 229,999", "https://static-01.daraz.pk/p/aca8cabb1f87c24a9c38901cf4720d02.jpg"));
        toReturn.add(new Advertisement("New Original Apple Watch Series 5 Space Gray Aluminum Case Black Sport Band (GPS 44mm )", "", "Rs. 89,000", "https://techcrunch.com/wp-content/uploads/2018/09/IMG_3293.jpg?w=730&crop=1"));
        toReturn.add(new Advertisement("Sony Extra Bass MDR-XB650BT Headphones", "", "Rs. 22,000", "http://www.comparebaazar.com/wp-content/uploads/2018/06/Sony-Extra-Bass-MDR-XB650BT-Headphones-price.jpg"));
        toReturn.add(new Advertisement("Original Apple AirPods 2 Wireless Charging Case Bluetooth Headset White - Non Active 1 Year Apple Care International Warranty", "", "Rs. 49,0000", "https://media.idownloadblog.com/wp-content/uploads/2019/06/iOS-13-AirPods-notifications-banner.jpg"));
        toReturn.add(new Advertisement("iTunes Card - 50 Dollars", "Give the gift of one-stop entertainment, to all the Apple-lovers out there.", "Rs. 7,989", "https://www.computercaredubai.ae/media/catalog/product/cache/1/image/700x/040ec09b1e35df139433887a97daa66f/a/c/accappleitune50.png"));

        return toReturn;
    }

    List<Advertisement> loadPopularProduct(){
        List<Advertisement> toReturn = new ArrayList<>();
        toReturn.add(new Advertisement("Galaxy Note 9 - 6.4\" - 6Gb Ram - 128Gb Rom - Dual Sim - Ocean Blue", "", "Rs. 149,000", "https://cnet3.cbsistatic.com/img/9ZuQOnOyKtMTzVm-GnTVtZwMmds=/2018/08/15/cfdf138b-06fc-42bf-b61f-724f248f05a9/samsung-galaxy-note-9-use-1161.jpg"));
        toReturn.add(new Advertisement("W11+ PUBG Gamepad PUBG Controller With 2 Builtin Triggers", "", "RS. 500", "https://5.imimg.com/data5/RE/QA/MY-40309194/metal-pubg-game-controller-joystick-500x500.jpeg"));
        toReturn.add(new Advertisement("Pack Of 6 Rooh Afza Go- 250ML", "", "Rs. 270", "https://images.khaleejtimes.com/storyimage/KT/20190508/ARTICLE/190509304/AR/0/AR-190509304.jpg"));
        toReturn.add(new Advertisement("Cat Food", "", "Rs. 510", "https://i.ytimg.com/vi/kcOqug5_M1I/maxresdefault.jpg"));
        toReturn.add(new Advertisement("Supreme x Nike Air MAX 270 University Red White Black Running Shoes AH8050-610", "", "Rs. 11,000", "https://www.febshoes.com/media/x490/Nike_Air_Max_Shoes/Air_Max_270/Supreme_x_Nike_Air_MAX_270_University_Red_White_Black_Running_Shoes_AH8050-610.jpg"));
        return toReturn;
    }
}
