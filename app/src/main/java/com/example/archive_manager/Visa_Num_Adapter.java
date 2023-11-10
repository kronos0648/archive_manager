package com.example.archive_manager;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Visa_Num_Adapter extends RecyclerView.Adapter {
    public ArrayList<Integer> arr_visa_num;


    public Visa_Num_Adapter(ArrayList<Integer> arr)
    {
        this.arr_visa_num=arr;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_visa_num;
        Button btn_minus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_visa_num=itemView.findViewById(R.id.tv_visa_num);
            btn_minus=itemView.findViewById(R.id.btn_minus);


        }
        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=li.inflate(R.layout.activity_visa_num,parent,false);
        Visa_Num_Adapter.ViewHolder vh=new Visa_Num_Adapter.ViewHolder(itemView);
        Log.d("onCreATE","DD");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Visa_Num_Adapter.ViewHolder vh=(Visa_Num_Adapter.ViewHolder)holder;
        vh.tv_visa_num.setText(Integer.toString(this.arr_visa_num.get(position)));
        vh.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr_visa_num.remove(vh.getAdapterPosition());
                notifyItemRemoved(vh.getAdapterPosition());
            }
        });
        Log.d("height",Integer.toString(holder.itemView.getLayoutParams().height));
        Log.d("onbindviewholder","DD");
    }




    @Override
    public int getItemCount() {
        if(arr_visa_num==null) return 0;
        return arr_visa_num.size();
    }


}
