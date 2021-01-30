package com.example.estrrado_sujeeshtask.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.estrrado_sujeeshtask.R
import com.example.estrrado_sujeeshtask.data.ProductPojo


class EventsFeatureAdapter(val eventList: ArrayList<ProductPojo.Data.Featured>, val mContext: Context, val isMyEvet: Boolean) : RecyclerView.Adapter<EventsFeatureAdapter.ViewHolder>() {

    private val VIEW_TYPE_LOADING = 1
    private val VIEW_TYPE_ITEM = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsFeatureAdapter.ViewHolder {
       // if(viewType == VIEW_TYPE_ITEM) {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_event, parent, false)
            return ViewHolder(v)

      //  }

    }

    //this method is binding the data on the list
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: EventsFeatureAdapter.ViewHolder, position: Int) {
        val data = eventList[position]

        //logMe("Pos:: "+position+" Title:: "+data!!.title)
        Glide.with(mContext)  //2
            .load(data.prd_image) //3
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.eventAttachImg)


        holder.eventName.text= data!!.prd_name
        holder.eventquant.text= data!!.qty
        holder.eventweight.text= data!!.price+" "+data!!.currency




    }



    //this method is giving the size of the list
    override fun getItemCount(): Int {
        println("tag size:::: "+eventList.size)
        return eventList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (eventList[position] == null) {
            return VIEW_TYPE_LOADING
        } else {
            return VIEW_TYPE_ITEM
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(v: View?) {
        }

        internal var eventAttachImg: ImageView
        internal var eventName:TextView
        internal var eventquant:TextView
        internal var eventweight:TextView
       // internal var eventDuration:TextView


        init {

            eventAttachImg = view.findViewById(R.id.event_attch_img)
            eventName=view.findViewById(R.id.prod_name)
            eventquant=view.findViewById(R.id.prod_quan)
            eventweight=view.findViewById(R.id.prod_weight)

        }

    }
}