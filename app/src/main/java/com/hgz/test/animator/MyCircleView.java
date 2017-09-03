package com.hgz.test.animator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by Administrator on 2017/9/3.
 */

public class MyCircleView extends View {
    Paint paint;
    float radius = 100;

    Point currentPoint;

    public MyCircleView(Context context) {
        super(context);
        init();
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);

    }

    //根据currentPoint的x和y确定圆心的位置,去画圆
    private void drawCirle(Canvas canvas) {

        //只执行一次动画
        if (currentPoint == null) {
            startPointAnimation();
        }
        canvas.drawCircle(currentPoint.getX(), currentPoint.getY(), radius, paint);
    }


    //开始执行对Point对象操作的动画
    private void startPointAnimation() {
        Point startPoint = new Point(radius, radius);
        Point endPoint = new Point(getWidth() - radius, getHeight() - radius);
        currentPoint = startPoint;

        //fraction是一个从0到1变化的值
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator<Point>() {
            @Override
            public Point evaluate(float fraction, Point startValue, Point endValue) {
                float startX = startValue.getX();
                float endX = endValue.getX();
                float currentX = startX + fraction * (endX - startX);

                float startY = startValue.getY();
                float endY = endValue.getY();
                float currentY = startY + fraction * (endY - startY);

                Log.e("tag", "===== " + currentY);
                return new Point(currentX, currentY);
            }
        }, startPoint, endPoint);

        //设置监听,获取当前的监听对象
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();

                //重新绘制,   (改变了currentPoint之后去重新绘制圆)
                invalidate();
            }
        });

        //设置动画插值器,改变动画变化速率 , 加速,先加速后减速,弹跳效果
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //去画圆
        drawCirle(canvas);
    }
}
