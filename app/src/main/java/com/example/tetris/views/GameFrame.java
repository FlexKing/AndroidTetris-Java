package com.example.tetris.views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.tetris.presenters.Point;

public class GameFrame extends View {
    public GameFrame(Context context) {
        super(context);
    }

    public GameFrame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameFrame(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameFrame(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private Point[][] points;
    private int boxSize;
    private int boxPadding;
    private int gameSize;
    private final Paint paint= new Paint();
    public void init(int gameSize){
        this.gameSize=gameSize;
        getViewTreeObserver().addOnGlobalLayoutListener(()-> {
            boxSize = Math.min(getWidth(),getHeight()) / gameSize;
            boxPadding = boxPadding/10;
        });
    }
    void setPoints(Point[][] points){
        this.points=points;
    }
    private Point getPoint(int x,int y){
        return points[y][x];
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, gameSize, gameSize, paint);
        if (points == null) {
            return;
        }
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                Point point = getPoint(i, j);
                int left, right, top, bottom;
                paint.setColor(Color.WHITE);
                switch (point.type) {
                    case BOX:
                        left = boxSize * point.x + boxPadding;
                        right = left + boxSize - boxPadding;
                        top = boxSize * point.y + boxPadding;
                        bottom = top + boxSize - boxPadding;
                        break;
                    case VERTICAL_LINE:
                        left = boxSize * point.x;
                        right = left + boxPadding;
                        top = boxSize * point.y;
                        bottom = top + boxSize;
                        break;
                    case HORIZONTAL_LINE:
                        left = boxSize * point.y;
                        right = left + boxSize;
                        top = boxSize * point.y;
                        bottom = top + boxPadding;
                        break;
                    case EMPTY:
                    default:
                        left = boxSize * point.x;
                        right = left + boxSize;
                        top = boxSize * point.y;
                        bottom = top + boxSize;
                        paint.setColor(Color.BLACK);
                        break;
                }
                canvas.drawRect(left, top, right, bottom, paint);
            }
        }
    }
}
