package com.example.tabexperiment;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

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

public class MainChart extends LineChart {
    FirebaseDatabase mFirebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference mReference=mFirebaseDatabase.getReference("cases_time_series");
    LineData lineData;
    ArrayList dateVals;


    public MainChart(Context context) {
        super(context);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<Entry> confirmed=new ArrayList<Entry>();
                ArrayList<Entry> recovered=new ArrayList<Entry>();
                ArrayList<Entry> deaths=new ArrayList<Entry>();

                dateVals=new ArrayList();
                int i=0;
                for(DataSnapshot mDataSnapshot:dataSnapshot.getChildren()){
                    YourData data=mDataSnapshot.getValue(YourData.class);
                    confirmed.add(new Entry(i,Integer.parseInt(data.getTotalconfirmed())));
                    recovered.add(new BarEntry(i,Integer.parseInt(data.getTotalrecovered())));
                    deaths.add(new BarEntry(i,Integer.parseInt(data.getTotaldeceased())));
                    dateVals.add(data.getDate());
                    i++;
                }
                LineDataSet dataSet1 = new LineDataSet(confirmed, "Total Confirmed");
                setDatasetProperties1(dataSet1);
                LineDataSet dataSet2 = new LineDataSet(recovered, "Total Cured");
                setDatasetProperties2(dataSet2);
                LineDataSet dataSet3 = new LineDataSet(deaths, "Total Deaths");
                setDatasetProperties3(dataSet3);

                lineData=new LineData(dataSet1,dataSet2,dataSet3);
                //setlinechartproperties(MainChart);
               // MainChart.setData(lineData);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
    private void setDatasetProperties1(LineDataSet linedataset){
        linedataset.setFillAlpha(65);
        linedataset.setFillColor(Color.rgb(228, 63, 90));
        linedataset.setColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColorHole(Color.rgb(22,22,37));
        linedataset.setDrawValues(false);

    }
    private void setDatasetProperties2(LineDataSet linedataset){
        linedataset.setFillAlpha(65);
        linedataset.setFillColor(Color.rgb(228, 63, 90));
        linedataset.setColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColorHole(Color.rgb(22,22,37));
        linedataset.setDrawValues(false);

    }
    private void setDatasetProperties3(LineDataSet linedataset){
        linedataset.setFillAlpha(65);
        linedataset.setFillColor(Color.rgb(228, 63, 90));
        linedataset.setColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColorHole(Color.rgb(22,22,37));
        linedataset.setDrawValues(false);

    }
    public MainChart setlinechartproperties(MainChart mChart){

        mChart.getXAxis().setTextColor(Color.rgb(236,8,56));
        mChart.getLegend().setTextColor(Color.rgb(236,8,56));


        mChart.getAxisLeft().setTextColor(Color.rgb(236,8,56));
        mChart.getAxisRight().setTextColor(Color.rgb(236,8,56));


        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getDescription().setText("");
        mChart.getAxisRight().setGridColor(Color.rgb(236,8,56));
        mChart.getXAxis().setAxisLineColor(Color.rgb(236,8,56));
        mChart.getXAxis().setValueFormatter(new IAxisValueFormatter(){

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value>=0){
                    if(value<=dateVals.size()-1){
                        return (String)dateVals.get((int)value);
                    }
                    return " ";
                }
                return " ";
            }
        });
        return mChart;
    }









}
