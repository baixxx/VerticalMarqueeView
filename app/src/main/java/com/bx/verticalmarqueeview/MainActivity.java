package com.bx.verticalmarqueeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bx.marqueeviewlibrary.MarqueeView;

import java.util.ArrayList;
import java.util.List;

/**
*@author small white
*@date 2018/4/13
*@fuction 
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> notices = new ArrayList<>();
        notices.add("套马的汉子你威武雄壮");
        notices.add("飞驰的骏马像疾风一样");
        notices.add("一望无际的原野随你去流浪");
        notices.add("你的心海和大地一样宽广");
        MarqueeView marqueeView = findViewById(R.id.marqueeview);
        marqueeView.startMarquee(notices);
    }
}
