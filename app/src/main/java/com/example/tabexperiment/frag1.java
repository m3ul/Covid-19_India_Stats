package com.example.tabexperiment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class frag1 extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    private static RecyclerView recyclerView;

    FirebaseDatabase mFirebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference mReference=mFirebaseDatabase.getReference("statewise");
    DatabaseReference mReference2=mFirebaseDatabase.getReference("cases_time_series");


    LineChart mLineChart;


    private int i=0;
    boolean was=false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment1, container, false);
       // mLineChart = (LineChart) view.findViewById(R.id.chart_main);


//        mReference2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ArrayList<Entry> confirmed=new ArrayList<Entry>();
//                ArrayList<Entry> recovered=new ArrayList<Entry>();
//                ArrayList<Entry> deaths=new ArrayList<Entry>();
//
//                ArrayList dateVals=new ArrayList();
//
//
//                for(DataSnapshot mDataSnapshot:dataSnapshot.getChildren()){
//                    YourData data=mDataSnapshot.getValue(YourData.class);
//                    confirmed.add(new Entry(i,Integer.parseInt(data.getTotalconfirmed())));
//                    recovered.add(new BarEntry(i,Integer.parseInt(data.getTotalrecovered())));
//                    deaths.add(new BarEntry(i,Integer.parseInt(data.getTotaldeaths())));
//                    dateVals.add(data.getDate());
//                    i++;
//                }
//                LineDataSet dataSet1 = new LineDataSet(confirmed, "Total Confirmed");
//                setDatasetProperties1(dataSet1);
//                LineDataSet dataSet2 = new LineDataSet(recovered, "Total Cured");
//                setDatasetProperties2(dataSet2);
//                LineDataSet dataSet3 = new LineDataSet(deaths, "Total Deaths");
//                setDatasetProperties3(dataSet3);
//
//                LineData lineData=new LineData(dataSet1,dataSet2,dataSet3);
//                setlinechartproperties(mLineChart,dateVals);
//                mLineChart.setData(lineData);
//
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        mReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<DataModel> stateData = new ArrayList<>();
                for(DataSnapshot mDataSnapshot:dataSnapshot.getChildren()){
                    DataModel data=mDataSnapshot.getValue(DataModel.class);
                    stateData.add(data);
                }
                recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());





                adapter = new CustomAdapter(stateData);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    return view;
}

}

