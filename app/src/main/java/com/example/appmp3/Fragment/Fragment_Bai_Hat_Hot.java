package com.example.appmp3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.Adapter.BaihathotAdpter;
import com.example.appmp3.Model.Baihat;
import com.example.appmp3.R;
import com.example.appmp3.Service.APIService;
import com.example.appmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewbaihathot;
    BaihathotAdpter baihathotAdpter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_bai_hat_thich_nhat,container,false);
        recyclerViewbaihathot = view.findViewById(R.id.recyclerviewbaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback =dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baihathotAdpter =new BaihathotAdpter(getActivity(),baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewbaihathot.setAdapter(baihathotAdpter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
