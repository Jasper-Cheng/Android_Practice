package Behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
//第二种，定义的View去监听另一个View的变化
public class FooterBehaviorAppBar extends CoordinatorLayout.Behavior<View> {

    public FooterBehaviorAppBar(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        float translationY=Math.abs(dependency.getY());
        child.setTranslationY(translationY);
        return true;
    }
}
