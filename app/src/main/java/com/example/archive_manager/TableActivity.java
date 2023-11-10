package com.example.archive_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private TextView[] tv_pos;
    private TextView tv_sajeung;
    private TextView tv_row;
    private Button btn_left,btn_right;
    private Intent it;
    private int row_num;
    private int[][] mat_num;

    private int current_row;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        tv_pos=new TextView[10];
        tv_pos[0]=findViewById(R.id.pos_1);
        tv_pos[1]=findViewById(R.id.pos_2);
        tv_pos[2]=findViewById(R.id.pos_3);
        tv_pos[3]=findViewById(R.id.pos_4);
        tv_pos[4]=findViewById(R.id.pos_5);
        tv_pos[5]=findViewById(R.id.pos_6);
        tv_pos[6]=findViewById(R.id.pos_7);
        tv_pos[7]=findViewById(R.id.pos_8);
        tv_pos[8]=findViewById(R.id.pos_9);
        tv_pos[9]=findViewById(R.id.pos_10);
        tv_sajeung=findViewById(R.id.tv_sajeung);
        tv_row=findViewById(R.id.tv_row);
        btn_left=findViewById(R.id.btn_left);
        btn_right=findViewById(R.id.btn_right);
        it=getIntent();
        Bundle bundle=it.getExtras();
        row_num=bundle.getInt("row_num");
        mat_num=new int[row_num][10];
        for(int i=0;i<row_num;i++)
        {
            int[] temp=bundle.getIntArray("row"+i);
            for(int j=0;j<10;j++)
            {
                mat_num[i][j]=temp[j];
            }
        }
        current_row=0;
        updateRow();

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLeft();
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRight();
            }
        });
    }

    private void updateRow()
    {
        for(int i=0;i<10;i++)
        {
            if(mat_num[current_row][i]==0)
            {
                tv_pos[i].setText("X");
                continue;
            }
            tv_pos[i].setText(Integer.toString(mat_num[current_row][i]));
            tv_row.setText(String.format("%d / %d",current_row+1,row_num));

        }
        int i=0;
        while(mat_num[current_row][i]==0)
        {
            i++;
        }
        int min=mat_num[current_row][i];
        int max=mat_num[current_row][9];
        int cursor=min;
        int cur_idx=i;
        String str_sajeung="사증: ";
        while(cursor<=max)
        {
            if(cursor==mat_num[current_row][cur_idx])
            {
                cursor++;
                cur_idx++;
            }
            else
            {
                str_sajeung=str_sajeung+Integer.toString(cursor)+", ";
                cursor++;
            }

        }
        if(str_sajeung.equals("사증: "))
        {
            str_sajeung+="X";
        }
        else
        {
            str_sajeung=str_sajeung.substring(0,str_sajeung.length()-2);
        }
        tv_sajeung.setText(str_sajeung);

    }

    private void goLeft()
    {
        if(current_row==0)return;
        current_row--;
        updateRow();


    }

    private void goRight()
    {
        if(current_row==row_num-1)return;
        current_row++;
        updateRow();
    }
}
