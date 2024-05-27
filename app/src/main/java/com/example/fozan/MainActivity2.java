package com.example.fozan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    Button a,u,r,v,l,c;

    databasehelper maindb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        a=findViewById(R.id.add);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),addproducts.class);
                view.getContext().startActivity(intent);
            }
        });



        u=findViewById(R.id.update);
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),updateproduct.class);
                view.getContext().startActivity(intent);
            }
        });


        r=findViewById(R.id.remove);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),removeproducts.class);
                view.getContext().startActivity(intent);
            }
        });




        l=findViewById(R.id.logout);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        c=findViewById(R.id.close);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });


        v=findViewById(R.id.view);
        maindb= new databasehelper(this);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor r=maindb.viewproduct();
                if(r.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer b = new StringBuffer();
                while(r.moveToNext())
                {
                    b.append("Product ID : "+r.getString(0)+"\n");
                    b.append("Product Name : "+r.getString(1)+"\n");
                    b.append("Product Quantity : "+r.getString(2)+"\n");
                    b.append("Cost Price  : "+r.getString(3)+"\n");
                    b.append("Selling Price : "+r.getString(4)+"\n");
                    b.append("Estimated Profit : "+r.getString(5)+"\n");
                    b.append("---------------------------------------"+"\n");
                }
                showMessage("Stock Details",b.toString());
            }
        });






    }
    public void showMessage(String title,String mes){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }
}