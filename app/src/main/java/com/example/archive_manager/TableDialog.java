package com.example.archive_manager;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class TableDialog extends Dialog {
    public TableDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_help);
        TextView[] tv_pos=new TextView[10];
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
        TextView tv_info=findViewById(R.id.tv_info);
        DisMissListener dml=new DisMissListener();
        for(TextView tv : tv_pos)
        {
            tv.setOnClickListener(dml);
        }
        tv_info.setOnClickListener(dml);

    }

    class DisMissListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }
}
