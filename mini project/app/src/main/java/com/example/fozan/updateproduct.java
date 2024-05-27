package com.example.fozan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateproduct extends AppCompatActivity {

    Button up,l;
    EditText id, sp;
    databasehelper mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateproduct);

        l=findViewById(R.id.home);
        l=findViewById(R.id.home);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),MainActivity2.class);
                view.getContext().startActivity(intent);
            }
        });


        up=findViewById(R.id.update);
        id=findViewById(R.id.idtxt);
        sp=findViewById(R.id.sprice);

        mdb=new databasehelper(this);
        updateproduct();
    }

    public void updateproduct(){
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=id.getText().toString();
                String usp=sp.getText().toString();
                boolean update = mdb.updateCostPrice(uid,usp);
                if(update==true){
                    Toast.makeText(updateproduct.this, "Selling Price Update updated Successfully", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(updateproduct.this, "Selling Price Unable to Update", Toast.LENGTH_LONG).show();
            }
        });
    }
}