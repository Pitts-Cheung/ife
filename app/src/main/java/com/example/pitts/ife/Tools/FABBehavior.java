package com.example.pitts.ife.Tools;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class FABBehavior extends FloatingActionButton.Behavior {

    public FABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency){

        return dependency instanceof BottomNavigationView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency){
        if(dependency.getTranslationY() > 0){
            child.hide();
        } else if(dependency.getTranslationY() == 0){
            child.show();
        }

        return true;
    }
}
