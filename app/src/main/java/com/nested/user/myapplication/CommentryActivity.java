package com.nested.user.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentryActivity extends AppCompatActivity {

    private List<Model2> Commentrylist = new ArrayList<>();
    private List<Model3> Commentrylist1 = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private RecyclerView mRecyclerView,mRecyclerView1;
    //private RecyclerView.Adapter mAdapter;
    TextView text1,text2,text3;
    private String[] teams = new String[20];
    private String[] scores= new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Live Bangla Commentary");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mRecyclerView=findViewById(R.id.recycler1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView1=findViewById(R.id.recycler2);
        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));

        //Commentrylist=new ArrayList<>();
        text1=findViewById(R.id.batting);
        text2=findViewById(R.id.bowling);
        text3=findViewById(R.id.message1);
        final MyAdapter2 adapter=new MyAdapter2(Commentrylist,this);
        final MyAdapter3 adapter1=new MyAdapter3(Commentrylist1,this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView1.setAdapter(adapter1);
        final DatabaseReference myRef = database.getReference("commentry");
        final DatabaseReference myRef3 = database.getReference("commentry1");
        final DatabaseReference myRef2 = database.getReference("score");
        myRef.addValueEventListener(new ValueEventListener() {//add listener to vales of that child

                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//snapshot holds value

                                            Commentrylist.clear();// Result will be holded Here
                                            //myRef.orderByValue();
                                            for (DataSnapshot dsp : dataSnapshot.getChildren()) {


                                                String commentry = String.valueOf(  dsp.getValue());

                                                String overs=String.valueOf(dsp.getKey());

                                                overs=overs.replace("o",".");
                                                Model2 fire = new Model2(overs,commentry);
                                                Log.d("Commentry", String.valueOf(dsp.getValue()));
                                                Log.d("overs", String.valueOf(dsp.getKey()));
                                                Commentrylist.add(fire);

                                                //populates result with current value os dspand adds it as an element to the arraylist

                                            }
                                            Collections.reverse(Commentrylist);
                                            adapter.notifyDataSetChanged();//each time value of the child is changed the adapter is notified

                                            //jst to print in the logcat
                                        }


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    }
        );
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//snapshot holds value



                int i=0;
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {


                    String team1= String.valueOf(  dsp.getKey());

                    String score1=String.valueOf(dsp.getValue());


                    Log.d("team1", String.valueOf(dsp.getValue()));
                    Log.d("score", String.valueOf(dsp.getKey()));

                    teams[i]=team1;
                    scores[i]=score1;
                    i++;

                    //populates result with current value os dspand adds it as an element to the arraylist

                }
                text1.setText(teams[1]+":\n"+teams[2]+":");
                text2.setText(scores[1]+"\n"+scores[2]);
                text3.setText(scores[0]);

                //each time value of the child is changed the adapter is notified

                //jst to print in the logcat
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef3.addValueEventListener(new ValueEventListener() {//add listener to vales of that child

                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//snapshot holds value

                                             Commentrylist1.clear();// Result will be holded Here
                                             //myRef.orderByValue();
                                             for (DataSnapshot dsp : dataSnapshot.getChildren()) {


                                                 String commentry = String.valueOf(  dsp.getValue());

                                                 String overs=String.valueOf(dsp.getKey());

                                                 overs=overs.replace("o",".");
                                                 Model3 fire = new Model3(overs,commentry);
                                                 Log.d("Commentry1", String.valueOf(dsp.getValue()));
                                                 Log.d("overs1", String.valueOf(dsp.getKey()));
                                                 Commentrylist1.add(fire);

                                                 //populates result with current value os dspand adds it as an element to the arraylist

                                             }
                                             Collections.reverse(Commentrylist1);
                                             adapter1.notifyDataSetChanged();//each time value of the child is changed the adapter is notified

                                             //jst to print in the logcat
                                         }


                                         @Override
                                         public void onCancelled(@NonNull DatabaseError databaseError) {

                                         }
                                     }
        );


    }

    @Override
    public  boolean onSupportNavigateUp()
    {onBackPressed();
        return super.onSupportNavigateUp();}



}
