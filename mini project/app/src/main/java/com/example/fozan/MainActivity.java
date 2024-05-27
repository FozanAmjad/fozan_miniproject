package com.example.fozan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText phn,pass;
    Button btn;
    TextView c;
    accountdatabase adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phn=findViewById(R.id.idtxt);
        pass=findViewById(R.id.passtxt);
        btn=findViewById(R.id.button);


        c=findViewById(R.id.register);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, create_account.class);
                startActivity(intent);
            }
        });

        adb=new accountdatabase(this);
        loginaccount();

    }
    public void loginaccount(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=phn.getText().toString();
                String p=pass.getText().toString();

                    try{
                        String rp=adb.loginaccount(id);
                        if (p.equals(rp))
                        {
                            Toast.makeText(MainActivity.this,"WELCOME",Toast.LENGTH_SHORT).show();
                            phn.setText("");
                            pass.setText("");
                            Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
}