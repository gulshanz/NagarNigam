package com.gulshan.nagarnigam.ui.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class HomeDpAdapter() :PagerAdapter() {

    private lateinit var imageArray: Array<Int>
    private lateinit var activity:FragmentActivity

    constructor(array: Array<Int>, activity: FragmentActivity) : this() {
        this.imageArray = array
        this.activity = activity
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = activity.layoutInflater

        val viewItem: View = inflater.inflate(com.gulshan.nagarnigam.R.layout.image_dp_1, container, false)
        val imageView: ImageView = viewItem.findViewById<View>(com.gulshan.nagarnigam.R.id.image_dp) as ImageView
        imageView.setImageResource(imageArray.get(position))
//        val textView1 = viewItem.findViewById<View>(R.id.textview) as TextView
//        textView1.setText(namesArray.get(position))
        (container as ViewPager).addView(viewItem)

        return viewItem
    }

    override fun getCount(): Int {
        return imageArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }
}