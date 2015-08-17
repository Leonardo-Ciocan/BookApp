package lc.bookapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SquareView extends RelativeLayout {
    public SquareView(Context context , int id) {
        super(context);
        inflate(getContext(), id, this);
    }

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.char_tile, this);
    }

    public SquareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.char_tile, this);
    }

    @Override
public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
}
}
