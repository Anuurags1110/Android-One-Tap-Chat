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

    //private RelativeLayout rto = null ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bt = findViewById(R.id.t1);
        //r=findViewById(R.id.t);
        Lout = findViewById(R.id.touch);




        listViewR =  findViewById(R.id.listViewR);
        listViewL=findViewById(R.id.listviewL);
//        listViewL.setOnTouchListener(null);
//        listViewR.setOnTouchListener(null);
        count=1;
        listR = new ArrayList<String>();
        listL=new ArrayList<String>();
        adapterL = new ChatUserAdapter(this,R.layout.bot_bubble, listL);
        adaptorR=new ChatUserAdapter(this,R.layout.me_bubble,listR);

        //list.add("AnuBoy");

//             bt.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                     ToCall();
//                 }
//             });

        Lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToCall();
            }
        });
//        Lout.setOnTouchListener(new View.OnTouchListener()
//        {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getActionMasked();
//
////                switch(action) {
////                    case (MotionEvent.ACTION_DOWN) :
////                        Toast.makeText(getApplicationContext(),"Action was Down", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case (MotionEvent.ACTION_MOVE) :
////                        Toast.makeText(getApplicationContext(),"Action was Move", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case (MotionEvent.ACTION_UP) :
////
////                        Toast.makeText(getApplicationContext(),"Action was Up", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case (MotionEvent.ACTION_CANCEL) :
////                        Toast.makeText(getApplicationContext(),"Action was Cancelled", Toast.LENGTH_SHORT).show();
////                        return true;
////                    case (MotionEvent.ACTION_OUTSIDE) :
////                        Toast.makeText(getApplicationContext(),"Movement occurred outside bounds ", Toast.LENGTH_SHORT).show();
////                        return true;
////                    default :
////                        return true;
////                }
//
//                if (action== MotionEvent.ACTION_DOWN) {
//                    Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
//                    ToCall();
//                    return true;
//                }else if(action==MotionEvent.ACTION_MOVE){
//
//                    return false;
//                }
//                return true;
//            }
//        });

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
                            // listL.add(null);
                            // setVisible();
                            listViewR.setAdapter(adaptorR);
                            // count++;
                        }
                        if(count%2==1){
                            listL.add(chat);
                            //listR.add(null);
                            listViewL.setAdapter(adapterL);
                            //count++;
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
