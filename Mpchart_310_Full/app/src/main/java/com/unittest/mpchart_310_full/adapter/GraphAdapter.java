package com.unittest.mpchart_310_full.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.unittest.mpchart_310_full.Consequence;
import com.unittest.mpchart_310_full.GraphActivity;
import com.unittest.mpchart_310_full.Question;
import com.unittest.mpchart_310_full.R;
import com.unittest.mpchart_310_full.ToggleAnimation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.ViewHolder>{
//    private List<Message> messages;
    private List<Question> questions;


    class ViewHolder extends RecyclerView.ViewHolder {

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

        public void expandItem(Question question, View item) {
            Boolean show = toggleLayout(!question.getExpanded(), item, layoutExpand);
            setImageButton(!question.getExpanded());
            question.setExpanded(show);
        }

        private void setImageButton(Boolean isExpanded) {
            if (isExpanded) {
                showImageButton.setBackgroundResource(R.drawable.ic_square_minus);
            } else {
                showImageButton.setBackgroundResource(R.drawable.ic_square_plus);

            }

        }

        private void bind(Question question) {
            String position = Integer.toString(getLayoutPosition() + 1);
            titleTextView.setText(position + ". " + question.getTitle() + ".");
//            setChart(question);
            showImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    expandItem(question, view);
                    Intent intent = new Intent(view.getContext(), GraphActivity.class);
                    List<Consequence> consequences = question.getConsequences();
                    Bundle args = new Bundle();
                    args.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) consequences);
                    intent.putExtra("data",args);
                    intent.putExtra("title", question.getTitle());
                    view.getContext().startActivity(intent);
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_message, parent, false) ;
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
