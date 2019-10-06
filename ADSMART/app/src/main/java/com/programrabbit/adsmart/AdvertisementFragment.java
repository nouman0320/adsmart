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
        toReturn.add(new Advertisement("title1", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title2", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title3", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title4", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title5", "desc 1", "123", "u"));

        return toReturn;
    }

    List<Advertisement> loadPopularProduct(){
        List<Advertisement> toReturn = new ArrayList<>();
        toReturn.add(new Advertisement("title1", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title2", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title3", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title4", "desc 1", "123", "u"));
        toReturn.add(new Advertisement("title5", "desc 1", "123", "u"));

        return toReturn;
    }
}
