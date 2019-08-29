package com.example.rumana.database;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumana.database.DataBase.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button login,btnAdd,select_all,update,delete;
    TextView username,password;
    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextView)findViewById(R.id.editText2);
        password =(TextView)findViewById(R.id.editText);

        btnAdd = findViewById(R.id.button);
        select_all = findViewById(R.id.button5);
        login = findViewById(R.id.button2);
        update = findViewById(R.id.button4);
        delete = findViewById(R.id.button3);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.addInfo(username.getText().toString(), password.getText().toString());
                Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
            }

        });

        select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List data = dbHelper.readAllInfo();
                for(int i = 0; i < data.size(); i++){
                    Log.v("Data" +i, data.get(i).toString());
                    Toast.makeText(getApplicationContext(), "Received successfully", Toast.LENGTH_LONG).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateInfo(username.getText().toString(), password.getText().toString());
                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteInfo(username.getText().toString());
                Toast.makeText(getApplicationContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        dbHelper = new DBHelper(this);
    }

    public void addInfo(View view){
        String uname = username.getText().toString();
        String pwrd = password.getText().toString();

        long newRowid = dbHelper.addInfo(uname,pwrd);

        Context context = getApplicationContext();
        CharSequence text = "Success";
        CharSequence text1 = "Fail";
        int duration =Toast.LENGTH_SHORT;
        if(newRowid > 0){
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(context,text1,duration);
            toast.show();
        }
    }
}
