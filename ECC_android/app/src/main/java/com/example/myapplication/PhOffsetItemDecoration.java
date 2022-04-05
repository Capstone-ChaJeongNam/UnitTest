package com.example.myapplication;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhOffsetItemDecoration extends RecyclerView.ItemDecoration {

    private final int mPadding;

    public PhOffsetItemDecoration(int a_padding) {
        this.mPadding = a_padding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect a_outRect, @NonNull View a_view, @NonNull RecyclerView a_parent, @NonNull RecyclerView.State a_state) {
        super.getItemOffsets( a_outRect, a_view, a_parent, a_state);
        a_outRect.top=mPadding;
        a_outRect.bottom = mPadding;
        a_outRect.left = mPadding;
        a_outRect.right = mPadding;


    }
}
