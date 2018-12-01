package com.example.pitts.ife.Tools;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int spaceTop;
    private int spaceBottom;
    private int spaceLeft;
    private int spaceRight;
    private boolean temp;

    public RecyclerItemDecoration(int space) {
        this.space = space;
        this.temp = true;
    }

    public RecyclerItemDecoration(int spaceTop, int spaceBottom, int spaceLeft, int spaceRight) {
        this.spaceTop = spaceTop;
        this.spaceBottom = spaceBottom;
        this.spaceLeft = spaceLeft;
        this.spaceRight = spaceRight;
        this.temp = false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(temp){
            outRect.bottom = space;
            if (parent.getChildPosition(view) == 0) outRect.top = space;
        }
        else {
            outRect.bottom = spaceBottom;
            outRect.top = spaceTop;
            outRect.left = spaceLeft;
            outRect.right = spaceRight;
        }
    }
}
