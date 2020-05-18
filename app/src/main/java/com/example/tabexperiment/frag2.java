package com.example.tabexperiment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
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

public class frag2 extends Fragment {
    LineChart mLineChart;
    BarChart mLineChart2;
    FirebaseDatabase mFirebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference mReference;
    private int i=0;
    private boolean was=false;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && !was){
            was=true;
            Log.d("MyFragment", "Fragment is visible.");
        mLineChart.animateX(1500);
        mLineChart2.animateX(1500);}
        else
            Log.d("MyFragment", "Fragment is not visible.");
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment2, container, false);

        mReference=mFirebaseDatabase.getReference("cases_time_series");


        mLineChart = (LineChart) view.findViewById(R.id.chart);
        mLineChart2 = (BarChart) view.findViewById(R.id.chart2);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataVals=new ArrayList<Entry>();
                ArrayList dataVals2=new ArrayList();
                ArrayList dateVals=new ArrayList();



                for(DataSnapshot mDataSnapshot:dataSnapshot.getChildren()){
                    YourData data=mDataSnapshot.getValue(YourData.class);
                    dataVals.add(new Entry(i,Integer.parseInt(data.getTotalconfirmed())));
                    dataVals2.add(new BarEntry(i,Integer.parseInt(data.getDailyconfirmed())));
                    dateVals.add(data.getDate());
                    i++;
                }
//                mLineChart.setGridBackgroundColor(R.color.colorAccent);
//                mLineChart2.setGridBackgroundColor(R.color.colorAccent);
                LineDataSet dataSet = new LineDataSet(dataVals, "Total Confirmed");
                setDatasetProperties(dataSet);
                LineData lineData = new LineData(dataSet);
                setlinechartproperties(mLineChart,dateVals);
                mLineChart.setData(lineData);

                BarDataSet dataSet2 = new BarDataSet(dataVals2, "Daily Confirmed");
                setBarDatasetProperties(dataSet2);
                BarData lineData2 = new BarData(dataSet2);
                setBarChartProperties(mLineChart2, dateVals);
                mLineChart2.setData(lineData2);
//                mLineChart.invalidate();
//                mLineChart2.invalidate();

                //mLineChart.animateX(2000);
                //mLineChart2.animateX(2000);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;
    }

    public void setBarChartProperties(BarChart mChart, final ArrayList dateVals){

        //mChart.setDrawBorders(true);
        //mChart.setBorderColor(Color.rgb(228,63,90));
        //mChart.setBackgroundColor(Color.rgb(22, 22, 37));
        mChart.getXAxis().setTextColor(Color.rgb(236,8,56));
        mChart.getLegend().setTextColor(Color.rgb(236,8,56));
//        mChart.getBarData().setValueTextColor(Color.WHITE);
        mChart.getAxisLeft().setTextColor(Color.rgb(236,8,56));
        mChart.getAxisRight().setTextColor(Color.rgb(236,8,56));
        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.getDescription().setText("");
        //mChart.getAxisLeft().setGridColor(Color.rgb(236,8,56));
        mChart.getAxisRight().setGridColor(Color.rgb(236,8,56));
        mChart.getXAxis().setAxisLineColor(Color.rgb(236,8,56));
        //mChart.setGridBackgroundColor(Color.rgb(236,8,56));
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
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
                                            }

        );


        //mChart.setGridBackgroundColor(Color.rgb(247,243,247));




    }
    public void setDatasetProperties(LineDataSet linedataset){
//        linedataset.setCircleColor(Color.rgb(228, 63, 90));
//        linedataset.isDrawCirclesEnabled();
//        linedataset.setFillColor(Color.rgb(22,22,37));
//        linedataset.isDrawCircleHoleEnabled();
//        linedataset.setColor(Color.rgb(228,63,90));
//        linedataset.setValueTextSize(12);
//        linedataset.setValueTextColor(Color.rgb(228, 63, 90));

        linedataset.setFillAlpha(65);
        linedataset.setFillColor(Color.rgb(228, 63, 90));
        linedataset.setColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColor(Color.rgb(228, 63, 90));
        linedataset.setCircleColorHole(Color.rgb(22,22,37));
        //linedataset.setLineWidth(2f);
        //linedataset.setCircleSize(5f);
        linedataset.setDrawValues(false);





    }
    public void setBarDatasetProperties(BarDataSet bardataset){
        bardataset.setColor(Color.rgb(228, 63, 90));
        bardataset.setValueTextSize(12);
        bardataset.setValueTextColor(Color.rgb(228, 63, 90));

    }

    public void setlinechartproperties(LineChart mChart, final ArrayList dateVals){
        //mChart.setDrawBorders(true);
        //mChart.setBorderColor(Color.rgb(228,63,90));
        //mChart.setBackgroundColor(Color.rgb(22, 22, 37));
        mChart.getXAxis().setTextColor(Color.rgb(236,8,56));
        mChart.getLegend().setTextColor(Color.rgb(236,8,56));


//        mChart.getBarData().setValueTextColor(Color.WHITE);
        mChart.getAxisLeft().setTextColor(Color.rgb(236,8,56));
        mChart.getAxisRight().setTextColor(Color.rgb(236,8,56));
//        mChart.getXAxis().setGridColor(Color.rgb(22,22,37));
//        mChart.getXAxis().setAxisLineColor(Color.rgb(22,22,37));

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
    }



}
