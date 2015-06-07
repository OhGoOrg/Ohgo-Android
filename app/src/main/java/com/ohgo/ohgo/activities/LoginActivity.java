package com.ohgo.ohgo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ohgo.ohgo.R;
import com.ohgo.ohgo.models.Employee;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class LoginActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final EditText etUser = (EditText) findViewById(R.id.etUsername);
        final EditText etPass = (EditText) findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<Employee> query = ParseQuery.getQuery("Employee");
                query.whereEqualTo("name", etUser.getText().toString());
                query.findInBackground(new FindCallback<Employee>() {
                    @Override
                    public void done(List<Employee> employees, ParseException e) {
                        if(employees != null && employees.size()>0 ){
                            Employee employ = employees.get(0);
                            if(employ.getPassword().equals(etPass.getText().toString())){
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("employee", employ.getObjectId());
                                startActivity(intent);

                            }else{
                                Toast.makeText(getApplicationContext(),"Incorrect username or Password",Toast.LENGTH_SHORT).show();
                            }


                        }else {

                            Toast.makeText(getApplicationContext(),"Incorrect username or Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });


    }


}
