package com.example.appmp3.Service;

public class APIService {
    private static  String base_url = "https://phploc0606199912.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
