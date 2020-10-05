package com.example.appmp3.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.Activity.DanhsachtheloaitheochudeMainActivity;
import com.example.appmp3.Model.ChuDe;
import com.example.appmp3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtatcacacchudeAdapter extends RecyclerView.Adapter<DanhsachtatcacacchudeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> mangchude;

    public DanhsachtatcacacchudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = mangchude.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgchude);
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewdongcacchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeMainActivity.class);
                    intent.putExtra("chude",mangchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
