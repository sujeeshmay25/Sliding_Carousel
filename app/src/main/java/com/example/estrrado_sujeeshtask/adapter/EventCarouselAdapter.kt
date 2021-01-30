package com.example.estrrado_sujeeshtask.adapter

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.estrrado_sujeeshtask.R
import com.example.estrrado_sujeeshtask.data.ProductPojo

//import kotlinx.android.synthetic.main.adapter_imageslider.view.*

class EventCarouselAdapter(val list:ArrayList<ProductPojo.Data.BannerSlider>, val mContext:Context): PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null
    var ivView:ImageView?=null
    override fun isViewFromObject(view: View, p1: Any): Boolean {
        return view == p1
    }

    override fun getCount(): Int {
        //logMe("caro:::: "+list.size)
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.adapter_imageslider, null)
ivView=view.findViewById(R.id.is_image)
//todo show amount
        val data= list[position]


        Glide.with(view)  //2
            .load(data.slider_image) //3
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(ivView!!)

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}