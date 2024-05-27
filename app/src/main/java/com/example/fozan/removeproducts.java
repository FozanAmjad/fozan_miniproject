package com.example.fozan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class removeproducts extends AppCompatActivity {

    EditText id;
    Button d,l;
    databasehelper mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removeproducts);

        id=findViewById(R.id.idtxt);
        d=findViewById(R.id.remove);
        mdb=new databasehelper(this);



            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String p=id.getText().toString();
                    Integer del=mdb.deleteproduct(p);
                    if(del>0){
                        Toast.makeText(removeproducts.this,"Delete Success",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(removeproducts.this,"Delete Un-successfull",Toast.LENGTH_SHORT).show();
                    }
                }
            });






        l=findViewById(R.id.home);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),MainActivity2.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}