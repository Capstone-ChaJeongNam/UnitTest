package com.unittest.firebasedemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.unittest.firebasedemo.Consequence;
import com.unittest.firebasedemo.Question;
import com.unittest.firebasedemo.R;
import com.unittest.firebasedemo.ToggleAnimation;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.ViewHolder>{
//    private List<Message> messages;
    private List<Question> questions;


    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout layoutExpand;
        private ImageButton showImageButton;
        private TextView titleTextView;
        private LineChart chart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutExpand = itemView.findViewById(R.id.layout_expand);
            showImageButton = itemView.findViewById(R.id.showImageButton);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            chart = itemView.findViewById(R.id.chart);
//            setQuestion();
        }

        public void expandItem(Question question, View item){
            Boolean show = toggleLayout(!question.getExpanded(), item, layoutExpand);
            setImageButton(!question.getExpanded());
            question.setExpanded(show);
        }

        private void setImageButton(Boolean isExpanded){
            if(isExpanded){
                showImageButton.setBackgroundResource(R.drawable.ic_square_minus);
            }else{
                showImageButton.setBackgroundResource(R.drawable.ic_square_plus);

            }

        }

        private void bind(Question question){
            String position = Integer.toString(getLayoutPosition() + 1);
            titleTextView.setText(position+". " + question.getTitle() + ".");
            setChart(question);
            showImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expandItem(question, view);
                }
            });
        }



        public void setChart(Question question){
            List<Consequence> consequences = new ArrayList<>(question.getConsequences());
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
            dataSet.setColor(R.color.mainColor);
            dataSet.setCircleRadius(4f);
            dataSet.setCircleHoleRadius(3f);
            dataSet.setCircleHoleColor(Color.WHITE);
            dataSet.setCircleColor(R.color.mainColor);
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

//    public MessageAdapter(List<Message> messages) {
//        this.messages = messages;
//    }


    public GraphAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public GraphAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_message, parent, false) ;
        GraphAdapter.ViewHolder vh = new GraphAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GraphAdapter.ViewHolder holder, int position) {
        Question question  = questions.get(position);
        holder.bind(question);

//        holder.nameTextView.setText(message.getName());
//        holder.contentTextView.setText(message.getContent());
//        holder.keyTextView.setText(message.getKey());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    private Boolean toggleLayout(Boolean isExpanded, View view, LinearLayout layoutExpand){
        if (isExpanded) {
            ToggleAnimation.Companion.expand(layoutExpand);

        } else {
            ToggleAnimation.Companion.collapse(layoutExpand);
        }
        return isExpanded;
    }
}
