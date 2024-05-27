package com.example.fozan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addproducts extends AppCompatActivity {

    EditText quan,cp,sp,id,pn;
    Button calc,add,home;
    TextView pv;
    private profitcalc pcalc;
    databasehelper mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproducts);

        pcalc=new profitcalc();

        quan=findViewById(R.id.qtxt);
        cp=findViewById(R.id.cprice);
        sp=findViewById(R.id.sprice);
        calc=findViewById(R.id.profit);
        pv=findViewById(R.id.exp);
        add=findViewById(R.id.addproduct);
        id=findViewById(R.id.idtxt);
        pn=findViewById(R.id.pntxt);

        addproducts();

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  q=quan.getText().toString();
                String c=cp.getText().toString();
                String s=sp.getText().toString();

                Double dq=Double.parseDouble(q);
                Double dc= Double.parseDouble(c);
                Double ds=Double.parseDouble(s);

                Double profit=pcalc.calcprofit(dq,dc,ds);

                pv.setText(""+profit);
                pv.setVisibility(View.VISIBLE);
            }
        });


        mdb=new databasehelper(this);






            home=findViewById(R.id.home);
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(),MainActivity2.class);
                    view.getContext().startActivity(intent);
                }
            });

    }

    public void addproducts()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pid=id.getText().toString();
                String pname=pn.getText().toString();
                String q=quan.getText().toString();
                String cprice=cp.getText().toString();
                String sprice=sp.getText().toString();
                String esp=pv.getText().toString();
                String ep=String.valueOf(esp);
                boolean insert= mdb.insertproduct(pid,pname,q,cprice,sprice,esp);
                if(insert==true)
                {
                    Intent add=new Intent(addproducts.this,addproducts.class);
                    startActivity(add);
                    Toast.makeText(addproducts.this,"Product added suceesfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(addproducts.this,"Failed to add products",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}