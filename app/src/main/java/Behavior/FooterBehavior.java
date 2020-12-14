package Behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

//第一种，自定义View去监听CoordinatorLayout里的滑动状态
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {
    private int directionChange;
    public FooterBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.e("SADA",directionChange+"");
        Log.e("SADA+DY",dy+"");
        Log.e("SADA+child",child.getHeight()+"");
        if(dy>0&&directionChange<0||dy<0&&directionChange>0){
            child.animate().cancel();
            directionChange=0;
        }
        directionChange+=dy;
        if(directionChange>child.getHeight()&&child.getVisibility()==View.VISIBLE){
            hide(child);
        }else if(directionChange<0&&child.getVisibility()==View.INVISIBLE){
            show(child);
        }
    }
    private void hide(final View view){
        ViewPropertyAnimator animator=view.animate().translationY(view.getHeight()).setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }

    private void show(final View view){
        ViewPropertyAnimator animator=view.animate().translationY(0).setInterpolator(new FastOutSlowInInterpolator()).setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }
}
