package com.example.archive_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.archive_manager.event.PlusEvent;
import com.example.archive_manager.event.PlusEventArgs;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText et_visa_num;
    private EditText et_std_num;
    private EditText et_last_num;
    private Spinner sp_std_pos;
    private Button btn_plus;
    private Button btn_generate;
    private ImageButton btn_batch;
    private RecyclerView rv_visa_num;
    private ArrayList<Integer> arr_visa_num;

    private TableDialog tableDialog;


    private Visa_Num_Adapter vna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_visa_num=findViewById(R.id.rv_visa_num);
        btn_plus=findViewById(R.id.btn_plus);
        et_visa_num=findViewById(R.id.et_visa_num);
        et_std_num=findViewById(R.id.et_std_num);

        et_last_num=findViewById(R.id.et_last_num);
        sp_std_pos=findViewById(R.id.sp_std_pos);
        btn_generate=findViewById(R.id.btn_generate);
        btn_batch=findViewById(R.id.btn_batch);
        arr_visa_num=new ArrayList<Integer>();
        LinearLayoutManager llm=new LinearLayoutManager(this);
        //아이템 간격 조절
        rv_visa_num.setLayoutManager(llm);
        rv_visa_num.addItemDecoration(new RecyclerViewDecoration(0));
        rv_visa_num.setHasFixedSize(true);

        vna=new Visa_Num_Adapter(arr_visa_num);
        rv_visa_num.setAdapter(vna);
        btn_plus.setOnClickListener(new View.OnClickListener() {

            private boolean isntExist(int num)
            {
                boolean is=false;
                for(int i=0;i<vna.arr_visa_num.size();i++)
                {
                    if(vna.arr_visa_num.get(i)==num)
                    {
                        is=true;
                        break;
                    }
                }
                return is;
            }

            @Override
            public void onClick(View v) {
                if(et_visa_num==null||et_visa_num.getText().toString().equals(""))return;
                if(et_std_num.getText()==null
                ||et_std_num.getText().toString().equals("")
                ||et_last_num.getText().toString().equals("")
                ||et_last_num.getText()==null)
                {
                    Toast.makeText(getApplicationContext(),"기준 번호와 도착 번호 입력을 완료해주세요.",Toast.LENGTH_LONG);
                    return;
                }
                if(Integer.parseInt(et_std_num.getText().toString())>Integer.parseInt(et_last_num.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"도착 번호는 기준 번호보다 크거나 같은 수여야 합니다.",Toast.LENGTH_LONG);
                    return;
                }
                int input=Integer.parseInt(et_visa_num.getText().toString());
                if(input>Integer.parseInt(et_last_num.getText().toString())
                ||input<Integer.parseInt(et_std_num.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"번호 범위 내의 수를 입력해주세요.",Toast.LENGTH_LONG);
                    return;
                }
                if(isntExist(input))return;
                vna.arr_visa_num.add(input);
                vna.notifyItemInserted(vna.arr_visa_num.size()-1);
                Log.d("attach","dsf");
            }
        });
        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();
            }
        });

        btn_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArrangement();
            }
        });

        WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
        layoutParams.flags=WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount=0.8f;
        getWindow().setAttributes(layoutParams);
    }

    private void showArrangement()
    {
        tableDialog=new TableDialog(this);
        tableDialog.show();
    }

    //알고리즘 메소드 분리

    private void generate()
    {
        int std_num=Integer.parseInt(et_std_num.getText().toString());
        int last_num=Integer.parseInt(et_last_num.getText().toString());
        int std_pos=sp_std_pos.getSelectedItemPosition();
        int row_num=(int)Math.ceil((double)((last_num-std_num-arr_visa_num.size())/(double)10))+1;
        int col_num=10;
        int[][] mat_num=new int[row_num][col_num];
        for(int i=std_pos;i<10;i++)
        {
            if(std_num>last_num)break;
            if(std_pos==10)break;
            boolean isVisaNum=false;
            for(int j=0;j<arr_visa_num.size();j++)
            {
                if(std_num==arr_visa_num.get(j))
                {
                    isVisaNum=true;
                    break;
                }
            }
            if(isVisaNum)
            {
                i--;
                std_num++;
            }
            else mat_num[0][std_pos++]=std_num++;
        }
        if(std_num<=last_num)
        {
            int current_row=1;
            while(current_row<row_num)
            {
                if(std_num>last_num)break;
                int current_col=0;
                while(current_col<10)
                {
                    if(std_num>last_num)break;
                    boolean isVisaNum=false;
                    for(int j=0;j<arr_visa_num.size();j++)
                    {
                        if(std_num>last_num)break;
                        if(std_num==arr_visa_num.get(j))
                        {
                            isVisaNum=true;
                            break;
                        }
                    }
                    if(isVisaNum)
                    {
                        current_col--;
                        std_num++;
                    }
                    else mat_num[current_row][current_col]=std_num++;
                    current_col++;
                }
                current_row++;
            }
        }

        for(int i=0;i<row_num;i++)
        {
            for(int j=0;j<col_num;j++)
            {
                System.out.println("mat_num["+i+"]["+j+"] : "+mat_num[i][j]);
            }
        }
        Intent it=new Intent(this,TableActivity.class);
        it.putExtra("row_num",row_num);
        for(int i=0;i<row_num;i++)
        {
            it.putExtra("row"+i,mat_num[i]);
        }
        startActivity(it);
    }
}