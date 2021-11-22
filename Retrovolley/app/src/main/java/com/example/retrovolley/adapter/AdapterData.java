package com.example.retrovolley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrovolley.R;
import com.example.retrovolley.model.DataModel;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    //membuat variable
    private Context ctx;
    private List<DataModel>listData;

    //membuat constructor
    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    //inflate data dari card ke recyclerview
    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,
                parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    //meletakkan setiap textview
    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position); //set data berdasarkan posisi

        holder.tv_id.setText (String.valueOf(dm.getId()));
        holder.tv_fullname.setText(dm.getUser_fullname());
        holder.tv_email.setText(dm.getUser_email());
        holder.tv_password.setText(dm.getUser_password());
    }

    //melihat berapa banyak data yang ditarik
    @Override
    public int getItemCount() {
        return listData.size();
    }

    //membuat class holderdata untuk mengextendkan class adapter data ke recyclerview
    public class HolderData extends RecyclerView.ViewHolder {
        TextView tv_id, tv_fullname, tv_email, tv_password;

        //constructor
        public HolderData(@NonNull View itemView) {
            super(itemView);

            //menemukan textview yang ada pada card
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_password = itemView.findViewById(R.id.tv_password);

        }
    }
}
