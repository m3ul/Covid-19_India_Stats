package com.example.tabexperiment;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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

public class CustomAdapter extends RecyclerView.Adapter {
    private static int TYPE_graph = 1;
    private static int TYPE_table = 2;

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView state;
        TextView active;
        TextView confirmed;
        TextView recovered;
        TextView deaths;
        CardView tableCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.state = (TextView) itemView.findViewById(R.id.state);
            this.active = (TextView) itemView.findViewById(R.id.active);
            this.confirmed=(TextView)itemView.findViewById(R.id.confirmed);
            this.recovered=(TextView)itemView.findViewById(R.id.recovered);
            this.deaths=(TextView)itemView.findViewById(R.id.deaths);
            this.tableCard=(CardView)itemView.findViewById(R.id.cell_id);

        }

        private void setattributes(int listPosition, ArrayList<DataModel> dataSet){
            TextView state = this.state;
            TextView active = this.active;
            TextView confirmed=this.confirmed;
            TextView recovered=this.recovered;
            TextView deaths=this.deaths;
            //CardView tableCard=(CardView)holder.tableCard;
//        if(listPosition%2 == 0){
//            holder.tableCard.setBackgroundColor(Color.rgb(28,28,43));}
//            else{
//            holder.tableCard.setBackgroundColor(Color.rgb(22,22,37));
//            }




            state.setText(dataSet.get(listPosition-1).getState());
            active.setText(dataSet.get(listPosition-1).getActive());
            confirmed.setText(dataSet.get(listPosition-1).getConfirmed());
            recovered.setText(dataSet.get(listPosition-1).getRecovered());
            deaths.setText(dataSet.get(listPosition-1).getDeaths());

        }


    }
    public static class graphViewHolder extends RecyclerView.ViewHolder {
        FirebaseDatabase mFirebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference mReference=mFirebaseDatabase.getReference("cases_time_series");
        LineData lineData;
        ArrayList dateVals;


        public graphViewHolder(@NonNull final View itemView) {
            super(itemView);
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
                    LineChart mChart =(LineChart) itemView.findViewById(R.id.chart_main);
                    mChart.setData(lineData);
                    setlinechartproperties(mChart);
                    mChart.animateX(1500);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
                private void setDatasetProperties1(LineDataSet linedataset){
                    linedataset.setFillAlpha(65);
                    linedataset.setFillColor(Color.rgb(0,122,252));
                    linedataset.setColor(Color.rgb(0,122,252));
                    linedataset.setCircleColor(Color.rgb(0,122,252));
                    linedataset.setCircleColorHole(Color.rgb(22,22,37));
                    linedataset.setDrawValues(false);
                    linedataset.setDrawHighlightIndicators(false);

                }
                private void setDatasetProperties2(LineDataSet linedataset){
                    linedataset.setFillAlpha(65);
                    linedataset.setFillColor(Color.rgb(39,156,67));
                    linedataset.setColor(Color.rgb(39,156,67));
                    linedataset.setCircleColor(Color.rgb(39,156,67));
                    linedataset.setCircleColorHole(Color.rgb(22,22,37));
                    linedataset.setDrawValues(false);
                    linedataset.setDrawHighlightIndicators(false);

                }
                private void setDatasetProperties3(LineDataSet linedataset){
                    linedataset.setFillAlpha(65);
                    linedataset.setFillColor(Color.rgb(236,8,56));
                    linedataset.setColor(Color.rgb(236,8,56));
                    linedataset.setCircleColor(Color.rgb(236,8,56));
                    linedataset.setCircleColorHole(Color.rgb(22,22,37));
                    linedataset.setDrawValues(false);
                    linedataset.setDrawHighlightIndicators(false);

                }
                public LineChart setlinechartproperties(LineChart mChart){
                    int width=mChart.getWidth();
                    mChart.setMinimumHeight(width);

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
                                if(value <= dateVals.size()-1){
                                    return (String)dateVals.get((int)value);
                                }
                                return " ";
                            }
                            return " ";
                        }
                    });
                    return mChart;
                }
            });
        }
    }






    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return TYPE_graph;
        }
        else{
            return TYPE_table;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        if(viewType == TYPE_graph){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_chart, parent, false);
            graphViewHolder graphholder = new graphViewHolder(view);
            return graphholder;


        }else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_layout, parent, false);



            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int listPosition) {
        if(listPosition==0){}
        else {if(listPosition == 1){
            TextView active =(TextView) holder.itemView.findViewById(R.id.active);
            TextView confirmed =(TextView) holder.itemView.findViewById(R.id.confirmed);
            TextView recovered =(TextView) holder.itemView.findViewById(R.id.recovered);
            TextView deaths =(TextView) holder.itemView.findViewById(R.id.deaths);
            active.setTextSize(11);
            confirmed.setTextSize(11);
            recovered.setTextSize(11);
            deaths.setTextSize(11);
            ((MyViewHolder)holder).setattributes(listPosition,dataSet);




        }else{
            ((MyViewHolder)holder).setattributes(listPosition,dataSet);
//
//            TextView state = holder.state;
//            TextView active = holder.active;
//            TextView confirmed=holder.confirmed;
//            TextView recovered=holder.recovered;
//            TextView deaths=holder.deaths;
//            //CardView tableCard=(CardView)holder.tableCard;
////        if(listPosition%2 == 0){
////            holder.tableCard.setBackgroundColor(Color.rgb(28,28,43));}
////            else{
////            holder.tableCard.setBackgroundColor(Color.rgb(22,22,37));
////            }
//
//
//
//
//            state.setText(dataSet.get(listPosition).getState());
//            active.setText(dataSet.get(listPosition).getActive());
//            confirmed.setText(dataSet.get(listPosition).getConfirmed());
//            recovered.setText(dataSet.get(listPosition).getRecovered());
//            deaths.setText(dataSet.get(listPosition).getDeaths());

        }

    }}




    @Override
    public int getItemCount() {
        return dataSet.size()+1;
    }
//
//private void setRowApperance(CardView tableCard, int position){
//        if(position%2 ==1){
//           tableCard.setBackgroundColor(Color.rgb(28,28,43));
//        }
//}
}