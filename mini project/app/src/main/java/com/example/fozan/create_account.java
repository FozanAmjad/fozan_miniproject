package com.example.fozan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class create_account extends AppCompatActivity {

    EditText fulln,lastn,em,phone,passw;
    Button b;
    accountdatabase adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fulln=findViewById(R.id.firstname);
        lastn=findViewById(R.id.secondname);
        em=findViewById(R.id.email);
        phone=findViewById(R.id.phoneNUMBER);
        passw=findViewById(R.id.password);
        b=findViewById(R.id.create);

        adb=new accountdatabase(this);
        createacc();
    }
    public void createacc(){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn=fulln.getText().toString();
                String ln=lastn.getText().toString();
                String e=em.getText().toString();
                String phn=phone.getText().toString();
                String p=passw.getText().toString();
                boolean insert=adb.createacc(fn,ln,e,phn,p);
                if(insert==true)
                {
                    Toast.makeText(create_account.this,"Account has been Created Successfully", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(create_account.this,MainActivity.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(create_account.this,"Employee is already existing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}