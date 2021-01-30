package com.example.estrrado_sujeeshtask.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.estrrado_sujeeshtask.Network.ApiCall1
import com.example.estrrado_sujeeshtask.R
import com.example.estrrado_sujeeshtask.adapter.CategoryAdapter
import com.example.estrrado_sujeeshtask.adapter.EventCarouselAdapter
import com.example.estrrado_sujeeshtask.adapter.EventsFeatureAdapter
import com.example.estrrado_sujeeshtask.data.ProductPojo
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    lateinit var eventRV: RecyclerView
    lateinit var event_nodataTxt:TextView
    lateinit var event_carouselTxt:TextView
    //val eventList:ArrayList<EventDataOptim?>?=ArrayList()
    lateinit var eventAdapter:EventsFeatureAdapter
    val eventListPojo=ArrayList<ProductPojo.Data.Featured>()

    lateinit var cateRV: RecyclerView
    lateinit var cateAdapter: CategoryAdapter
    val cateListPojo=ArrayList<ProductPojo.Data.Category>()

    private lateinit var homeViewModel: HomeViewModel
    val eventCarsouselList1= ArrayList<ProductPojo.Data.BannerSlider>()
    lateinit var eventCarousel: ViewPager
    lateinit var eventCarouselAdapter: EventCarouselAdapter
    lateinit var sliderDotspanel: LinearLayout
    var dotscount: Int = 0
    lateinit var dots: Array<ImageView?>
    var  isLoading:Boolean = false


    var startCount=0

    @SuppressLint("WrongConstant")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        eventRV=root.findViewById(R.id.eventfrag_rv)
        event_nodataTxt=root.findViewById(R.id.event_nodata_txt)
        event_carouselTxt=root.findViewById(R.id.event_carousel_txt)

        eventRV.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        eventAdapter = EventsFeatureAdapter(eventListPojo, requireContext(),false)
        eventRV.adapter = eventAdapter

        cateRV=root.findViewById(R.id.category_rv)
        cateRV.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        cateAdapter = CategoryAdapter(cateListPojo, requireContext(),false)
        cateRV.adapter = cateAdapter

        eventCarousel=root.findViewById(R.id.event_viewPager)
        sliderDotspanel=root.findViewById(R.id.event_sliderdots_lay)

        apiCall()

        return root
    }

    fun apiCall() {
        val data=JsonObject()
        data.addProperty("access_token",752027)
        data.addProperty("country_code","SA")
        ApiCall1.create().shoplistData(data).enqueue(object : Callback<ProductPojo?> {

            override fun onResponse(call: Call<ProductPojo?>, response: Response<ProductPojo?>) {
                println("Resp*** "+response.body())
                if (response.isSuccessful){

                    eventCarsouselList1.addAll((response.body()?.data?.banner_slider!!))

                    eventListPojo.addAll(response.body()?.data?.featured!!)

                    cateListPojo.addAll(response.body()!!.data.categories)

                 //   eventAdapter.notifyItemInserted(eventCarsouselList1.size-1)

                    eventCarouselAdapter= EventCarouselAdapter(eventCarsouselList1,activity!!)
                    eventCarousel.adapter=eventCarouselAdapter
                    dotscount=eventCarouselAdapter.count
                    dots = arrayOfNulls(dotscount)
                 //   logMe("Check::::::::::::::::::: event dotscount $dotscount")
                 //   logMe("Check::::::::::::::::::: event if ")
                    if(dotscount>0){
                        setDots()
                    }

                }else{
                    eventCarousel.visibility=View.GONE
                  //  event_carouselTxt.visibility=View.VISIBLE
                }


            }

            override fun onFailure(call: Call<ProductPojo?>, t: Throwable) {
                Toast.makeText(context,"Data erroe",Toast.LENGTH_LONG).show()
            }
        });
    }

    fun setDots(){
        for (i in 0 until dotscount) {

            dots[i]= ImageView(activity)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(requireActivity().applicationContext, R.drawable.event_nonactivate_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)

            sliderDotspanel.addView(dots[i], params)

        }

        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(requireActivity().applicationContext, R.drawable.event_activate_dot))

        eventCarousel.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                try {
                    for (i in 0 until dotscount) {
                        dots[i]!!.setImageDrawable(
                            ContextCompat.getDrawable(
                                activity!!.applicationContext,
                                R.drawable.event_nonactivate_dot
                            )
                        )
                    }

                    dots[position]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            activity!!.applicationContext,
                            R.drawable.event_activate_dot
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        val timer = Timer()
        timer.scheduleAtFixedRate(MyTimerTask(), 2000, 4000)

    }

    inner class MyTimerTask : TimerTask() {

        override fun run() {

            activity?.runOnUiThread(Runnable {
                when {
                    eventCarousel.currentItem == 0 -> eventCarousel.currentItem = 1
                    eventCarousel.currentItem == 1 -> eventCarousel.currentItem = 2
                    eventCarousel.currentItem == 2 -> eventCarousel.currentItem = 3
                    eventCarousel.currentItem == 3 -> eventCarousel.currentItem = 4
                    eventCarousel.currentItem == 5 -> eventCarousel.currentItem = 5
                    else -> eventCarousel.currentItem = 0
                }
            })

        }
    }

}