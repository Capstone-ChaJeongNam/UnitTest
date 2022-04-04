package com.unittest.mpchart_310_full;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GraphActivity extends AppCompatActivity {
    private LineChart chart;
    private TextView titleTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        chart = findViewById(R.id.chart);
        titleTextView = findViewById(R.id.titleTextView);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("data");
        ArrayList<Consequence> consequences = (ArrayList<Consequence>) args.getSerializable("list");
        String title = intent.getStringExtra("title");
        setChart(consequences);
        titleTextView.setText(title);
    }

    public void setChart( List<Consequence> consequences){
        float count = consequences.size();
        List<String> dates = new ArrayList<>();
        List<Long> scores = new ArrayList<>();
        List<Entry> entries = new ArrayList<>();

        for(int i =0; i<consequences.size(); i++){
            Long score = consequences.get(i).getScore();
            entries.add(new Entry(i, score));
            dates.add(consequences.get(i).getDate().split("-")[1] +"/" +consequences.get(i).getDate().split("-")[2] );
        }

//            for(Consequence consequence : consequences){
//                dates.add(consequence.getDate());
//                scores.add(consequence.getScore());
//                entries.add(new Entry(consequence.getDate(), consequence.getScore()));
//            }

        LineDataSet dataSet = new LineDataSet(entries,"홍길동"); // add entries to dataset
        dataSet.setColor(R.color.blue_500);
        dataSet.setCircleRadius(4f);
        dataSet.setCircleHoleRadius(3f);
        dataSet.setCircleHoleColor(Color.WHITE);
        dataSet.setCircleColor(R.color.blue_500);
        dataSet.setValueTextColor(Color.BLACK); // styling, ...
//        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        LineData lineData = new LineData(dataSet);

        chart.setData(lineData);
        chart.setDescription(null);
        YAxis left = chart.getAxisLeft();
        left.setDrawAxisLine(false);
//            left.setDrawZeroLine(true);
//        left.setEnabled(false);
//            left.setDrawZeroLine(true);
        left.setLabelCount(5,true);
        left.setAxisMinimum(0);
        left.setAxisMaximum(4);
        left.setDrawZeroLine(false);
        left.setDrawGridLines(false);
        YAxis right = chart.getAxisRight();
        right.setEnabled(false);

        XAxis x = chart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
//        x.setDrawGridLines(false);
        x.setAxisMinimum(0f);
//            x.setPosition();
        x.setLabelCount((int)count, true);
//            x.setAxisMaximum(count);
        x.setGridLineWidth(1);

        List<String> y = new ArrayList<>();
        y.add("0");
        y.add("1");
        y.add("2");
        y.add("3");
        y.add("C");
        left.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return y.get((int)value);
            }
        });

        x.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                if(value < dates.size()){
                    return dates.get((int)value);
                }
                else
                    return "";
            }
        });
    }
}


