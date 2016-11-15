package com.example.duacomputer.helloapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;

    EditText txtName,txtEmail,txtAge;
    Button btn,btn1;
    String name, email;
    int age;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Name:");
       // ref.push().setValue("hello world");
        txt = (TextView) findViewById(R.id.value);
        txtName = (EditText) findViewById(R.id.name);
        txtEmail = (EditText) findViewById(R.id.email);
        txtAge = (EditText) findViewById(R.id.age);
        btn1 = (Button) findViewById(R.id.bb);
        btn = (Button) findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    name = txtName.getText().toString();
                    email = txtEmail.getText().toString();
                    age = Integer.parseInt( txtAge.getText().toString());

                    ref.push().setValue(new MyClass(name,email,age));



                }catch (Exception e){


                }



            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addChildEventListener(new ChildEventListener() {


                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                       MyClass ab = dataSnapshot.getValue(MyClass.class);
                        txt.setText(" ");
                        txt.setText("on child added"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");

                        Log.d("db","on child aded key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        MyClass ab = dataSnapshot.getValue(MyClass.class);
                        txt.setText("on child change"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");
                        txt.setText(" ");
                        Log.d("db",""+"key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        MyClass ab = dataSnapshot.getValue(MyClass.class);
                        txt.setText(" ");
                        txt.setText("on child remove"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");

                        Log.d("db","key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        MyClass ab = dataSnapshot.getValue(MyClass.class);
                        txt.setText(" ");
                        txt.setText("on child moved"+txt.getText().toString()+ab.getName()+" "+ab.getEmail()+" "+ab.getAge()+"\n");

                        Log.d("db","key: "+dataSnapshot.getKey()+" value "+dataSnapshot.getValue()+"");

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
