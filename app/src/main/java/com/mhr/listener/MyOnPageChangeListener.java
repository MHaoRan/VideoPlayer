package com.mhr.listener;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.viewpager.widget.ViewPager;

public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    public MyOnPageChangeListener(){}
    public MyOnPageChangeListener(int offset,int currIndex,int bmpW){
        this.offset = offset;
        this.currIndex = currIndex;
        this.bmpW =  bmpW;
    }

    int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                }/*
                 * else if (currIndex == 2) { animation = new
                 * TranslateAnimation(two, 0, 0, 0); }
                 */
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);

                } /*
                 * else if (currIndex == 2) { animation = new
                 * TranslateAnimation(two, one, 0, 0); }
                 */
                break;
            /*
             * case 2: if (currIndex == 0) { animation = new
             * TranslateAnimation(offset, two, 0, 0); } else if (currIndex == 1)
             * { animation = new TranslateAnimation(one, two, 0, 0); } break;
             */
            }
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

    }
