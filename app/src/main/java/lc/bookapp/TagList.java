package lc.bookapp;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.parse.ParseException;

import java.util.List;

import lc.bookapp.models.Tag;

public class TagList extends View {
    Context c ;
    public TagList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.c = context;
    }

    public TagList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.c = context;

    }

    Paint p = new Paint();
    List<Tag> t;
    public void setList(List<Tag> t ){this.t = t;}
    protected void onDraw(Canvas canvas) {
        p.setFlags(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.RED);
        p.setStrokeWidth(2);
        super.onDraw(canvas);
        float unit = convertDpToPixel(20);
        for(int x =0; x < t.size() ; x++){
            try {
                t.get(x).fetchIfNeeded();
                p.setStyle(Paint.Style.FILL);
                p.setColor(Integer.parseInt(t.get(x).getColor()));
                canvas.drawCircle(
                        x * unit + convertDpToPixel(25) + convertDpToPixel(5) * x,
                        unit / 1.9f,
                        unit / 2,
                        p);
                p.setStyle(Paint.Style.STROKE);
                p.setColor(Color.DKGRAY);
                canvas.drawCircle(
                        x * unit + convertDpToPixel(25) + convertDpToPixel(5) * x,
                        unit / 1.9f,
                        unit / 2,
                        p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            }
    }
    public float convertDpToPixel(float dp){
        Resources resources = c.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
