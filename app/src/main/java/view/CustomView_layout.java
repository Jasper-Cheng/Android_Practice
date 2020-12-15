package view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class CustomView_layout extends View {

    private Scroller mScroller;
    private int lastX;
    private int lastY;

    public CustomView_layout(Context context) {
        super(context);
    }

    public CustomView_layout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }

    public CustomView_layout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x= (int) event.getX();
        int y= (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=x;
                lastY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX=x-lastX;
                int offsetY=y-lastY;
                ((View) getParent()).scrollBy(-offsetX,-offsetY);
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        Log.e("SDSA-s",mScroller.getCurrX()+" "+mScroller.getCurrY());
        int scrollX=getScrollX();
        int scrollY=getScrollY();
        int moveDistanceX=destX-scrollX;
        int moveDistanceY=destY-scrollY;
        mScroller.startScroll(scrollX,0,moveDistanceX,moveDistanceY,2000);
        invalidate();
    }
}
