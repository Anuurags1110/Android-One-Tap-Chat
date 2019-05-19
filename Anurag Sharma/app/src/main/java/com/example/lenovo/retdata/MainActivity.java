package com.example.lenovo.retdata;

import android.annotation.TargetApi;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.lenovo.reteivingdata.ChatUserAdapter;
import com.example.lenovo.reteivingdata.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listViewR;
    ListView listViewL;
    RelativeLayout Lout ;

    FirebaseDatabase datatbase;
    DatabaseReference ref;
    private int count;
    Button bt ;

    ArrayList<String> listR;
    ArrayList<String> listL;
    ArrayAdapter<String> adaptorR;
    ArrayAdapter<String> adapterL;
    ArrayAdapter<String> adapterH;
    private RelativeLayout r;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lout = findViewById(R.id.touch);




        listViewR =  findViewById(R.id.listViewR);
        listViewL=findViewById(R.id.listviewL);

        count=1;
        listR = new ArrayList<String>();
        listL=new ArrayList<String>();
        adapterL = new ChatUserAdapter(this,R.layout.bot_bubble, listL);
        adaptorR=new ChatUserAdapter(this,R.layout.me_bubble,listR);


        Lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToCall();
            }
        });


    }


    public void ToCall() {

        try{datatbase = FirebaseDatabase.getInstance();}
        catch(Exception e){
            Toast.makeText(this,"Network Connection Failed", Toast.LENGTH_SHORT).show();
        }

        ref = datatbase.getReference().child("Chat2");


        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (count < 21) {
                    for (int i = count; i ==count; i++) {
                        String chat = dataSnapshot.child(Integer.toString(count)).getValue().toString();

                        if (count % 2 == 0) {
                            listR.add(chat);

                            listViewR.setAdapter(adaptorR);

                        }
                        if(count%2==1){
                            listL.add(chat);

                            listViewL.setAdapter(adapterL);

                        }


                    }
                    count = count + 1;
                }else {
                    Toast.makeText(getApplicationContext(),"The End",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"You are Offline",Toast.LENGTH_LONG).show();
            }

        });





    }


}
