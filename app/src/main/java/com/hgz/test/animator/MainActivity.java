package com.hgz.test.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView showIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showIv = (ImageView) findViewById(R.id.showIv);

        showIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                useValueAnimator();
//                 useObjectAnimator();
//                    useViewPropertyAnimator();
//                useAnimatorSet();
//                useObjectAnimatorSet();
//                useValueAnimatorSet();
//                useValueAnimatorXml();
//                useObjectAnimatorXml();
                useAnimatorXml();
            }
        });

    }
    //使用ValueAnimator的方法完成动画
    private void useValueAnimator(){
//        属性动画  ValueAnimator 的用法  ,属性动画本质是值动画
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                valueAnimator.setDuration(3000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        //设置透明
//                        showIv.setAlpha(animatedValue);
                        ViewGroup.LayoutParams layoutParams = showIv.getLayoutParams();
                        layoutParams.width= (int) (animatedValue*500);
                        layoutParams.height= (int) (animatedValue*500);
                        showIv.setLayoutParams(layoutParams);
                    }
                });
                valueAnimator.start();
    }
    //使用ObjectAnimator的方法完成动画
    private void useObjectAnimator(){
        //透明度变化
//        ObjectAnimator alphaObjectAnimator = ObjectAnimator.ofFloat(showIv,"alpha",0,1);
//        alphaObjectAnimator.setDuration(3000).start();
        //平移动画
//        ObjectAnimator translationXObjectAnimator = ObjectAnimator.ofFloat(showIv, "translationX", 0, 1000);
//        translationXObjectAnimator.setDuration(3000).start();
        //旋转动画
//        ObjectAnimator rotateObjectAnimator = ObjectAnimator.ofFloat(showIv, "rotation", 0, 360);
//        rotateObjectAnimator.setDuration(3000).start();
        //缩放动画
        ObjectAnimator scaleObjectAnimator = ObjectAnimator.ofFloat(showIv, "scaleX", 0f, 2f);
        scaleObjectAnimator.setDuration(3000);
        //重复3次
        scaleObjectAnimator.setRepeatCount(3);
        scaleObjectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        scaleObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        scaleObjectAnimator.start();

    }
    //使用ViewPropertyAnimator的方法完成动画
    private void useViewPropertyAnimator(){
        showIv.animate().alpha(0.5f)
                .translationX(300)
                .scaleX(2)
                .scaleY(2)
                .rotation(360f)
                .setDuration(3000)
                //延迟两秒执行
                .setStartDelay(2000)
                .start();
    }
    //使用AnimatorSet的方法完成组合动画
    private void useAnimatorSet(){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(showIv, "alpha", 0f, 1f);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(showIv, "translationX", 0f, 200f);
        animatorSet.playTogether(alpha,translationX);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }
    //使用ObjectAnimator完成组合动画
    private void useObjectAnimatorSet(){
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 0f, 200f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(showIv, alpha, translationX);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
    //使用ValueAnimator完成组合动画
    private void useValueAnimatorSet(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = showIv.getLayoutParams();
                layoutParams.width= (int) (animatedValue*300);
                layoutParams.height= (int) (animatedValue*300);
                showIv.setLayoutParams(layoutParams);
                showIv.setAlpha(animatedValue);
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }
    //通过引用ValueAnimation的xml文件完成动画
    private void useValueAnimatorXml(){
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.value_animator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                showIv.setAlpha(animatedValue);
            }
        });
        animator.setDuration(3000);
        animator.start();
    }
    //通过引用ObjectAnimator的xml文件完成动画
    private void useObjectAnimatorXml(){
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.object_animator);
        animator.setTarget(showIv);
        animator.start();
    }
    //通过引用AnimatorSet的xml文件完成动画
    private void useAnimatorXml(){
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.set_animator);
        animator.setTarget(showIv);
        animator.start();
    }

}
