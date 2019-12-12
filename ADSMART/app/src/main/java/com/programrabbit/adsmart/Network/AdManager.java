package com.programrabbit.adsmart.Network;

import com.programrabbit.adsmart.Model.Ad;

import java.util.ArrayList;

public class AdManager {

    private static AdManager single_instance = null;
    public static ArrayList<Ad> advertisements = null;


    public AdManager() { }
    // static method to create instance of Singleton class
    public static AdManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new AdManager();
            advertisements = new ArrayList<>();
        }
        return single_instance;
    }



}
