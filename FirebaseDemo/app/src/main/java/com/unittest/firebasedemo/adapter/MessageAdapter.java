package com.unittest.firebasedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unittest.firebasedemo.Message;
import com.unittest.firebasedemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private List<Message> messages;

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTextView;
        private TextView contentTextView;
        private TextView keyTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            keyTextView = itemView.findViewById(R.id.keyTextView);
        }
    }

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_message, parent, false) ;
        MessageAdapter.ViewHolder vh = new MessageAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message message = messages.get(position);

        holder.nameTextView.setText(message.getName());
        holder.contentTextView.setText(message.getContent());
        holder.keyTextView.setText(message.getKey());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
