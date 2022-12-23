package com.example.myapplication;

import android.net.Uri;

import java.util.ArrayList;


public class Official {
    String people_name;
    String position_name;
    String city;
    String stay;
    String zip;

    public Official() {
        String people_name;
        String position_name;
        stay = "";
        zip = "";
    }

    public Official(String _people_name, String _position_name, String _city, String _stay, String _zip){
        people_name = _people_name;
        position_name = _position_name;
        city = _city;
        stay = _stay;
        zip = _zip;
    }
}
