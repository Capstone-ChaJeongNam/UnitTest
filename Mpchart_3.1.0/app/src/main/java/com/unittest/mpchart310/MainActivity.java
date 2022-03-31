package com.unittest.mpchart310;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Entry> entries = new ArrayList<Entry>();
        LineChart chart= findViewById(R.id.chart);

        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 3));
        entries.add(new Entry(4, 4));
        entries.add(new Entry(5, 1));

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK); // styling, ...
//        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        LineData lineData = new LineData(dataSet);

        chart.setData(lineData);
        chart.setDescription(null);
        YAxis left = chart.getAxisLeft();
        left.setDrawZeroLine(true);
//        left.setEnabled(false);
        left.setDrawZeroLine(true);
        left.setLabelCount(5,true);
        left.setAxisMinimum(0);
        left.setAxisMaximum(4);
        YAxis right = chart.getAxisRight();
        right.setEnabled(false);

        XAxis x = chart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
//        x.setDrawGridLines(false);
        x.setAxisMinimum(0f);
        x.setAxisMaximum(5f);
        x.setLabelCount(6, true);
        x.setGridLineWidth(1f);

//        x.enableAxisLineDashedLine(1f,1f,0f);
//        x.setValueFormatter(new IndexAxisValueFormatter(){
//            @Override
//            public String getFormattedValue(float value) {
//                return super.getFormattedValue(value);
//            }
//        });

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
    }
}