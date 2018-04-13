package com.bx.marqueeviewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * Created by smallbai on 2018/4/12.
 * Function:
 */

public class MarqueeView extends ViewFlipper {
    /**
     * 滚动间隔时间
     */
    private int interval;
    /**
     * 动画持续时间
     */
    private int animDuration;
    /**
     * 文字大小
     */
    private int textSize;
    /**
     * 文字颜色
     */
    private int textColor;
    private Context context;
    /**
     * 当前消息位置
     */
    private int position;
    /**
     * 动画是否开始,防止
     */
    private boolean isAnimStarted;

    public MarqueeView(Context context) {
        this(context,null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MarqueeViewStyle);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_interval,interval);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_animDuration,animDuration);
        textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_textSize,textSize);
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_textColor,textColor);
        typedArray.recycle();
        setFlipInterval(interval);
    }

    /**
     *
     * @param notices 滚动的消息列表
     */
    public void startMarquee(final List<? extends Object> notices){
        if (notices!=null&&notices.size()>0){
            //开启滚动,耗时异步处理
            post(new Runnable() {
                @Override
                public void run() {
                    //避免重影
                    removeAllViews();
                    clearAnimation();
                    position = 0;
                    addView(createTextView(position,notices));
                    setInAndOutAnimation();
                    startFlipping();
                    if (getInAnimation()!=null){
                        getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                if (isAnimStarted){
                                    animation.cancel();
                                }
                                isAnimStarted = true;
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                position++;
                                if (position >= notices.size()) {
                                    position = 0;
                                }
                                View view = createTextView(position,notices);
                                if (view.getParent() == null) {
                                    addView(view);
                                }
                                isAnimStarted = false;
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     *
     * @param pos 消息的当前位置
     * @param notices 消息列表
     * @return 创建textview
     */
    private TextView createTextView(int pos,List<? extends Object> notices){
        TextView textView = new TextView(context);
        textView.setText(notices.get(pos).toString());
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        return textView;
    }

    /**
     * 设置进入动画和离开动画
     */
    private void setInAndOutAnimation() {
        Animation inAnim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_bottom_into);
        inAnim.setDuration(animDuration);
        setInAnimation(inAnim);

        Animation outAnim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_top_out);
        outAnim.setDuration(animDuration);
        setOutAnimation(outAnim);
    }
}
