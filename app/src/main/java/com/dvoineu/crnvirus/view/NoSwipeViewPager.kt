package com.dvoineu.crnvirus.view

import android.content.Context
//import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class NoSwipeViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    override fun onTouchEvent(event: MotionEvent) = false

    override fun onInterceptTouchEvent(event: MotionEvent) = false
}